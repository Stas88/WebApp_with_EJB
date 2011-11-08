package Controller.actions.SalGrade;

import Controller.actions.ActionDispatcher.IAction;
import Model.DAO.*;
import javax.servlet.http.*;
import Model.DAO.DAOFactory;
/**
 * Action of removing salary grade from database
 * @author sikorskyi
 */
public class RemoveSalGradeAction implements IAction {
    
    @Override
    public String perform(HttpServletRequest request, HttpServletResponse response) throws ModelException {
        int grade = Integer.valueOf(request.getParameter(Constants.GRADE));
        DAOFactory.getModel(ModelType.Local).removeSalGrade(grade);
        request.getSession().setAttribute(Constants.SALGRADE_LIST, DAOFactory.getModel(ModelType.Local).getSalGradeList());
        return  Constants.REDIRECT_SALGRADE_TABLE;
    }
}
