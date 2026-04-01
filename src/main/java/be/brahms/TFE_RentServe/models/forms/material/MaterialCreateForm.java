package be.brahms.TFE_RentServe.models.forms.material;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

/**
 * Record MaterialCreateForm to save into a new material Entity
 *
 * @param nameMaterial the name of material
 * @param category     the name of category
 * @param isAvailable  the boolean to check available material
 */
public record MaterialCreateForm(
        @NotBlank
        String nameMaterial,
        @NotNull
        String category,
        @NotNull
        Boolean isAvailable
) {
}
