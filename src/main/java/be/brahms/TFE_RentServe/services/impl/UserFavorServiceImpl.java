package be.brahms.TFE_RentServe.services.impl;

import be.brahms.TFE_RentServe.exceptions.favor.FavorNotFoundException;
import be.brahms.TFE_RentServe.exceptions.picture.PictureException;
import be.brahms.TFE_RentServe.exceptions.user.UserNotFoundException;
import be.brahms.TFE_RentServe.exceptions.userFavor.UserFavorException;
import be.brahms.TFE_RentServe.exceptions.userFavor.UserFavorNotFoundException;
import be.brahms.TFE_RentServe.exceptions.userFavor.UserFavourEmptyException;
import be.brahms.TFE_RentServe.mappers.UserFavorMapper;
import be.brahms.TFE_RentServe.models.dtos.userFavor.UserFavorByIdDTO;
import be.brahms.TFE_RentServe.models.dtos.userFavor.UserFavorDTO;
import be.brahms.TFE_RentServe.models.entities.Favor;
import be.brahms.TFE_RentServe.models.entities.Picture;
import be.brahms.TFE_RentServe.models.entities.User;
import be.brahms.TFE_RentServe.models.entities.UserFavor;
import be.brahms.TFE_RentServe.models.forms.userFavor.UserFavorCreateForm;
import be.brahms.TFE_RentServe.repositories.FavorRepository;
import be.brahms.TFE_RentServe.repositories.PictureRepository;
import be.brahms.TFE_RentServe.repositories.UserFavorRepository;
import be.brahms.TFE_RentServe.repositories.UserRepository;
import be.brahms.TFE_RentServe.services.UserFavorService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Service implementation for managing UserFavor. Uses UserFavorRepository to perform database
 * operations uses UserFavorMapper to map between form to entity or dto to entity
 */
@Service
public class UserFavorServiceImpl implements UserFavorService {

    private final UserFavorRepository userFavorRepository;
    private final UserFavorMapper userFavorMapper;
    private final UserRepository userRepository;
    private final FavorRepository favorRepository;
    private final PictureRepository pictureRepository;

    /**
     * Constructor with parameters
     *
     * @param userFavorRepository the userFavorRepo to access userFavor data
     * @param userFavorMapper     map between from userFavor to entity or dto to entity
     * @param userRepository      the userRepo to access User data
     * @param favorRepository     the favorRepo to access Favor data
     * @param pictureRepository   the pictureRepo to access Picture data
     */
    public UserFavorServiceImpl(
            UserFavorRepository userFavorRepository,
            UserFavorMapper userFavorMapper,
            UserRepository userRepository,
            FavorRepository favorRepository,
            PictureRepository pictureRepository
    ) {
        this.userFavorRepository = userFavorRepository;
        this.userFavorMapper = userFavorMapper;
        this.userRepository = userRepository;
        this.favorRepository = favorRepository;
        this.pictureRepository = pictureRepository;
    }

    /**
     * Get a list of all users favour If list is empty, send an exception
     *
     * @return a list of users favour
     */
    @Override
    public List<UserFavorDTO> findAllUserFavour() {
        List<UserFavor> listUserFavour = userFavorRepository.findAll();

        if (listUserFavour.isEmpty()) {
            throw new UserFavourEmptyException();
        }
        return listUserFavour.stream().map(userFavorMapper::toListDto).toList();
    }

    /**
     * Get a list of all users favour grouped by ID favor
     *
     * @param favorId the identifier favor
     * @return a list of user favor by Favor ID
     */
    @Override
    public List<UserFavorDTO> findAllUserFavourByFavorId(long favorId) {
        List<UserFavor> listUserFavour = userFavorRepository.findAllUserFavourByFavorId(favorId);

        if (!userFavorRepository.existsById(favorId)) {
            throw new UserFavorNotFoundException();
        }

        if (listUserFavour.isEmpty()) {
            throw new FavorNotFoundException();
        }
        return listUserFavour.stream().map(userFavorMapper::toListDto).toList();
    }

