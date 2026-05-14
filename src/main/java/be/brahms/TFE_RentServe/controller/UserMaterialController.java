package be.brahms.TFE_RentServe.controller;

import be.brahms.TFE_RentServe.hateoas.userMaterial.UserMaterialAssembler;
import be.brahms.TFE_RentServe.models.dtos.userMaterial.UserMaterialDTO;
import be.brahms.TFE_RentServe.services.UserMaterialService;
import java.util.List;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * This controller manages user material
 *
 * <ul>
 *   <li>Get a list of user materials
 *   <li>Get a list of user materials by Material ID
 *   <li>Get a list of user materials activated
 *   <li>Get a list of user materials deactivated
 *   <li>Get details of the user materials by his ID
 *   <li>Get a list of user material by grouped user ID
 *   <li>Update a user material
 *   <li>Delete a user material by ID
 *   <li>Create a new user material
 * </ul>
 *
 * @author Brahim K
 */
@RestController
@RequestMapping("/api/user-material/")
public class UserMaterialController {

  private final UserMaterialService userMaterialService;
  private final UserMaterialAssembler userMaterialAssembler;

  /**
   * This constructor is used to inject the necessary service for handling user material related
   * request
   *
   * @param userMaterialService the service used for user material management
   * @param userMaterialAssembler the assembler used to convert User material object to into
   *     UserMaterialDto
   */
  public UserMaterialController(
      UserMaterialService userMaterialService, UserMaterialAssembler userMaterialAssembler) {
    this.userMaterialService = userMaterialService;
    this.userMaterialAssembler = userMaterialAssembler;
  }

  /**
   * Get a list of all users materials
   *
   * @return a list of users material
   */
  @GetMapping("list")
  public ResponseEntity<CollectionModel<UserMaterialDTO>> getAllUserMaterials() {
    List<UserMaterialDTO> userMaterials = userMaterialService.findAllUserMaterials();
    CollectionModel<UserMaterialDTO> userMaterialCollectionModel =
        userMaterialAssembler.toCollectionModel(userMaterials);
    return ResponseEntity.ok().body(userMaterialCollectionModel);
  }
}
