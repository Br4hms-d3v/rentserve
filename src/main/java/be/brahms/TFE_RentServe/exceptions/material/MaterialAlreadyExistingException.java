package be.brahms.TFE_RentServe.exceptions.material;

/**
 * Exception evoked when the material already existing
 */
public class MaterialAlreadyExistingException extends RuntimeException {
    /**
     * This a new exception when the material is exists
     *
     * @param message the error message
     */
    public MaterialAlreadyExistingException(String message) {
        super(message);
    }

    /**
     * This exception is used when a material is already exists
     */
    public MaterialAlreadyExistingException() {
        super("The material is already existing");
    }
}
