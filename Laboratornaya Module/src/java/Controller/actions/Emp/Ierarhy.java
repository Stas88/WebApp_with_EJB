
package Controller.actions.Emp;

import Controller.actions.ActionDispatcher.IAction;
import Model.DAO.*;
import javax.servlet.http.*;
import Model.DAO.DAOFactory;

/**
 * Action of getting hierarchy of employees
 * @author sikorskyi
 */
public class Ierarhy implements IAction {
    
    @Override
    public String perform(HttpServletRequest request, HttpServletResponse response) throws ModelException {
        int empno = Integer.valueOf(request.getParameter(Constants.EMPNO));
        HttpSession session = request.getSession();
        session.setAttribute(Constants.PARENT_LIST, DAOFactory.getModel(ModelType.Local).getParentIerarhy(empno));
        session.setAttribute(Constants.EMP_LIST, DAOFactory.getModel(ModelType.Local).getIerarhy(empno));
        session.setAttribute(Constants.EMP_LIST_PLUS, DAOFactory.getModel(ModelType.Local).getEmpList());
        session.setAttribute(Constants.EMP_IERARHY, DAOFactory.getModel(ModelType.Local).findEmp(empno));
        session.setAttribute(Constants.DEPT_LIST, DAOFactory.getModel(ModelType.Local).getDeptList());
        return  Constants.REDIRECT_EMP_TABLE;
    }
}
