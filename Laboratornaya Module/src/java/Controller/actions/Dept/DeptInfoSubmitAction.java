package Controller.actions.Dept;

import Controller.actions.ActionDispatcher.IAction;
import javax.servlet.http.*;
import Model.DAO.*;

/**
 * Seeing information about department action
 * @author Admin
 */
public class DeptInfoSubmitAction implements IAction{
    
    @Override
    public String perform(HttpServletRequest request, HttpServletResponse response) {
        request.getSession().removeAttribute(Constants.DEPT);
        return Constants.REDIRECT_DEPT_TABLE;
    }
    
}
