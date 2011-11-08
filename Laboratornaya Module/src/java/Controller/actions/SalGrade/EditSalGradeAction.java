package Controller.actions.SalGrade;

import Controller.actions.ActionDispatcher.IAction;
import javax.servlet.http.*;
import Model.DAO.*;

/**
 * Action of editing salary grade
 * @author sikorskyi
 */
public class EditSalGradeAction implements IAction {
    
    @Override
    public String perform(HttpServletRequest request, HttpServletResponse response) throws ModelException {
        HttpSession session = request.getSession();
        int grade = Integer.valueOf(request.getParameter(Constants.GRADE));
        session.setAttribute(Constants.SALGRADE,DAOFactory.getModel(ModelType.Local).findSalGrade(grade));
        session.setAttribute(Constants.ACTION_SALGRADE, Constants.EDIT);
        return Constants.EDIT_SALGRADE;
    }
}
