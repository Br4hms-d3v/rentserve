package be.brahms.TFE_RentServe.controller;

import be.brahms.TFE_RentServe.hateoas.userFavor.UserFavorAssembler;
import be.brahms.TFE_RentServe.models.dtos.userFavor.UserFavorDTO;
import be.brahms.TFE_RentServe.services.UserFavorService;
import java.util.List;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
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
    return ResponseEntity.ok().body(CollectionModel.of(userFavour));
  }
}
