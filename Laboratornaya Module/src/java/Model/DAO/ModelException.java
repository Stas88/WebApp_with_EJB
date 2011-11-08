/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.DAO;

/**
 * @author sikorskyi
 */
public class ModelException extends Exception {

    public ModelException() {
        super();
    }

    /**
     * @param msg - message of exception 
     */
    public ModelException(String msg) {
        super(msg);
    }
    
    /**
     * 
     * @param e - Exception to be wrapped
     */
    public ModelException(Throwable e) {
        super(e);
    }
}
