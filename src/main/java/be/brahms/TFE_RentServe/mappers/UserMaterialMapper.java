package be.brahms.TFE_RentServe.mappers;

import be.brahms.TFE_RentServe.models.dtos.material.MaterialNameDTO;
import be.brahms.TFE_RentServe.models.dtos.user.UserPseudoDTO;
import be.brahms.TFE_RentServe.models.dtos.userMaterial.UserMaterialByIdDTO;
import be.brahms.TFE_RentServe.models.dtos.userMaterial.UserMaterialDTO;
import be.brahms.TFE_RentServe.models.entities.Picture;
import be.brahms.TFE_RentServe.models.entities.UserMaterial;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

/**
 * Mapper responsible for converting user material entity to various userMaterial-related DTOs and
 * updating form objects
 *
 * <p>This mapper is used to handle user material data transformations between the domain layer and
 * API layer
 *
 * @author Brahim K
 */
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserMaterialMapper {
    // Entity to DTO

    /**
     * Convert a user material UserMaterialDTO Take only first picture to display
     *
     * @param userMaterial the user material entity
     * @return a user material DTO
     */
    default UserMaterialDTO toListDto(UserMaterial userMaterial) {
        String firstPicture =
                userMaterial.getPictures().stream()
                        .map(Picture::getNamePicture)
                        .findFirst()
                        .orElse("imageByDefault.jpg");

        return new UserMaterialDTO(
                userMaterial.getId(),
                userMaterial.getPriceHourMaterial(),
                userMaterial.isAvailable(),
                firstPicture);
    }

    /**
     * Convert a user material to a UserMaterialByIdDTO Get more details : - description about the material -
     * list of pictures - the price per hour - the owner user - the name of material
     *
     * @param userMaterial the user material entity
     * @return a user material details
     */
    default UserMaterialByIdDTO toIdDto(UserMaterial userMaterial) {
        List<String> pictures = userMaterial.getPictures().stream().map(Picture::getNamePicture).toList();

        // Get the pseudo from user
        UserPseudoDTO userPseudo = new UserPseudoDTO(userMaterial.getUser().getPseudo());

        // Get the name from material
        MaterialNameDTO nameMaterial = new MaterialNameDTO(userMaterial.getMaterial().getNameMaterial());

        return new UserMaterialByIdDTO(
                userMaterial.getId(),
                userMaterial.getDescriptionMaterial(),
                userMaterial.getPriceHourMaterial(),
                userMaterial.isAvailable(),
                pictures,
                userPseudo,
                nameMaterial,
                userMaterial.getStateMaterial()
        );
    }
}
