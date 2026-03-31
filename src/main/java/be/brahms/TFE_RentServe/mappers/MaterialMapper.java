package be.brahms.TFE_RentServe.mappers;

import be.brahms.TFE_RentServe.models.dtos.material.MaterialByIdDTO;
import be.brahms.TFE_RentServe.models.dtos.material.MaterialDTO;
import be.brahms.TFE_RentServe.models.entities.Material;
import be.brahms.TFE_RentServe.models.forms.material.MaterialCreateForm;
import be.brahms.TFE_RentServe.models.forms.material.MaterialUpdateFormDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;

import java.util.List;

/**
 * Mapper responsible for converting material entity
 * to various material related DTOs and update from form objects
 * <p>
 * This mapper is used to handle material data transformations
 * between the domain layer and Api layer
 * </p>
 *
 * @author Brahim k
 */
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface MaterialMapper {

    // Entity to DTO

    /**
     * Convert Material to a {@code List<MaterialDto}
     *
     * @param materials the list of material entity
     * @return the list of Material Dto
     */
    List<MaterialDTO> toListDto(List<Material> materials);

    /**
     * Converts a Material entity to MaterialByIdDto
     * It takes the  category name from Material
     * and puts it into the field nameCategory in MaterialByIdDto
     *
     * @param material The material object to convert
     * @return a MaterialDto object data from Material
     */
    @Mapping(source = "category.nameCategory", target = "nameCategory")
    @Mapping(source = "available", target = "isAvailable")
    MaterialByIdDTO toDtoById(Material material);

    /**
     * Convert Material to MaterialDto
     *
     * @param material the material entity
     * @return the material Dto
     */
    MaterialDTO toDto(Material material);


    // Form to Entity

    /**
     * Convert CreateMaterialForm to a material entity.
     * Used when create a material
     *
     * @param form the material form
     * @return the material entity
     */
    @Mapping(target = "category", ignore = true)
    Material fromCreateMaterialForm(MaterialCreateForm form);

    /**
     * Update an existing Material with data from a updateMaterialForm
     * Used when update a material
     *
     * @param form     the material form
     * @param material the existing material
     */
    @Mapping(target = "category", ignore = true)
    void fromUpdateMaterialForm(MaterialUpdateFormDTO form, @MappingTarget Material material);
}
