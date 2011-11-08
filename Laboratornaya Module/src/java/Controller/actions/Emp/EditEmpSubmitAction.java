package Controller.actions.Emp;

import Model.EmpStorage.Emp;
import Controller.actions.ActionDispatcher.IAction;
import javax.servlet.http.*;
import java.util.*;
import java.text.*;
import Model.DAO.*;
import org.apache.commons.lang3.*;

/**
 * Submitting action of editing employee
 * @author Admin
 */
public class EditEmpSubmitAction implements IAction {

    @Override
    public String perform(HttpServletRequest request, HttpServletResponse response) throws ModelException {
        HttpSession session = request.getSession();
        if (StringUtils.isEmpty(request.getParameter(Constants.EMPNO))) {
            Emp emp = createEmpWithoutEmpno(request);
            DAOFactory.getModel(ModelType.Local).addEmp(emp);
            session.setAttribute(Constants.EMP_LIST, DAOFactory.getModel(ModelType.Local).getEmpList());
            session.setAttribute(Constants.DEPT_LIST, DAOFactory.getModel(ModelType.Local).getDeptList());
            session.removeAttribute(Constants.EMP);
        } else {
            Emp emp = createEmp(request);
            DAOFactory.getModel(ModelType.Local).editEmp(emp);
            session.removeAttribute(Constants.EMP);
            session.setAttribute(Constants.EMP_LIST, DAOFactory.getModel(ModelType.Local).getEmpList());
            session.setAttribute(Constants.DEPT_LIST, DAOFactory.getModel(ModelType.Local).getDeptList());
        }
        return Constants.REDIRECT_EMP_TABLE;
    }

    private Emp createEmp(HttpServletRequest request) throws ModelException {
        Emp emp = null;
        try {
            Integer mgr;
            int empno = Integer.valueOf(request.getParameter(Constants.EMPNO));
            String name = request.getParameter(Constants.ENAME);
            String job = request.getParameter(Constants.JOB);
            String mgrString = request.getParameter(Constants.MGR);
            if (StringUtils.isEmpty(mgrString)) {
                mgr = 1;
            } else {
                mgr = Integer.valueOf(mgrString);
            }
            Date hiredate = new SimpleDateFormat("yyyy-MM-dd").parse(request.getParameter(Constants.HIREDATE));
            double sal = Double.valueOf(request.getParameter(Constants.SAL));
            double comm = Double.valueOf(request.getParameter(Constants.COMM));
            int deptno = Integer.valueOf(request.getParameter(Constants.DEPTNO_EMP));
            emp = new Emp(empno, name, job, mgr, hiredate, sal, comm, deptno);
        }  catch (ParseException ex) {
            throw new ModelException(ex.getMessage());
        }
         catch (NumberFormatException ex) {
            throw new ModelException(ex.getMessage());
        }
        return emp;
    }

    private Emp createEmpWithoutEmpno(HttpServletRequest request) throws ModelException {
        Emp emp = new Emp();
        try {
            emp.setEname(request.getParameter(Constants.ENAME));
            emp.setJob(request.getParameter(Constants.JOB));
            emp.setMgr(Integer.valueOf(request.getParameter(Constants.MGR)));
            emp.setHiredate(new SimpleDateFormat("dd/MM/yyyy").parse(request.getParameter(Constants.HIREDATE)));
            emp.setSal(Double.valueOf(request.getParameter(Constants.SAL)));
            emp.setComm(Double.valueOf(request.getParameter(Constants.COMM)));
            emp.setDeptno(Integer.valueOf(request.getParameter(Constants.DEPTNO_EMP)));
        } catch (ParseException ex) {
            throw new ModelException(ex.getMessage());
        }
         catch (NumberFormatException ex) {
            throw new ModelException(ex.getMessage());
        }
        return emp;
    }
}
