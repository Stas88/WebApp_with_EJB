package Controller.actions.Dept;

import Controller.actions.ActionDispatcher.IAction;
import javax.servlet.http.*;
import Model.DAO.*;

/**
 * Action of removing department
 * @author sikorskyi
 */
public class RemoveDeptAction implements IAction {
    
    @Override
    public String perform(HttpServletRequest request, HttpServletResponse response) throws ModelException  {
        int deptno = Integer.valueOf(request.getParameter(Constants.DEPTNO));
        DAOFactory.getModel(ModelType.Local).removeDept(deptno);
        request.getSession().setAttribute(Constants.DEPT_LIST, DAOFactory.getModel(ModelType.Local).getDeptList());
        return  Constants.REDIRECT_DEPT_TABLE;
    }
}
