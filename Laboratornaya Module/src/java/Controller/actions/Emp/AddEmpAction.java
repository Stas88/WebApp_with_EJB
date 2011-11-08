package Controller.actions.Emp;


import Controller.actions.ActionDispatcher.IAction;
import javax.servlet.http.*;
import Model.DAO.*;

/**
 * Action of adding employee
 * @author Admin
 */
public class AddEmpAction implements IAction{
    
    
    @Override
    public String perform(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        session.removeAttribute(Constants.EMP);
        session.setAttribute(Constants.ACTION_EMP, Constants.ADD);
        return  Constants.EDIT_EMP;
    }
}
