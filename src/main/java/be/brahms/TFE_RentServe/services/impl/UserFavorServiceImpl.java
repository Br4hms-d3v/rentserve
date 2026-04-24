package be.brahms.TFE_RentServe.services.impl;

import be.brahms.TFE_RentServe.exceptions.favor.FavorNotFoundException;
import be.brahms.TFE_RentServe.exceptions.user.UserNotFoundException;
import be.brahms.TFE_RentServe.exceptions.userFavor.UserFavorException;
import be.brahms.TFE_RentServe.exceptions.userFavor.UserFavorNotFoundException;
import be.brahms.TFE_RentServe.exceptions.userFavor.UserFavourEmptyException;
import be.brahms.TFE_RentServe.mappers.UserFavorMapper;
import be.brahms.TFE_RentServe.models.dtos.userFavor.UserFavorByIdDTO;
import be.brahms.TFE_RentServe.models.dtos.userFavor.UserFavorDTO;
import be.brahms.TFE_RentServe.models.entities.UserFavor;
import be.brahms.TFE_RentServe.repositories.UserFavorRepository;
import be.brahms.TFE_RentServe.repositories.UserRepository;
import be.brahms.TFE_RentServe.services.UserFavorService;
import java.util.List;
import org.springframework.stereotype.Service;

/**
 * Service implementation for managing UserFavor. Uses UserFavorRepository to perform database
 * operations uses UserFavorMapper to map between form to entity or dto to entity
 */
@Service
public class UserFavorServiceImpl implements UserFavorService {

  private final UserFavorRepository userFavorRepository;
  private final UserFavorMapper userFavorMapper;
  private final UserRepository userRepository;

  /**
   * Constructor with parameters
   *
   * @param userFavorRepository the userFavorRepo to access userFavor data
   * @param userFavorMapper map between from userFavor to entity or dto to entity
   */
  public UserFavorServiceImpl(
      UserFavorRepository userFavorRepository,
      UserFavorMapper userFavorMapper,
      UserRepository userRepository) {
    this.userFavorRepository = userFavorRepository;
    this.userFavorMapper = userFavorMapper;
    this.userRepository = userRepository;
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
}
