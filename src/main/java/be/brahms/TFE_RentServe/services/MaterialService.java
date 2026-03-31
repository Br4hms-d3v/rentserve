package be.brahms.TFE_RentServe.services;

import be.brahms.TFE_RentServe.models.dtos.material.MaterialByIdDTO;
import be.brahms.TFE_RentServe.models.dtos.material.MaterialDTO;
import be.brahms.TFE_RentServe.models.forms.material.MaterialCreateForm;
import be.brahms.TFE_RentServe.models.forms.material.MaterialUpdateFormDTO;

import java.util.List;

/**
 * Service interface for managing material.
 * Defines business operations related to entity
 */
public interface MaterialService {

    /**
     * This method get a list of materials
     *
     * @return a list of materials
     */
    List<MaterialDTO> findAllMaterials();

    /**
     * This method get a material by ID
     *
     * @param id the identifier of material
     * @return details about the material
     */
    MaterialByIdDTO findMaterialById(Long id);

    /**
     * This method get a list of material by name of category
     *
     * @param categoryName the name of category
     * @return a list of material grouped by name of category
     */
    List<MaterialDTO> findAllMaterialsByCategory(String categoryName);

    /**
     * This method saves a new material
     *
     * @param form the form to create a new material
     * @return the saved material
     */
    MaterialDTO createMaterial(MaterialCreateForm form);

    /**
     * This method edit the material
     *
     * @param id   the identifier of material
     * @param form the form to edit the material
     * @return the updated material
     */
    MaterialDTO updateMaterial(Long id, MaterialUpdateFormDTO form);
}
