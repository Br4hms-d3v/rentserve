package be.brahms.TFE_RentServe.hateoas.userFavor;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import be.brahms.TFE_RentServe.controller.UserFavorController;
import be.brahms.TFE_RentServe.models.dtos.userFavor.UserFavorByIdDTO;
import be.brahms.TFE_RentServe.models.dtos.userFavor.UserFavorDTO;
import java.util.List;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

/**
 * UserFavorAssembler is a class that helps to convert UserFavorDTO objects into EntityModel
 * objects. It creates models with links for userFavor data.
 */
@Component
public class UserFavorAssembler
    implements RepresentationModelAssembler<UserFavorDTO, EntityModel<UserFavorDTO>> {
  @Override
  public EntityModel<UserFavorDTO> toModel(UserFavorDTO userFavor) {
    return EntityModel.of(
        userFavor,
        linkTo(methodOn(UserFavorController.class).getAllUserFavour())
            .withRel("List of users favour"),
        linkTo(methodOn(UserFavorController.class).getUserFavourByFavorId(userFavor.id()))
            .withRel("List of users favour grouped by favor ID"),
        linkTo(methodOn(UserFavorController.class).getUserFavorById(userFavor.id()))
            .withRel("User favor by id"),
        linkTo(methodOn(UserFavorController.class).createUserFavor(null))
            .withRel("Create a new user favor"));
  }

  /**
   * Convert a userFavorDto to EntityModel with HATEOAS links. This method adds useful links to the
   * UserFavor
   *
   * @param userFavour the users favour collection and HATEOAS links
   * @return a list of model with HATEOAS links
   */
  public CollectionModel<UserFavorDTO> toCollectionModel(List<UserFavorDTO> userFavour) {
    return CollectionModel.of(
        userFavour,
        linkTo(methodOn(UserFavorController.class).getAllUserFavour())
            .withRel("List of users favour"),
        linkTo(methodOn(UserFavorController.class).createUserFavor(null))
            .withRel("Create a new user favor"));
  }

  /**
   * Convert a userFavorByIdDto to EntityModel with HATEOAS links. This method adds useful links to
   * the UserFavorID
   *
   * @param userFavorByIdDTO the user favor entity model by ID and links
   * @return a list of model with HATEOAS links
   */
  public EntityModel<UserFavorByIdDTO> toIdModel(UserFavorByIdDTO userFavorByIdDTO) {
    return EntityModel.of(
        userFavorByIdDTO,
        linkTo(methodOn(UserFavorController.class).getUserFavorById(userFavorByIdDTO.id()))
            .withRel("user favor by id: " + userFavorByIdDTO.id()));
  }
}
