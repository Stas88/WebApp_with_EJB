package Controller.actions.Emp;

import Controller.actions.ActionDispatcher.IAction;
import javax.servlet.http.*;
import Model.DAO.Constants;

/**
 * Action of searching employees
 * @author Admin
 */
public class FindEmp implements IAction {
    
    @Override
    public String perform(HttpServletRequest request, HttpServletResponse response) {
        return  Constants.FIND_EMP;
    }
    
}
