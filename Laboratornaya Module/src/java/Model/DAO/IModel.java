package Model.DAO;

import Model.DeptStorage.Dept;
import Model.EmpStorage.Emp;
import Model.SalGradeStorage.SalGrade;
import java.util.*;

/**
 * @author Stas Sikroskyi 
 */
public interface IModel {
    
    /**
     * @return Collection of Employees
     * @throws ModelException 
     */
    Collection<Emp> getEmpList() throws ModelException;
    
    /**
     * 
     * @return Collection of Departments
     * @throws ModelException
     */
    Collection<Dept> getDeptList() throws ModelException;
    
    /**
     * 
     * @return Collection of SelGrades
     * @throws ModelException
     */
    Collection<SalGrade> getSalGradeList() throws ModelException;
    
    /**
     * 
     * @param empno - id of employee
     * @return Collections of employees, that represents hierarchy 
     * @throws ModelException
     */
    Collection<Emp> getIerarhy(int empno) throws ModelException;
    
    /**
     * 
     * @param empno - id of employee
     * @return Collections of employees, that represents parent hierarchy 
     * @throws ModelException
     */
    Collection<Emp> getParentIerarhy(int empno) throws ModelException;
    
    /**
     * 
     * @param sortBy - parameter of sorting
     * @return Collections of employees
     * @throws ModelException
     */
    Collection<Emp> getSortedEmps(String sortBy) throws ModelException;
    
    /**
     * 
     * @param sortBy - parameter of sorting
     * @return Collections of departments
     * @throws ModelException
     */
    Collection<Dept> getSortedDepts(String sortBy) throws ModelException;
    
    /**
     * 
     * @param dname - id of department 
     * @return Collections of departments
     * @throws ModelException
     */
    Collection<Dept> findDeptsByDname(String dname) throws ModelException;
    
    /**
     * 
     * @param sortBy - parameter of sorting
     * @return Collection of SalGrades
     * @throws ModelException
     */
    Collection<SalGrade> getSortedSalGrades(String sortBy) throws ModelException;
    
    /**
     * 
     * @param dname - Name of department
     * @param loc - Location of department
     * @return Collection of departments
     * @throws ModelException
     */
    Collection<Dept> findDepts(String dname, String loc) throws ModelException;
    
    /**
     * 
     * @param loc - Location of departments
     * @return Collection of departments
     * @throws ModelException
     */
    Collection<Dept> findDeptsByLoc(String loc) throws ModelException;
    
    /**
     * 
     * @param ename - Name of employee
     * @param sal - Salary of employee
     * @return Collection of employees
     * @throws ModelException
     */
    Collection<Emp> findEmps(String ename, double sal) throws ModelException; 
             
    /**
     * 
     * @param ename - Name of employee
     * @return Collection of employees
     * @throws ModelException
     */
    Collection<Emp> findEmpsByEname(String ename) throws ModelException;
    
    /**
     * 
     * @param sal - salary of employee
     * @return Collection of employees
     * @throws ModelException
     */
    Collection<Emp> findEmpsBySal(double sal) throws ModelException;
    
    /**
     * 
     * @param emp - Employee
     * @throws ModelException
     */
    void addEmp(Emp emp )throws ModelException ;
    
    /**
     * 
     * @param dept - Department
     * @throws ModelException
     */
    void addDept(Dept dept) throws ModelException;
    
    /**
     * 
     * @param salGrade - Salary grade
     * @throws ModelException
     */
    void addSalGrade(SalGrade salGrade) throws ModelException;
    
    /**
     * 
     * @param emp - Employee
     * @throws ModelException
     */
    void editEmp(Emp emp) throws ModelException;
    
    /**
     * 
     * @param dept - Department
     * @throws ModelException
     */
    void editDept(Dept dept) throws ModelException;
    
    /**
     * 
     * @param salGrade - Salary grade
     * @throws ModelException
     */
    void editSalGrade(SalGrade salGrade) throws ModelException;
    
    /**
     * 
     * @param index - index of department 
     * @throws ModelException
     */
    void removeDept(int index) throws ModelException;
    
    /**
     * 
     * @param index index of employee
     * @throws ModelException
     */
    void removeEmp(int index) throws ModelException;
    
    /**
     * 
     * @param grade grade from salary grades
     * @throws ModelException
     */
    void removeSalGrade(int grade) throws ModelException; 
    
    /**
     * 
     * @param deptno - id of department
     * @return id of department
     * @throws ModelException
     */
    Dept findDept(Integer deptno) throws ModelException;
    
    /**
     * 
     * @param empno id of employee
     * @return id of employee
     * @throws ModelException
     */
    Emp findEmp (int empno) throws ModelException;
    
    /**
     * 
     * @param grade from salary grades
     * @return grade`s id 
     * @throws ModelException
     */
    SalGrade findSalGrade (int grade) throws ModelException;
    
    /**
     * 
     * @param grade - grade from salary grades
     * @return grade`s id 
     * @throws ModelException
     */
    Collection<SalGrade> findSalGradesByGrade(int grade) throws ModelException;
   
}
