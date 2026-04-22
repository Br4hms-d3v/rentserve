package be.brahms.TFE_RentServe.repositories;

import be.brahms.TFE_RentServe.models.entities.UserFavor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository for managing User Favor entity. Provides basic CRUD operations and more Using
 * JpaRepository
 */
@Repository
public interface UserFavorRepository extends JpaRepository<UserFavor, Long> {}