    /**
     * Get a user favor by id
     *
     * @param id the identifier of user favor
     * @return a detail user favor
     */
    @Override
    public UserFavorByIdDTO findUserFavorById(long id) {
        UserFavor userFavorId =
                userFavorRepository.findById(id).orElseThrow(UserFavorNotFoundException::new);

        return userFavorMapper.toIdDto(userFavorId);
    }

    /**
     * Get a list of user favor by user ID
     *
     * @param id the identifier of user
     * @return get a list of user favor grouped by user ID
     */
    @Override
    public List<UserFavorDTO> findAllUserFavourByUserId(long id) {
        List<UserFavor> listUserFavorByUser = userFavorRepository.findAllUserFavourByUserId(id);

        if (userRepository.findById(id).isEmpty()) {
            throw new UserNotFoundException();
        }

        if (listUserFavorByUser.isEmpty()) {
            throw new UserFavorNotFoundException();
        }
        return listUserFavorByUser.stream().map(userFavorMapper::toListDto).toList();
    }

    /**
     * Get a list of user favour from the owner's user and available
     *
     * @param userId the user identifier
     * @return a list of user favour from user ID and available true
     */
    @Override
    public List<UserFavorDTO> findAllUserFavourIsActivated(long userId) {
        List<UserFavor> userFavorDTOListAvailable =
                userFavorRepository.findAllUserFavourIsActivated(userId);
        userRepository.findById(userId).orElseThrow(UserNotFoundException::new);

        if (userFavorDTOListAvailable.isEmpty()) {
            throw new UserFavorException("The list with user favour is empty");
        }
        return userFavorDTOListAvailable.stream().map(userFavorMapper::toListDto).toList();
    }

    /**
     * Get a list of user favour from the owner's user and is not available
     *
     * @param userId the user identifier
     * @return a list of user favour from user ID and available is false
     */
    @Override
    public List<UserFavorDTO> findAllUserFavourIsDeactivated(long userId) {
        List<UserFavor> userFavorDTOListNotAvailable =
                userFavorRepository.findAllUserFavourIsDeactivated(userId);
        userRepository.findById(userId).orElseThrow(UserNotFoundException::new);

        if (userFavorDTOListNotAvailable.isEmpty()) {
            throw new UserFavorException("The list with user favour is empty");
        }
        return userFavorDTOListNotAvailable.stream().map(userFavorMapper::toListDto).toList();
    }

    /**
     * Create a new UserFavor.
     * It's Check if the Favor exist
     * Must be connected to create a new userFavor
     *
     * @param form the form to create a new user Favor
     * @return a new User Favor
     */
    @Override
    public UserFavorDTO createUserFavor(UserFavorCreateForm form) {
        Favor favorById = favorRepository.findById(form.favorId()).orElseThrow(FavorNotFoundException::new);

        UserFavor userFavor = userFavorMapper.fromUserFavorForm(form);

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication != null && authentication.isAuthenticated()) {
            Object principal = authentication.getPrincipal();

            if (principal instanceof UserDetails userDetails) {
                String pseudo = userDetails.getUsername();

                User user = userRepository.findByPseudo(pseudo).orElseThrow(UserNotFoundException::new);
                userFavor.setUser(user);
            }

            userFavor.setFavor(favorById);
            userFavor.setDescriptionFavor(form.descriptionFavor());
            userFavor.setPriceHourFavor(form.priceHourFavor());
            userFavor.setAvailable(form.isAvailable());

            Set<Picture> pictures = Optional.ofNullable(userFavor.getPictures())
                    .orElse(Collections.emptySet())
                    .stream()
                    .map(pictureRepository::save)
                    .collect(Collectors.toSet());

            if(pictures.isEmpty()){
                throw new PictureException("You must have a minimum picture");
            }

            userFavor.setPictures(pictures);

            userFavorRepository.save(userFavor);
        }
        return userFavorMapper.toDto(userFavor);

    }
}
