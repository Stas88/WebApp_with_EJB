package Controller.actions.Dept;

import Controller.actions.ActionDispatcher.IAction;
import javax.servlet.http.*;
import Model.DAO.*;

/**
 * Action of viewing departments
 * @author Admin
 */
public class ViewDeptAction implements IAction {
    
    @Override
    public String perform(HttpServletRequest request, HttpServletResponse response) throws ModelException {
        HttpSession session = request.getSession();
        session.setAttribute(Constants.DEPT_LIST, DAOFactory.getModel(ModelType.Local).getDeptList());
        return Constants.REDIRECT_DEPT_TABLE;
    }
}
