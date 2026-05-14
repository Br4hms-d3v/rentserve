package be.brahms.TFE_RentServe.services.impl;

import be.brahms.TFE_RentServe.exceptions.userMaterial.UserMaterialEmptyException;
import be.brahms.TFE_RentServe.mappers.UserMaterialMapper;
import be.brahms.TFE_RentServe.models.dtos.userMaterial.UserMaterialDTO;
import be.brahms.TFE_RentServe.models.entities.UserMaterial;
import be.brahms.TFE_RentServe.repositories.UserMaterialRepository;
import be.brahms.TFE_RentServe.services.UserMaterialService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Service implementation for managing UserMaterial. Uses UserMaterialRepository to perform database
 * operations uses UserMaterialMapper to map between form to entity or dto to entity
 */
@Service
public class UserMaterialServiceImpl implements UserMaterialService {

  private final UserMaterialRepository userMaterialRepository;
  private final UserMaterialMapper userMaterialMapper;

  /**
   * Constructor with parameters
   *
   * @param userMaterialRepository the userMaterialRepo to access userMaterial data
   * @param userMaterialMapper map between from userMaterial to entity or dto to entity
   */
  @Autowired
  public UserMaterialServiceImpl(
      UserMaterialRepository userMaterialRepository, UserMaterialMapper userMaterialMapper) {
    this.userMaterialRepository = userMaterialRepository;
    this.userMaterialMapper = userMaterialMapper;
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
}
