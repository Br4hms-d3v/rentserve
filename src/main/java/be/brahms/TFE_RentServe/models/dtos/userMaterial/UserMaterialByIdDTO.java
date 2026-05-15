package be.brahms.TFE_RentServe.models.dtos.userMaterial;

import be.brahms.TFE_RentServe.enums.State;
import be.brahms.TFE_RentServe.models.dtos.material.MaterialNameDTO;
import be.brahms.TFE_RentServe.models.dtos.user.UserPseudoDTO;

import java.math.BigDecimal;
import java.util.List;

/**
 * UserMaterialByIdDTO is a data transfer object for user material with more details information
 *
 * @param id The identifier id
 * @param descriptionMaterial the description about the material
 * @param priceHourMaterial the price per hour
 * @param isAvailable the material is available
 * @param pictures the list of pictures
 * @param user the owner user
 * @param material the name of material
 * @param state the name of state about the material
 */
public record UserMaterialByIdDTO(
        Long id,
        String descriptionMaterial,
        BigDecimal priceHourMaterial,
        Boolean isAvailable,
        List<String> pictures,
        UserPseudoDTO user,
        MaterialNameDTO material,
        State state
) {}
