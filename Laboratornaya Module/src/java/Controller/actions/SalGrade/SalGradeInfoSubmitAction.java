package Controller.actions.SalGrade;

import Controller.actions.ActionDispatcher.IAction;
import Model.DAO.Constants;
import javax.servlet.http.*;
/**
 * Submitting of action of seeing salary grade`s information
 * @author sikorskyi
 */
public class SalGradeInfoSubmitAction implements IAction  {
    
    @Override
    public String perform(HttpServletRequest request, HttpServletResponse response) {
        request.getSession().removeAttribute(Constants.SALGRADE);
        return Constants.REDIRECT_SALGRADE_TABLE;
    }
    
}
