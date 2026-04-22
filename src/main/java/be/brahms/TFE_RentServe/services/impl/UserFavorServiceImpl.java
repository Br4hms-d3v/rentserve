package be.brahms.TFE_RentServe.services.impl;

import be.brahms.TFE_RentServe.exceptions.favor.FavorNotFoundException;
import be.brahms.TFE_RentServe.exceptions.userFavor.UserFavorNotFoundException;
import be.brahms.TFE_RentServe.exceptions.userFavor.UserFavourEmptyException;
import be.brahms.TFE_RentServe.mappers.UserFavorMapper;
import be.brahms.TFE_RentServe.models.dtos.userFavor.UserFavorDTO;
import be.brahms.TFE_RentServe.models.entities.UserFavor;
import be.brahms.TFE_RentServe.repositories.UserFavorRepository;
import be.brahms.TFE_RentServe.services.UserFavorService;
import java.util.List;
import org.springframework.stereotype.Service;

/**
 * Service implementation for managing UserFavor. Uses UserFavorRepository to perform database
 * operations uses UserFavorMapper to map between form to entity or dto to entity
 */
@Service
public class UserFavorServiceImpl implements UserFavorService {

  private final UserFavorRepository userFavorRepository;
  private final UserFavorMapper userFavorMapper;

  /**
   * Constructor with parameters
   *
   * @param userFavorRepository the userFavorRepo to access userFavor data
   * @param userFavorMapper map between from userFavor to entity or dto to entity
   */
  public UserFavorServiceImpl(
      UserFavorRepository userFavorRepository, UserFavorMapper userFavorMapper) {
    this.userFavorRepository = userFavorRepository;
    this.userFavorMapper = userFavorMapper;
  }

  /**
   * Get a list of all users favour If list is empty, send an exception
   *
   * @return a list of users favour
   */
  @Override
  public List<UserFavorDTO> findAllUserFavour() {
    List<UserFavor> listUserFavour = userFavorRepository.findAll();

    if (listUserFavour.isEmpty()) {
      throw new UserFavourEmptyException();
    }
    return listUserFavour.stream().map(userFavorMapper::toListDto).toList();
  }

  @Override
  public List<UserFavorDTO> findAllUserFavourByFavorId(long favorId) {
    List<UserFavor> listUserFavour = userFavorRepository.findAllUserFavourByFavorId(favorId);

    if (!userFavorRepository.existsById(favorId)) {
      throw new UserFavorNotFoundException();
    }

    if (listUserFavour.isEmpty()) {
      throw new FavorNotFoundException();
    }
    return listUserFavour.stream().map(userFavorMapper::toListDto).toList();
  }
}
