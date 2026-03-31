package be.brahms.TFE_RentServe.hateoas.material;

import be.brahms.TFE_RentServe.controller.MaterialController;
import be.brahms.TFE_RentServe.models.dtos.material.MaterialByIdDTO;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

/**
 * MaterialByIdDTO is a class that helps to convert MaterialDto objects
 * into EntityModel objects. It creates models with links for material data.
 */
@Component
public class MaterialByIdAssembler implements RepresentationModelAssembler<MaterialByIdDTO, EntityModel<MaterialByIdDTO>> {

    /**
     * Constructor by default for MaterialByIdAssembler
     */
    public MaterialByIdAssembler() {
    }

    /**
     * Convert a MaterialByIdDto to an Entity with HATEOAS links.
     * This method adds useful links to the MaterialDto
     * get a details material
     *
     * @param material the material data to wrap
     * @return an EntityModel with the material data and HATEOAS links
     */
    @Override
    public EntityModel<MaterialByIdDTO> toModel(MaterialByIdDTO material) {
        return EntityModel.of(material,
                linkTo(methodOn(MaterialController.class).findMaterialById(material.id())).withRel("Get material by ID"),
                linkTo(methodOn(MaterialController.class).findByCategoryName(material.nameCategory())).withRel("Get material by name of category"),
                linkTo(methodOn(MaterialController.class).updateMaterial(material.id(), null)).withRel("Edit material")
        );
    }
}
