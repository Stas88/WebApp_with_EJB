package Controller.actions.Emp;

import Controller.actions.ActionDispatcher.IAction;
import javax.servlet.http.*;
import Model.DAO.*;

/**
 * Sorting employees action
 * @author sikorskyi
 */
public class SortEmps implements IAction {
    
    @Override
    public String perform(HttpServletRequest request, HttpServletResponse response) throws ModelException {
        HttpSession session = request.getSession();
        String sortEmpsBy = (String)request.getParameter(Constants.DEPT_SORT);
        session.setAttribute(Constants.EMP_LIST, DAOFactory.getModel(ModelType.Local).getSortedEmps(sortEmpsBy));
        session.setAttribute(Constants.DEPT_LIST, DAOFactory.getModel(ModelType.Local).getDeptList());
        return  Constants.REDIRECT_EMP_TABLE;
   
    }
}
