package be.brahms.TFE_RentServe.models.forms.material;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

/**
 * Record UpdateMaterialForm to update into a Material Entity
 *
 * @param nameMaterial The name of Material
 * @param category The name of category
 * @param isAvailable The boolean truth is available for user and false it's not display for user
 */
public record MaterialUpdateFormDTO(
    @NotBlank String nameMaterial, @NotNull String category, @NotNull Boolean isAvailable) {}
