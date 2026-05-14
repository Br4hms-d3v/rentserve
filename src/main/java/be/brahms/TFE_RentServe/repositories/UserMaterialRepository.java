package be.brahms.TFE_RentServe.repositories;

import be.brahms.TFE_RentServe.models.entities.UserMaterial;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository for managing User Material entity. Provides basic CRUD operations and more Using
 * JpaRepository
 */
@Repository
public interface UserMaterialRepository extends JpaRepository<UserMaterial, Long> {}
