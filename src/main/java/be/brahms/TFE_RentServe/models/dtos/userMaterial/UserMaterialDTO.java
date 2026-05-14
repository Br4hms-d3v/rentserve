package be.brahms.TFE_RentServe.models.dtos.userMaterial;

import java.math.BigDecimal;

/**
 * Data Transfer Object for a UserMaterial. This Record is used to transfer data between the client
 * and server
 *
 * @param id the unique identifier
 * @param priceHourMaterial the price to rent per hour
 * @param isAvailable the availability of material
 * @param picture the first picture of material
 */
public record UserMaterialDTO(
    Long id, BigDecimal priceHourMaterial, Boolean isAvailable, String picture) {}
