package be.brahms.TFE_RentServe.models.dtos.userFavor;

import java.math.BigDecimal;

/**
 * Data Transfer Object for a UserFavor This Record is used to transfer data between th client and
 * server.
 *
 * @param id the unique identifier
 * @param priceHourFavor the price to rent per hour
 * @param isAvailable the availability of favor
 * @param picture the first picture of favor
 */
public record UserFavorDTO(
    Long id, BigDecimal priceHourFavor, Boolean isAvailable, String picture) {}
