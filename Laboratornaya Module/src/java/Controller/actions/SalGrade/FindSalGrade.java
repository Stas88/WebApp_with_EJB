package Controller.actions.SalGrade;

/**
 *
 * @author sikorskyi
 */
import Controller.actions.ActionDispatcher.IAction;
import javax.servlet.http.*;
import Model.DAO.*;

/**
 * Action of searching salary grade
 * @author Admin
 */
public class FindSalGrade implements IAction {
    
    @Override
    public String perform(HttpServletRequest request, HttpServletResponse response) {
        return  Constants.FIND_SALGRADE;
    }
    
}
