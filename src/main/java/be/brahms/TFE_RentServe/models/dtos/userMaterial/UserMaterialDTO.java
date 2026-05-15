package be.brahms.TFE_RentServe.models.dtos.userMaterial;

import be.brahms.TFE_RentServe.models.dtos.material.MaterialNameDTO;

import java.math.BigDecimal;

/**
 * Data Transfer Object for a UserMaterial. This Record is used to transfer data between the client
 * and server
 *
 * @param id the unique identifier
 * @param nameMaterial the name of material
 * @param priceHourMaterial the price to rent per hour
 * @param isAvailable the availability of material
 * @param picture the first picture of material
 */
public record UserMaterialDTO(
        Long id, MaterialNameDTO nameMaterial, BigDecimal priceHourMaterial, Boolean isAvailable, String picture) {}
