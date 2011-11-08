package Controller.actions.Emp;

import Controller.actions.ActionDispatcher.IAction;
import javax.servlet.http.*;
import Model.DAO.*;

/**
 * Action of seeing information about employee
 * @author Admin
 */
public class EmpInfoAction implements IAction {
    
    
    @Override
    public String perform(HttpServletRequest request, HttpServletResponse response) throws ModelException  {
        HttpSession session = request.getSession();
        int empno = Integer.valueOf(request.getParameter(Constants.EMPNO));
        session.setAttribute(Constants.EMP, DAOFactory.getModel(ModelType.Local).findEmp(empno));
        session.setAttribute(Constants.DEPT_LIST, DAOFactory.getModel(ModelType.Local).getDeptList());
        session.setAttribute(Constants.EMP_LIST, DAOFactory.getModel(ModelType.Local).getEmpList());
        return  Constants.EMP_INFO;
    }
}
