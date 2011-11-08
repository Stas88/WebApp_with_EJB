
package Model.DAO;

/**
 *
 * @author sikorskyi
 */
public class ValidateException extends Exception {
    
    public ValidateException() {
        super();
    }

    /**
     * @param msg - message of exception 
     */
    public ValidateException(String msg) {
        super(msg);
    }
    
    /**
     * 
     * @param e - Exception to be wrapped
     */
    public ValidateException(Throwable e) {
        super(e);
    }
}
