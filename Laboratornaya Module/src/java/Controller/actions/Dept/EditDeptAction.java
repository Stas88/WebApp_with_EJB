package Controller.actions.Dept;

import Controller.actions.ActionDispatcher.IAction;
import javax.servlet.http.*;
import Model.DAO.*;

/**
 * Editing department action
 * @author Admin
 */
public class EditDeptAction implements IAction {
    
    @Override
    public String perform(HttpServletRequest request, HttpServletResponse response) throws ModelException {
        HttpSession session = request.getSession();
        int deptno = Integer.valueOf(request.getParameter(Constants.DEPTNO));
        session.setAttribute(Constants.DEPT,DAOFactory.getModel(ModelType.Local).findDept(deptno));
        session.setAttribute(Constants.ACTION_DEPT, Constants.EDIT);
        return Constants.EDIT_DEPT;
    }
}
