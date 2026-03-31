package be.brahms.TFE_RentServe.controller;

import be.brahms.TFE_RentServe.hateoas.material.MaterialAssembler;
import be.brahms.TFE_RentServe.hateoas.material.MaterialByIdAssembler;
import be.brahms.TFE_RentServe.models.dtos.material.MaterialByIdDTO;
import be.brahms.TFE_RentServe.models.dtos.material.MaterialDTO;
import be.brahms.TFE_RentServe.models.forms.material.MaterialCreateForm;
import be.brahms.TFE_RentServe.models.forms.material.MaterialUpdateFormDTO;
import be.brahms.TFE_RentServe.services.MaterialService;
import jakarta.validation.Valid;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * This controller manages Material
 * It has a method to display a list of materials
 *
 * @author Brahim K
 */
@RestController
@RequestMapping("/api/material")
public class MaterialController {

    private final MaterialService materialService;
    private final MaterialAssembler materialAssembler;
    private final MaterialByIdAssembler materialByIdAssembler;

    public MaterialController(MaterialService materialService, MaterialAssembler materialAssembler, MaterialByIdAssembler materialByIdAssembler) {
        this.materialService = materialService;
        this.materialAssembler = materialAssembler;
        this.materialByIdAssembler = materialByIdAssembler;
    }

    /**
     * Get a list of materials
     *
     * @return a list of materials
     */
    @GetMapping("list")
    public ResponseEntity<CollectionModel<MaterialDTO>> findAllMaterials() {
        List<MaterialDTO> materials = materialService.findAllMaterials();
        CollectionModel<MaterialDTO> modelMaterials = materialAssembler.toCollectionModel(materials);
        return ResponseEntity.ok(modelMaterials);
    }

    /**
     * Get material's data by his ID
     *
     * @param id identifier unique
     * @return data's the favor
     */
    @GetMapping("{id}")
    public ResponseEntity<EntityModel<MaterialByIdDTO>> findMaterialById(@PathVariable Long id) {
        MaterialByIdDTO materialId = materialService.findMaterialById(id);
        EntityModel<MaterialByIdDTO> modelMaterialId = materialByIdAssembler.toModel(materialId);
        return ResponseEntity.ok(modelMaterialId);
    }

    /**
     * Get a list of materials grouped by name of category
     *
     * @param nameCategory name of category
     * @return a list of materials grouped by name of category
     */
    @GetMapping("category/{nameCategory}")
    public ResponseEntity<CollectionModel<MaterialDTO>> findByCategoryName(@PathVariable String nameCategory) {
        List<MaterialDTO> materials = materialService.findAllMaterialsByCategory(nameCategory);
        CollectionModel<MaterialDTO> modelMaterials = materialAssembler.toCollectionModel(materials);
        return ResponseEntity.ok(modelMaterials);
    }

    /**
     * Create a new material
     *
     * @param form the form to create a new material
     * @return a new material
     */
    @PostMapping("new")
    public ResponseEntity<EntityModel<MaterialDTO>> createMaterial(@RequestBody @Valid MaterialCreateForm form) {
        MaterialDTO material = materialService.createMaterial(form);
        EntityModel<MaterialDTO> modelMaterial = materialAssembler.toModel(material);
        return ResponseEntity.ok(modelMaterial);
    }

    @PutMapping("edit/{id}")
    public ResponseEntity<EntityModel<MaterialDTO>> updateMaterial(@PathVariable Long id, @RequestBody @Valid MaterialUpdateFormDTO form) {
        MaterialDTO material = materialService.updateMaterial(id, form);
        EntityModel<MaterialDTO> modelMaterial = materialAssembler.toModel(material);
        return ResponseEntity.ok(modelMaterial);
    }
}
