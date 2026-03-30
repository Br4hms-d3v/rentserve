package be.brahms.TFE_RentServe.repositories;

import be.brahms.TFE_RentServe.models.entities.Material;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repository for managing Material entity.
 * Provides basic CRUD and more
 */
@Repository
public interface MaterialRepository extends JpaRepository<Material, Long> {

    /**
     * The method check if the name of material exist
     * True if the name of material exist
     * False if then name of material doesn't exist
     *
     * @param nameMaterial The name of material
     * @return a boolean
     */
    Boolean existsMaterialByNameMaterial(String nameMaterial);

    /**
     * Make a list of materials only by group on category name
     *
     * @param categoryName the name of category
     * @return a list of materials from the same name of category
     */
    @Query("SELECT m FROM Material m JOIN m.category c WHERE c.nameCategory = :categoryName")
    List<Material> findMaterialsByNameCategory(@Param("categoryName") String categoryName);

}
