package Model.Util;

/**
 * SQL Commands
 * @author sikorskyi
 */
public class Commands {
    
    public static final String GET_ALL_DEPTS = "SELECT * FROM Dept";
    public static final String GET_ALL_EMPS = "SELECT *  FROM emp WHERE empno != 1 START WITH empno = 1 CONNECT BY PRIOR empno = mgr";
    public static final String GET_ALL_SALGRADES = "SELECT * FROM salgrade";
    public static final String GET_DEPT_BY_DEPTNO = "SELECT * FROM dept WHERE deptno = ?";
    public static final String GET_EMP_BY_EMPNO = "SELECT * FROM emp WHERE empno = ?";
    public static final String GET_SALGRADE_BY_GRADE = "SELECT * FROM salgrade  WHERE grade = ?";
    public static final String DELETE_DEPT_BY_DEPTNO = "Delete FROM dept WHERE deptno = ?";
    public static final String DELETE_EMP_BY_EMPNO = "Delete FROM emp  WHERE empno = ?";
    public static final String DELETE_SALGRADE_BY_GRADE = "Delete FROM SALGRADE  WHERE grade = ?";
    public static final String INSERT_IN_DEPT = "Insert into dept VALUES (dept_sequence.nextval, ?, ?)";
    public static final String INSERT_IN_SALGRADE = "Insert into salgrade VALUES (salgrade_sequence.nextval, ?, ?)";
    public static final String INSERT_IN_EMP = "Insert into emp VALUES (emp_sequence.nextval,?,?,?,?,?,?,?)";
    public static final String UPDATE_DEPT = "UPDATE dept SET dname=?, loc=? WHERE deptno=?";
    public static final String UPDATE_SALGRADE = "UPDATE salgrade SET hisal=?, minsal=? WHERE grade=?";
    public static final String UPDATE_EMP = "UPDATE emp SET ename=?, job=?, mgr=?, hiredate=?, sal=?, comm=?, deptno=?  WHERE empno= ?";
    public static final String GET_EMP_IERARHY = "SELECT * FROM emp WHERE empno != ? START WITH empno = ? CONNECT BY PRIOR empno = mgr";
    public static final String GET_EMP_PARENT_IERARHY = "SELECT *  FROM emp START WITH empno = ? CONNECT BY PRIOR mgr = empno";
    public static final String GET_ALL_EMPS_NAME = "SELECT * FROM Emp order by ename";
    public static final String GET_ALL_EMPS_SAL = "SELECT * FROM Emp order by sal";
    public static final String GET_ALL_EMPS_HIREDATE = "SELECT * FROM Emp order by hiredate";
    public static final String GET_ALL_DEPTS_LOC = "SELECT * FROM Dept order by loc";
    public static final String GET_ALL_SALGRADES_GRADE = "SELECT * FROM Salgrade order by grade";
    public static final String FIND_DEPTS_DNAME_LOC = "SELECT * FROM dept where dname = ? and loc = ?";
    public static final String FIND_DEPTS_DNAME = "SELECT * FROM dept where dname = ?";
    public static final String FIND_DEPTS_LOC = "SELECT * FROM dept where loc  = ?";
    public static final String FIND_EMPS_ENAME_SAL = "SELECT * FROM emp where ename =  ? and SAL = ?";
    public static final String FIND_EMPS_ENAME = "SELECT * FROM emp where ename =  ? ";
    public static final String FIND_EMPS_SAL = "SELECT * FROM emp where sal =  ? ";
    public static final String GET_DEPT_ID_BY_DNAME = "SELECT deptno from dept where dname = ?";
    public static final String GET_EMP_ID_BY_ENAME = "SELECT empno from emp where ename = ?";
    public static final String GET_SALGRADE_ID_BY_MINSAL_AND_MAXSAL = "SELECT grade from salgrade where minsal = ? and hisal = ? ";
}
