package be.brahms.TFE_RentServe.services.impl;

import be.brahms.TFE_RentServe.exceptions.category.CategoryNotEmptyException;
import be.brahms.TFE_RentServe.exceptions.category.CategoryNotExistingException;
import be.brahms.TFE_RentServe.exceptions.database.DatabaseConstraintException;
import be.brahms.TFE_RentServe.exceptions.material.MaterialAlreadyExistingException;
import be.brahms.TFE_RentServe.exceptions.material.MaterialException;
import be.brahms.TFE_RentServe.exceptions.material.MaterialNotEmptyException;
import be.brahms.TFE_RentServe.exceptions.material.MaterialNotFoundException;
import be.brahms.TFE_RentServe.mappers.MaterialMapper;
import be.brahms.TFE_RentServe.models.dtos.material.MaterialByIdDTO;
import be.brahms.TFE_RentServe.models.dtos.material.MaterialDTO;
import be.brahms.TFE_RentServe.models.entities.Category;
import be.brahms.TFE_RentServe.models.entities.Material;
import be.brahms.TFE_RentServe.models.forms.material.MaterialCreateForm;
import be.brahms.TFE_RentServe.models.forms.material.MaterialUpdateFormDTO;
import be.brahms.TFE_RentServe.repositories.CategoryRepository;
import be.brahms.TFE_RentServe.repositories.MaterialRepository;
import be.brahms.TFE_RentServe.services.MaterialService;
import java.util.List;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service implementation for managing Material. Use MaterialRepository to perform database
 * operations.
 *
 * @author Brahim K
 */
@Transactional()
@Service
public class MaterialServiceImpl implements MaterialService {

  private final MaterialRepository materialRepository;
  private final CategoryRepository categoryRepository;
  private final MaterialMapper materialMapper;

  /**
   * Constructor with parameters
   *
   * @param materialRepository the materialRepository to access material data
   * @param categoryRepository the categoryRepository to access category data
   * @param materialMapper map between Form to Entity or Dto to Entity
   */
  public MaterialServiceImpl(
      MaterialRepository materialRepository,
      CategoryRepository categoryRepository,
      MaterialMapper materialMapper) {
    this.materialRepository = materialRepository;
    this.categoryRepository = categoryRepository;
    this.materialMapper = materialMapper;
  }

  /**
   * Get a list of all materials If the list is empty, send a message exception
   *
   * @return a list of materials
   */
  @Override
  public List<MaterialDTO> findAllMaterials() {
    List<Material> materials = materialRepository.findAll();

    if (materials.isEmpty()) {
      throw new MaterialException("The list is empty");
    }

    return materialMapper.toListDto(materials);
  }

  /**
   * Get material by ID If not found material send a message exception
   *
   * @param id the identifier exception
   * @return a details about material
   */
  @Override
  public MaterialByIdDTO findMaterialById(Long id) {
    Material material = materialRepository.findById(id).orElseThrow(MaterialNotFoundException::new);

    return materialMapper.toDtoById(material);
  }

  /**
   * Retrieves a list of materials grouped by name of category Check if the name category exist
   * Check if the list of materials are not empty
   *
   * @param categoryName the name of category
   * @return a list of material grouped by
   */
  @Override
  public List<MaterialDTO> findAllMaterialsByCategory(String categoryName) {
    Category categoryExist = categoryRepository.findCategoryByNameCategory(categoryName);
    List<Material> listMaterial = materialRepository.findMaterialsByNameCategory(categoryName);

    if (categoryExist == null) {
      throw new CategoryNotExistingException();
    }

    if (!categoryExist.getNameCategory().equals(categoryName)) {
      throw new CategoryNotExistingException();
    }

    if (categoryExist.getNameCategory().isEmpty() || categoryExist.getNameCategory().isBlank()) {
      throw new CategoryNotEmptyException();
    }

    if (listMaterial.isEmpty()) {
      throw new MaterialException("The list is empty");
    }

    return materialMapper.toListDto(listMaterial);
  }

  /**
   * Create a new material It Checks if a name of category is already existing It checks if a name
   * of material is already exists It checks if a name of material is not empty It map the form to
   * entity If existing send an exception message
   *
   * @param form the form to create a new material
   * @return the save material
   */
  @Override
  public MaterialDTO createMaterial(MaterialCreateForm form) {
    Material material = materialMapper.fromCreateMaterialForm(form);
    Category categoryExist = categoryRepository.findCategoryByNameCategory(form.category());
    Boolean materialExist =
        materialRepository.existsMaterialByNameMaterial(material.getNameMaterial());

    if (form.category().isEmpty() || form.category().isBlank()) {
      throw new CategoryNotEmptyException();
    }

    if (categoryExist == null) {
      throw new CategoryNotExistingException();
    }

    if (materialExist) {
      throw new MaterialAlreadyExistingException();
    }

    if (form.nameMaterial().isEmpty() || form.nameMaterial().isBlank()) {
      throw new MaterialNotEmptyException();
    }

    material.setNameMaterial(form.nameMaterial());
    material.setAvailable(form.isAvailable());
    material.setCategory(categoryExist);

    materialRepository.save(material);

    return materialMapper.toDto(material);
  }

  /**
   * Update the material
   *
   * @param id the identifier of material
   * @param form the form to edit the material
   * @return a material updated
   */
  @Override
  public MaterialDTO updateMaterial(Long id, MaterialUpdateFormDTO form) {
    Material materialId =
        materialRepository.findById(id).orElseThrow(MaterialNotFoundException::new);
    Category categoryExist = categoryRepository.findCategoryByNameCategory(form.category());

    if (categoryExist == null) {
      throw new CategoryNotExistingException();
    }

    //        if (materialId.getNameMaterial().equals(form.nameMaterial())) {
    //            throw new MaterialAlreadyExistingException();
    //        }

    if (categoryExist.getNameCategory().isEmpty() || categoryExist.getNameCategory().isBlank()) {
      throw new CategoryNotEmptyException();
    }

    if (form.nameMaterial().isEmpty() || form.nameMaterial().isBlank()) {
      throw new MaterialNotEmptyException();
    }

    materialId.setNameMaterial(form.nameMaterial());
    materialId.setAvailable(form.isAvailable());
    materialId.setCategory(categoryExist);

    materialMapper.fromUpdateMaterialForm(form, materialId);

    materialRepository.save(materialId);

    return materialMapper.toDto(materialId);
  }

  /**
   * Delete a material Check if the material exists Check if it used on DataBase
   *
   * @param id the identifier of favor
   */
  @Override
  public void deleteMaterial(Long id) {
    Material materialId =
        materialRepository.findById(id).orElseThrow(MaterialNotFoundException::new);
    try {
      materialRepository.delete(materialId);
      materialRepository.flush();
    } catch (DataIntegrityViolationException cause) {
      throw new DatabaseConstraintException(
          "Can't delete category because it is used by another database");
    }
  }
}
