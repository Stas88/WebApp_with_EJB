
package Controller.actions.Emp;

import Model.EmpStorage.Emp;
import Controller.actions.ActionDispatcher.IAction;
import javax.servlet.http.*;
import Model.DAO.*;

/**
 * Action of editing employee
 * @author Admin
 */

public class EditEmpAction implements IAction {
    
    @Override
    public String perform(HttpServletRequest request, HttpServletResponse response) throws ModelException  {
        HttpSession session = request.getSession();
        int empno = Integer.valueOf(request.getParameter(Constants.EMPNO));
        Emp emp = DAOFactory.getModel(ModelType.Local).findEmp(empno);
        session.setAttribute(Constants.EMP, emp);
        session.setAttribute(Constants.EMP_LIST, DAOFactory.getModel(ModelType.Local).getEmpList());
        session.setAttribute(Constants.DEPT_LIST, DAOFactory.getModel(ModelType.Local).getDeptList());
        session.setAttribute(Constants.ACTION_EMP, Constants.EDIT);
        return Constants.EDIT_EMP;
    }
    
}
