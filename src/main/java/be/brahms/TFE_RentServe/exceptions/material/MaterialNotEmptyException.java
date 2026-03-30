package be.brahms.TFE_RentServe.exceptions.material;

/**
 * Exception evoked when the material is empty
 */
public class MaterialNotEmptyException extends RuntimeException {
    /**
     * Make a new exception when the material is empty
     *
     * @param message the error message
     */
    public MaterialNotEmptyException(String message) {
        super(message);
    }

    /**
     * When we created a new material but, it's empty exception
     * Send a message for a material empty
     */
    public MaterialNotEmptyException() {
        super("The name of material must not be empty");
    }
}
