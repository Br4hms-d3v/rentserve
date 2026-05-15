package be.brahms.TFE_RentServe.services.impl;

import be.brahms.TFE_RentServe.exceptions.user.UserNotFoundException;
import be.brahms.TFE_RentServe.exceptions.userMaterial.UserMaterialEmptyException;
import be.brahms.TFE_RentServe.exceptions.userMaterial.UserMaterialException;
import be.brahms.TFE_RentServe.exceptions.userMaterial.UserMaterialNotFoundException;
import be.brahms.TFE_RentServe.mappers.UserMaterialMapper;
import be.brahms.TFE_RentServe.models.dtos.userMaterial.UserMaterialByIdDTO;
import be.brahms.TFE_RentServe.models.dtos.userMaterial.UserMaterialDTO;
import be.brahms.TFE_RentServe.models.entities.UserMaterial;
import be.brahms.TFE_RentServe.repositories.UserMaterialRepository;
import be.brahms.TFE_RentServe.repositories.UserRepository;
import be.brahms.TFE_RentServe.services.UserMaterialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service implementation for managing UserMaterial. Uses UserMaterialRepository to perform database
 * operations uses UserMaterialMapper to map between form to entity or dto to entity
 */
@Service
public class UserMaterialServiceImpl implements UserMaterialService {

  private final UserMaterialRepository userMaterialRepository;
  private final UserMaterialMapper userMaterialMapper;
  private final UserRepository userRepository;

  /**
   * Constructor with parameters
   *
   * @param userMaterialRepository the userMaterialRepo to access userMaterial data
   * @param userMaterialMapper map between from userMaterial to entity or dto to entity
   */
  @Autowired
  public UserMaterialServiceImpl(
      UserMaterialRepository userMaterialRepository,
      UserMaterialMapper userMaterialMapper,
      UserRepository userRepository) {
    this.userMaterialRepository = userMaterialRepository;
    this.userMaterialMapper = userMaterialMapper;
    this.userRepository = userRepository;
  }

  /**
   * Get a list of all users material If list is empty, send an exception
   *
   * @return a list of user material
   */
  @Override
  public List<UserMaterialDTO> findAllUserMaterials() {
    List<UserMaterial> listUserMaterials = userMaterialRepository.findAll();

    if (listUserMaterials.isEmpty()) {
      throw new UserMaterialEmptyException();
    }

    return listUserMaterials.stream().map(userMaterialMapper::toListDto).toList();
  }

  /**
   * Get a list of user material from the owner's user and available
   *
   * @param userId the user identifier
   * @return a list of user material from user ID and available true
   */
  @Override
  public List<UserMaterialDTO> findAllUserMaterialIsActivated(long userId) {
    List<UserMaterial> userMaterialListAvailable =
        userMaterialRepository.findAllUserMaterialIsActivated(userId);

    userRepository.findById(userId).orElseThrow(UserNotFoundException::new);

    if (userMaterialListAvailable.isEmpty()) {
      throw new UserMaterialException("The list with user material is empty");
    }
    return userMaterialListAvailable.stream().map(userMaterialMapper::toListDto).toList();
  }

  /**
   * Get a list of user material from the owner's user and is not available
   *
   * @param userId the user identifier
   * @return a list of user material from user ID and available is false
   */
  @Override
  public List<UserMaterialDTO> findAllUserMaterialIsDeactivated(long userId) {
    List<UserMaterial> userMaterialListNotAvailable =
        userMaterialRepository.findAllUserMaterialIsDeactivated(userId);

    userRepository.findById(userId).orElseThrow(UserNotFoundException::new);

    if (userMaterialListNotAvailable.isEmpty()) {
      throw new UserMaterialException("The list with user material is empty");
    }
    return userMaterialListNotAvailable.stream().map(userMaterialMapper::toListDto).toList();
  }

  /**
   * Get a user material by id
   *
   * @param id the identifier of user material
   * @return a detail user material
   */
  @Override
  public UserMaterialByIdDTO findUserMaterialById(long id) {
    UserMaterial userMaterial = userMaterialRepository.findById(id).orElseThrow(UserMaterialNotFoundException::new);

    return userMaterialMapper.toIdDto(userMaterial);
  }
}
