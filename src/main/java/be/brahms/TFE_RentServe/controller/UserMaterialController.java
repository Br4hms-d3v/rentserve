package be.brahms.TFE_RentServe.controller;

import be.brahms.TFE_RentServe.hateoas.userMaterial.UserMaterialAssembler;
import be.brahms.TFE_RentServe.models.dtos.userMaterial.UserMaterialByIdDTO;
import be.brahms.TFE_RentServe.models.dtos.userMaterial.UserMaterialDTO;
import be.brahms.TFE_RentServe.services.UserMaterialService;

import java.util.List;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
     * @param userMaterialService   the service used for user material management
     * @param userMaterialAssembler the assembler used to convert User material object to into
     *                              UserMaterialDto
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

    /**
     * Get a list of user material activated from the user ID owner
     *
     * @param id the identifier user
     * @return a list of user material activated
     */
    @GetMapping("user/{id}/activated")
    public ResponseEntity<CollectionModel<UserMaterialDTO>> getActiveUserMaterials(
            @PathVariable long id) {
        List<UserMaterialDTO> userMaterialActivated =
                userMaterialService.findAllUserMaterialIsActivated(id);
        CollectionModel<UserMaterialDTO> userMaterialsActivated =
                userMaterialAssembler.toCollectionModel(userMaterialActivated);

        return ResponseEntity.ok().body(userMaterialsActivated);
    }

    /**
     * Get a list of user material deactivated from the user ID owner
     *
     * @param id the identifier user
     * @return a list of user material deactivated
     */
    @GetMapping("user/{id}/deactivated")
    public ResponseEntity<CollectionModel<UserMaterialDTO>> getDeactivateUserMaterials(
            @PathVariable long id) {
        List<UserMaterialDTO> userMaterialDeactivated =
                userMaterialService.findAllUserMaterialIsDeactivated(id);
        CollectionModel<UserMaterialDTO> userMaterialsDeactivated =
                userMaterialAssembler.toCollectionModel(userMaterialDeactivated);

        return ResponseEntity.ok().body(userMaterialsDeactivated);
    }

    /**
     * Get a detail about the user material by ID
     *
     * @param id the identifier of User material
     * @return a detail about the user material
     */
    @GetMapping("{id}")
    public ResponseEntity<EntityModel<UserMaterialByIdDTO>> getUserMaterialById(@PathVariable long id) {
        UserMaterialByIdDTO userMaterialByIdDTO = userMaterialService.findUserMaterialById(id);
        return ResponseEntity.ok().body(userMaterialAssembler.toIdModel(userMaterialByIdDTO));
    }
}
