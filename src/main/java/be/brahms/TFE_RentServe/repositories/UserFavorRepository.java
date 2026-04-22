package be.brahms.TFE_RentServe.repositories;

import be.brahms.TFE_RentServe.models.entities.UserFavor;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * Repository for managing User Favor entity. Provides basic CRUD operations and more Using
 * JpaRepository
 */
@Repository
public interface UserFavorRepository extends JpaRepository<UserFavor, Long> {

  /**
   * Find all users favour by favor ID
   *
   * @param favorId the identifier favor
   * @return a list of user favor grouped by id favor
   */
  @Query("SELECT uf FROM UserFavor uf WHERE uf.favor.id = :favorId AND uf.isAvailable")
  List<UserFavor> findAllUserFavourByFavorId(@Param("favorId") long favorId);
}
