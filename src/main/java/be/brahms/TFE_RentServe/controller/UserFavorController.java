package be.brahms.TFE_RentServe.controller;

import be.brahms.TFE_RentServe.hateoas.userFavor.UserFavorAssembler;
import be.brahms.TFE_RentServe.models.dtos.userFavor.UserFavorByIdDTO;
import be.brahms.TFE_RentServe.models.dtos.userFavor.UserFavorDTO;
import be.brahms.TFE_RentServe.services.UserFavorService;
import java.util.List;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user-favor/")
public class UserFavorController {

  private final UserFavorService userFavorService;
  private final UserFavorAssembler userFavorAssembler;

  public UserFavorController(
      UserFavorService userFavorService, UserFavorAssembler userFavorAssembler) {
    this.userFavorService = userFavorService;
    this.userFavorAssembler = userFavorAssembler;
  }

  /**
   * Get a list of all users favour
   *
   * @return a list of users favour
   */
  @GetMapping("list")
  public ResponseEntity<CollectionModel<UserFavorDTO>> getAllUserFavour() {
    List<UserFavorDTO> userFavour = userFavorService.findAllUserFavour();
    CollectionModel<UserFavorDTO> userFavorDTOCollectionModel =
        userFavorAssembler.toCollectionModel(userFavour);
    return ResponseEntity.ok().body(userFavorDTOCollectionModel);
  }

  /**
   * Get a list of all users favour
   *
   * @param favorId the identifier favor
   * @return a list of users favour grouped by favor ID
   */
  @GetMapping("list/{favorId}")
  public ResponseEntity<CollectionModel<UserFavorDTO>> getUserFavourByFavorId(
      @PathVariable long favorId) {
    List<UserFavorDTO> userFavor = userFavorService.findAllUserFavourByFavorId(favorId);
    CollectionModel<UserFavorDTO> userFavorDTOCollectionModel =
        userFavorAssembler.toCollectionModel(userFavor);
    return ResponseEntity.ok().body(userFavorDTOCollectionModel);
  }

  /**
   * Get a detail about the user favor by ID
   *
   * @param id the identifier of User favor
   * @return a detail about the user favor
   */
  @GetMapping("{id}")
  public ResponseEntity<EntityModel<UserFavorByIdDTO>> getUserFavorById(@PathVariable long id) {
    UserFavorByIdDTO userFavorId = userFavorService.findUserFavorById(id);
    return ResponseEntity.ok().body(userFavorAssembler.toIdModel(userFavorId));
  }

  /**
   * Get a list of user favor grouped by User
   *
   * @param id the identifier from User
   * @return a list of user favor by user id
   */
  @GetMapping("user/{id}")
  public ResponseEntity<CollectionModel<UserFavorDTO>> getUserFavorByUserId(@PathVariable long id) {
    List<UserFavorDTO> userFavorByUserId = userFavorService.findAllUserFavourByUserId(id);
    CollectionModel<UserFavorDTO> userFavorDTOCollectionModel =
        userFavorAssembler.toCollectionModel(userFavorByUserId);
    return ResponseEntity.ok().body(userFavorDTOCollectionModel);
  }
}
