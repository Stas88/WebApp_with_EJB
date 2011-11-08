
package Controller.actions.ActionDispatcher;

import javax.servlet.http.*;
import Model.DAO.*;

/**
 * interface of Action
 * @author Admin
 */
public interface IAction {
    
    /**
     * 
     * @param request HttpServletRequest request
     * @param response HttpServletResponse response
     * @return String
     * @throws ModelException
     */
    public String perform(HttpServletRequest request, HttpServletResponse response) throws ModelException ;
}
