package Controller.actions.Dept;

import Controller.actions.ActionDispatcher.IAction;
import javax.servlet.http.*;
import Model.DAO.*;

/** 
 * Action of adding department 
 * @author Admin
 */
public class AddDeptAction implements IAction {
    
    @Override
    public String perform(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        session.removeAttribute(Constants.DEPT);
        session.setAttribute(Constants.ACTION_DEPT, Constants.ADD);
        return  Constants.EDIT_DEPT;
    }
    
}
