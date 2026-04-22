package be.brahms.TFE_RentServe.hateoas.userFavor;

import be.brahms.TFE_RentServe.models.dtos.userFavor.UserFavorDTO;
import be.brahms.TFE_RentServe.models.entities.UserFavor;
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
    implements RepresentationModelAssembler<UserFavor, EntityModel<UserFavorDTO>> {
  @Override
  public EntityModel<UserFavorDTO> toModel(UserFavor entity) {
    return null;
  }

  @Override
  public CollectionModel<EntityModel<UserFavorDTO>> toCollectionModel(
      Iterable<? extends UserFavor> entities) {
    return RepresentationModelAssembler.super.toCollectionModel(entities);
  }
}
