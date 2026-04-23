package be.brahms.TFE_RentServe.models.dtos.userFavor;

import be.brahms.TFE_RentServe.models.dtos.favor.FavorNameDTO;
import be.brahms.TFE_RentServe.models.dtos.user.UserPseudoDTO;
import java.math.BigDecimal;
import java.util.List;

/**
 * UserFavorByIdDTO is a data transfer object for user favor with more details information
 *
 * @param id The identifier id
 * @param descriptionFavor the description about the favor
 * @param priceHourFavor the price per hour
 * @param isAvailable the favor is available
 * @param pictures The list of pictures
 * @param user the owner user
 * @param favor the name of category's favor
 */
public record UserFavorByIdDTO(
    Long id,
    String descriptionFavor,
    BigDecimal priceHourFavor,
    Boolean isAvailable,
    List<String> pictures,
    UserPseudoDTO user,
    FavorNameDTO favor) {}
