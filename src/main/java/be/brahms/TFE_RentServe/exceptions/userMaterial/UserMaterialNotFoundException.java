package be.brahms.TFE_RentServe.exceptions.userMaterial;

/** Exception evoked when user material doesn't exist */
public class UserMaterialNotFoundException extends RuntimeException {
    /**
     * Make a new exception when the user material doesn't exist.
     *
     * @param message the error message
     */
    public UserMaterialNotFoundException(String message) {
        super(message);
    }

    /** This exception is used when a user favor is not found */
    public UserMaterialNotFoundException(){ super("Le material n'a pas été retrouvé");}
}
