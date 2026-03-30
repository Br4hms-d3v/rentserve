package be.brahms.TFE_RentServe.models.forms.material;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

/**
 * Record MaterialCreateForm to save into a new material Entity
 *
 * @param nameMaterial
 * @param category
 * @param isAvailable
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
