package Controller.actions.ActionDispatcher;



import Controller.actions.Emp.*;
import Controller.actions.Dept.*;
import Controller.actions.SalGrade.*;
import java.util.*;
import Model.DAO.*;

/**
 * Factory of actions
 * @author Admin
 */
public class ActionFactory {
    
    private static Map actionMap = defaultMap();
    
    /**
     * 
     * @param actionName name of particular action 
     * @return iAction object
     */
    public static IAction create(String actionName) {
        IAction actionInstance = null;
        try {
        if ("Error.jsp".equals(actionName)) Error.class.newInstance();
        Class klass = (Class)actionMap.get(actionName);
        if (klass == null) 
               throw new RuntimeException(" was unable to find " +
               	"an action named '" + actionName + "'.");
        
        
            actionInstance = (IAction)klass.newInstance();
        } catch (Exception e) {
            e.getMessage();
        }
        return actionInstance;
    }
    
    private static Map defaultMap() {
        Map map = new HashMap();
        map.put("index.html", ViewEmpsAction.class);
        map.put("ViewEmp", ViewEmpsAction.class);
        map.put("ViewDept", ViewDeptAction.class);
        map.put("ViewSalGrade", ViewSalGradeAction.class);
        map.put("AddDept", AddDeptAction.class);
        map.put("AddEmp", AddEmpAction.class);
        map.put("AddSalGrade", AddSalGradeAction.class);
        map.put("RemoveDept", RemoveDeptAction.class);
        map.put("RemoveEmp", RemoveEmpAction.class);
        map.put("RemoveSalGrade", RemoveSalGradeAction.class);
        map.put(Constants.EDIT_DEPT, EditDeptAction.class);
        map.put("EditDeptSubmit", EditDeptSubmitAction.class);
        map.put(Constants.EDIT_EMP, EditEmpAction.class);
        map.put("EditEmpSubmit", EditEmpSubmitAction.class);
        map.put(Constants.EDIT_SALGRADE, EditSalGradeAction.class);
        map.put("EditSalGradeSubmit", EditSalGradeSubmitAction.class);
        map.put(Constants.DEPT_INFO, DeptInfoAction.class);
        map.put("DeptInfoSubmit", DeptInfoSubmitAction.class);
        map.put(Constants.EMP_INFO, EmpInfoAction.class);
        map.put("EmpInfoSubmit", EmpInfoSubmitAction.class);
        map.put("SalGradeInfo", SalGradeInfoAction.class);
        map.put("SalGradeInfoSubmit", SalGradeInfoSubmitAction.class);
        map.put("Ierarhy", Ierarhy.class);
        map.put("SortEmps", SortEmps.class);
        map.put("SortDepts", SortDepts.class);
        map.put("SortSalGrades", SortSalGrades.class);
        map.put(Constants.FIND_DEPT, FindDept.class);
        map.put("FindDeptSubmit", FindDeptSubmit.class);
        map.put(Constants.FIND_EMP, FindEmp.class);
        map.put("FindEmpSubmit", FindEmpSubmit.class);
        map.put(Constants.FIND_SALGRADE, FindSalGrade.class);
        map.put("FindSalGradeSubmit", FindSalGradeSubmit.class);
        return map;
    }
    
    
}
