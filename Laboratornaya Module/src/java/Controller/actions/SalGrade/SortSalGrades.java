package Controller.actions.SalGrade;

import Controller.actions.ActionDispatcher.IAction;
import javax.servlet.http.*;
import Model.DAO.*;
/**
 * Sorting Salary Grade action
 * @author sikorskyi
 */
public class SortSalGrades implements IAction {
    
    @Override
    public String perform(HttpServletRequest request, HttpServletResponse response) throws ModelException {
        String sortSalGradesBy = (String)request.getParameter(Constants.DEPT_SORT);
        request.getSession().setAttribute(Constants.SALGRADE_LIST, DAOFactory.getModel(ModelType.Local).getSortedSalGrades(sortSalGradesBy));
        return Constants.REDIRECT_SALGRADE_TABLE;
   
    }
}
