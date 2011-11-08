package Controller.actions.Emp;

import Controller.actions.ActionDispatcher.IAction;
import javax.servlet.http.*;
import Model.DAO.*;

/**
 * Submitting of seeing information about employee action
 * @author Admin
 */
public class EmpInfoSubmitAction implements IAction {
    
    @Override
    public String perform(HttpServletRequest request, HttpServletResponse response) {
        request.getSession().removeAttribute(Constants.EMP);
        return  Constants.REDIRECT_EMP_TABLE;
    }
}
