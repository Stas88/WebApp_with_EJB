package Controller.actions.Emp;

import Controller.actions.ActionDispatcher.IAction;
import Model.DAO.*;
import javax.servlet.http.*;
import Model.DAO.DAOFactory;

/**
 * Removing employee action
 * @author Admin
 */
public class RemoveEmpAction implements IAction{
    
    @Override
    public String perform(HttpServletRequest request, HttpServletResponse response) throws ModelException {
        HttpSession session = request.getSession();
        int empno = Integer.valueOf(request.getParameter(Constants.EMPNO));
        DAOFactory.getModel(ModelType.Local).removeEmp(empno);
        session.setAttribute(Constants.EMP_LIST, DAOFactory.getModel(ModelType.Local).getEmpList());
        session.setAttribute(Constants.DEPT_LIST, DAOFactory.getModel(ModelType.Local).getDeptList());
        return  Constants.REDIRECT_EMP_TABLE;
    }
}
