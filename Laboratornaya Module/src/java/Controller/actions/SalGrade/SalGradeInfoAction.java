package Controller.actions.SalGrade;

import Controller.actions.ActionDispatcher.IAction;
import javax.servlet.http.*;
import Model.DAO.*;

/**
 * Seeing information about salary grade
 * @author sikorskyi
 */
public class SalGradeInfoAction implements IAction {

    @Override
    public String perform(HttpServletRequest request, HttpServletResponse response) throws ModelException {
        int grade = Integer.valueOf(request.getParameter(Constants.GRADE));
        request.getSession().setAttribute(Constants.SALGRADE, DAOFactory.getModel(ModelType.Local).findSalGrade(grade));
        return Constants.SALGRADE_INFO;
    }
}
