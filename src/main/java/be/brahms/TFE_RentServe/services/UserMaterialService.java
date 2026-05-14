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
}
