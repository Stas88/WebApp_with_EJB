package Controller.actions.Dept;

import Controller.actions.ActionDispatcher.IAction;
import javax.servlet.http.*;
import Model.DAO.*;

/**
 * Sorting departments action
 * @author sikorskyi
 */
public class SortDepts implements IAction {
    
    @Override
    public String perform(HttpServletRequest request, HttpServletResponse response) throws ModelException {
        String sortDeptsBy = (String)request.getParameter(Constants.DEPT_SORT);
        request.getSession().setAttribute(Constants.DEPT_LIST, DAOFactory.getModel(ModelType.Local).getSortedDepts(sortDeptsBy));
        return  Constants.REDIRECT_DEPT_TABLE;
    }
}
