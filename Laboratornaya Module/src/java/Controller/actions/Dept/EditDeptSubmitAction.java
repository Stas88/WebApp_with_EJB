package Controller.actions.Dept;

import Model.DeptStorage.Dept;
import Controller.actions.ActionDispatcher.IAction;
import Model.DAO.*;
import javax.servlet.http.*;
import Model.DAO.DAOFactory;
import org.apache.commons.lang3.*;

/**
 * Action of editing department
 * @author Admin
 */
public class EditDeptSubmitAction implements IAction {
    
    @Override
    public String perform(HttpServletRequest request, HttpServletResponse response) throws ModelException {
        HttpSession session = request.getSession();
        if (StringUtils.isEmpty(request.getParameter(Constants.DEPTNO))) {
            Dept dept = createDeptWithoutDeptno(request);
            DAOFactory.getModel(ModelType.Local).addDept(dept);
            session.removeAttribute(Constants.DEPT);
            session.setAttribute(Constants.DEPT_LIST, DAOFactory.getModel(ModelType.Local).getDeptList());
        }
        else {
            Dept dept = createDept(request);
            DAOFactory.getModel(ModelType.Local).editDept(dept);
            session.removeAttribute(Constants.DEPT);
            session.setAttribute(Constants.DEPT_LIST, DAOFactory.getModel(ModelType.Local).getDeptList());
        }
        return Constants.REDIRECT_DEPT_TABLE;
    }
    
    private Dept createDept(HttpServletRequest request) {
        int deptno = Integer.valueOf(request.getParameter(Constants.DEPTNO));
        String dname = request.getParameter(Constants.DNAME);
        String loc = request.getParameter(Constants.LOC);
        Dept dept = new Dept(deptno, dname, loc);
        return dept;
    }
    
    private Dept createDeptWithoutDeptno(HttpServletRequest request) {
        Dept dept = new Dept();
        dept.setDname(request.getParameter(Constants.DNAME));
        dept.setLoc(request.getParameter(Constants.LOC));
        return dept;
    }
    
}
