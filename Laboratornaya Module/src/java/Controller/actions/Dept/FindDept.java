package Controller.actions.Dept;

import Controller.actions.ActionDispatcher.IAction;
import javax.servlet.http.*;
import Model.DAO.*;

/**
 * Action of searching of department(s)
 * @author Admin
 */
public class FindDept implements IAction {
    
    @Override
    public String perform(HttpServletRequest request, HttpServletResponse response) {
        return  Constants.FIND_DEPT;
    }
    
}
