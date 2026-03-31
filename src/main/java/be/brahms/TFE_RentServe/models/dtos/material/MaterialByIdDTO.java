package be.brahms.TFE_RentServe.models.dtos.material;

/**
 * A Dto (Data Transfer Object) for material information
 * It contains simple data about the material and his details
 *
 * @param id           The unique identifier
 * @param nameMaterial The name of material
 * @param isAvailable  The favor is available
 * @param nameCategory The name of category
 */
public record MaterialByIdDTO(
        Long id,
        String nameMaterial,
        String nameCategory,
        boolean isAvailable
        ) {
}
