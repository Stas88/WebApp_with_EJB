package Controller.actions.SalGrade;

import Controller.actions.ActionDispatcher.IAction;
import javax.servlet.http.*;
import Model.DAO.Constants;

/**
 * Adding salary grade action
 * @author sikorskyi
 */
public class AddSalGradeAction implements IAction {
    
    @Override
    public String perform(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        session.removeAttribute(Constants.DEPT);
        session.setAttribute(Constants.ACTION_SALGRADE, Constants.ADD);
        return  Constants.EDIT_SALGRADE;
    }
}
