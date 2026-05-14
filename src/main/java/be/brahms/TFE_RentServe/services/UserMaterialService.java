package be.brahms.TFE_RentServe.services;

import be.brahms.TFE_RentServe.models.dtos.userMaterial.UserMaterialDTO;
import java.util.List;

/**
 * Service interface for managing user materials. Defines business operations related to
 * userMaterial entity
 */
public interface UserMaterialService {

  /**
   * This method get a list of all users materials
   *
   * @return list of users materials
   */
  List<UserMaterialDTO> findAllUserMaterials();

  /**
   * This method get a list of user material from user id and available
   *
   * @param userId the user identifier
   * @return a list of user material from user and is available
   */
  List<UserMaterialDTO> findAllUserMaterialIsActivated(long userId);

  /**
   * This method get a list of user material from user id and is not available
   *
   * @param userId the user identifier
   * @return a list of user material from user and is not available
   */
  List<UserMaterialDTO> findAllUserMaterialIsDeactivated(long userId);
}
