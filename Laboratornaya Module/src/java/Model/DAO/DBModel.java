package Model.DAO;

import Model.SalGradeStorage.SalGrade;
import Model.SalGradeStorage.SalGradeHome;
import Model.SalGradeStorage.SalGradeRemote;
import Model.EmpStorage.EmpHome;
import Model.EmpStorage.EmpRemote;
import Model.EmpStorage.Emp;
import Model.DeptStorage.DeptRemote;
import Model.DeptStorage.Dept;
import Model.DeptStorage.DeptHome;
import java.util.*;
import java.io.*;
import javax.naming.*;
import org.apache.log4j.*;
import javax.ejb.*;
import Model.Util.*;
import java.rmi.RemoteException;

/**
 * DBModel class. Defines model methods of program.
 * @author Admin
 */
public class DBModel implements IModel {

    private static Logger logger = Logger.getLogger(DBModel.class);
    private Object deptObject;
    private DeptHome deptHome;
    private Object salGradeObject;
    private SalGradeHome salGradeHome;
    private Object empObject;
    private EmpHome empHome;
    private Properties props;

    /**
     * @throws ModelException - Exception that usually can be thrown in model
     */
    public DBModel() throws ModelException {
        try {
            Context context = new InitialContext();
            props = new Properties();
            props.load(new FileInputStream("Config.properties"));
            deptObject = context.lookup(props.getProperty("JNDI_DEPT"));
            salGradeObject = context.lookup(props.getProperty("JNDI_SALGRADE"));
            empObject = context.lookup(props.getProperty("JNDI_EMP"));
            deptHome = (DeptHome) javax.rmi.PortableRemoteObject.narrow(deptObject, DeptHome.class);
            empHome = (EmpHome) javax.rmi.PortableRemoteObject.narrow(empObject, EmpHome.class);
            salGradeHome = (SalGradeHome) javax.rmi.PortableRemoteObject.narrow(salGradeObject, SalGradeHome.class);
        } catch (Exception e) {
            throw new ModelException(e);
        }
    }

    //Обработка ResultSet-ов
    /**
     * @return Collection of employees
     * @param empsRempote - Remote Interfaces of EmpBean
     * @throws ModelException - Exception that usually can be thrown in model
     */
    private Collection<Emp> getEmpsFromEmpsRemote(Collection<EmpRemote> empsRemote) throws ModelException {
        Collection<Emp> res = new LinkedList();
        try {
            for (EmpRemote dr : empsRemote) {
                Emp d = new Emp();
                d.setEmpno(dr.getEmpno());
                d.setEname(dr.getEname());
                d.setJob(dr.getJob());
                d.setMgr(dr.getMgr());
                d.setHiredate(dr.getHiredate());
                d.setSal(dr.getSal());
                d.setComm(dr.getComm());
                d.setDeptno(dr.getDeptno());
                res.add(d);
            }
        } catch (java.rmi.RemoteException e) {
            logger.error(e.getMessage());
            throw new ModelException(e);
        }
        return res;
    }
    
    /**
     * @return Collection of employees
     * @param deptRempote - Remote Interfaces of DeptBean
     * @throws ModelException - Exception that usually can be thrown in model
     */
    private Collection<Dept> getDeptsFromDeptsRemote(Collection<DeptRemote> deptsRemote) throws ModelException {
        Collection<Dept> res = new LinkedList();
        try {
            for (DeptRemote dr : deptsRemote) {
                Dept d = new Dept();
                d.setDeptno(dr.getDeptno());
                d.setDname(dr.getDname());
                d.setLoc(dr.getLoc());
                res.add(d);
            }
        } catch (java.rmi.RemoteException e) {
            logger.error(e.getMessage());
            throw new ModelException(e);
        }
        return res;
    }
    
    /**
     * @return Collection of employees
     * @param salGradeRempote - Remote Interfaces of SalGradeBean
     * @throws ModelException - Exception that usually can be thrown in model
     */
    private Collection<SalGrade> getSalGradesFromSalGradesRemote(Collection<SalGradeRemote> salGradesRemote) throws ModelException {
        try {
            Collection<SalGrade> res = new ArrayList<SalGrade>();
            for (SalGradeRemote dr : salGradesRemote) {
                SalGrade d = new SalGrade();
                d.setGrade(dr.getGrade());
                d.setMinSal(dr.getMinSal());
                d.setMaxSal(dr.getMaxSal());
                res.add(d);
            }
            return res;
        } catch (java.rmi.RemoteException e) {
            logger.error(e.getMessage());
            throw new ModelException(e);
        }
    }

    //------------------------------------------------------Methods with queries----------------------------------------
    @Override
    public Collection<Emp> getParentIerarhy(int empno) throws ModelException {
        Collection<EmpRemote> empsRemote = null;
        try {
            empsRemote = empHome.findParentIerarhy(empno);
        } catch (Exception e) {
            logger.error(e.getMessage());
            throw new ModelException(e);
        }
        return getEmpsFromEmpsRemote(empsRemote);
    }

    @Override
    public Collection<Dept> getDeptList() throws ModelException {
        try {
            return getDeptsFromDeptsRemote(deptHome.findAll());
        } catch (Exception e) {
            logger.error(e.getMessage());
            throw new ModelException(e);
        }
    }

    @Override
    public Collection<SalGrade> getSalGradeList() throws ModelException {
        try {
            return getSalGradesFromSalGradesRemote(salGradeHome.findAll());
        } catch (Exception e) {
            throw new ModelException(e);
        }
    }

    @Override
    public Collection<Emp> getEmpList() throws ModelException {
        Collection<EmpRemote> col = null;
        try {
            col = empHome.findAll();
            return getEmpsFromEmpsRemote(col);
        } catch (Exception e) {
            throw new ModelException(e);
        }
    }

    @Override
    public Collection<Emp> getSortedEmps(String sortBy) throws ModelException {
        Collection<EmpRemote> empsRemote = null;
        try {
            if ("ByNameAsc".equals(sortBy)) {
                empsRemote = empHome.findSortedEmps(SortDatas.ByNameAsc);
            } else if ("ByNameDsc".equals(sortBy)) {
                empsRemote = empHome.findSortedEmps(SortDatas.ByNameDsc);
            } else if ("ByHiredateAsc".equals(sortBy)) {
                empsRemote = empHome.findSortedEmps(SortDatas.ByHiredateAsc);
            } else if ("ByHiredateDsc".equals(sortBy)) {
                empsRemote = empHome.findSortedEmps(SortDatas.ByHiredateDsc);
            } else if ("BySalAsc".equals(sortBy)) {
                empsRemote = empHome.findSortedEmps(SortDatas.BySalAsc);
            } else if ("BySalDsc".equals(sortBy)) {
                empsRemote = empHome.findSortedEmps(SortDatas.BySalDsc);
            }
            return getEmpsFromEmpsRemote(empsRemote);
        } catch (Exception e) {
            logger.error(e.getMessage());
            throw new ModelException(e);
        }
    }

    @Override
    public Collection<Dept> getSortedDepts(String sortBy) throws ModelException {
        Collection<DeptRemote> deptsRemote = null;
        try {
            if ("ByLocAsc".equals(sortBy)) {
                deptsRemote = deptHome.findSortedDepts(SortDatas.ByLocAsc);
            } else if ("ByLocDsc".equals(sortBy)) {
                deptsRemote = deptHome.findSortedDepts(SortDatas.ByLocDsc);
            }
            return getDeptsFromDeptsRemote(deptsRemote);
        } catch (Exception e) {
            throw new ModelException(e);
        }
    }

    @Override
    public Collection<SalGrade> getSortedSalGrades(String sortBy) throws ModelException {
        Collection<SalGradeRemote> salGradesRemote = null;
        try {
            if ("ByGradeAsc".equals(sortBy)) {
                salGradesRemote = salGradeHome.findSortedSalGrades(SortDatas.ByGradeAsc);
            } else if ("ByGradeDsc".equals(sortBy)) {
                salGradesRemote = salGradeHome.findSortedSalGrades(SortDatas.ByGradeDsc);
            }
            return getSalGradesFromSalGradesRemote(salGradesRemote);
        } catch (Exception e) {
            logger.error(e.getMessage());
            throw new ModelException(e);
        }
    }

    @Override
    public Collection<Emp> getIerarhy(int empno) throws ModelException {
        try {
            Collection<EmpRemote> empsRemote = empHome.findIerarhy(empno);
            return getEmpsFromEmpsRemote(empsRemote);
        } catch (Exception e) {
            logger.error(e.getMessage());
            throw new ModelException(e);
        }
    }

    @Override
    public void addEmp(Emp emp) throws ModelException {
        try {
            Validator.validate(emp);
            empHome.create(emp);
        } catch (Exception e) {
            logger.error(e.getMessage());
            throw new ModelException(e);
        }
    }

    @Override
    public void addDept(Dept dept) throws ModelException {
        try {
            deptHome.create(dept);
        } catch (Exception e) {
            logger.error(e.getMessage());
            throw new ModelException(e);
        }
    }

    @Override
    public void addSalGrade(SalGrade salGrade) throws ModelException {
        try {
            Validator.validate(salGrade);
            salGradeHome.create(salGrade);
        } catch (Exception e) {
            logger.error(e);
            throw new ModelException(e);
        } 
    }

    @Override
    public void editEmp(Emp emp) throws ModelException {
        try {
            Validator.validate(emp);
            empHome.editEmp(emp);
        } catch (Exception e) {
            logger.error(e.getMessage());
            throw new ModelException(e);
        }
    }

    @Override
    public void editDept(Dept dept) throws ModelException {
        try {
            deptHome.editDept(dept);
        } catch (RemoteException e) {
            logger.error(e.getMessage());
            throw new ModelException(e);
        }
    }

    @Override
    public void editSalGrade(SalGrade salGrade) throws ModelException {
        try {
            salGradeHome.editSalGrade(salGrade);
        } catch (RemoteException e) {
            logger.error(e.getMessage());
            throw new ModelException(e);
        }
    }

    @Override
    public void removeDept(int deptno) throws ModelException {
        try {
            deptHome.findByPrimaryKey(deptno).remove();
        } catch (Exception e) {
            logger.error(e.getMessage());
            throw new ModelException(e);
        }
    }

    @Override
    public void removeSalGrade(int grade) throws ModelException {
        try {
            salGradeHome.findByPrimaryKey(grade).remove();
        } catch (Exception e) {
            logger.error(e.getMessage());
            throw new ModelException(e);
        }
    }

    @Override
    public void removeEmp(int empno) throws ModelException {
        try {
            empHome.findByPrimaryKey(empno).remove();
        } catch (Exception e) {
            logger.error(e.getMessage());
            throw new ModelException(e);
        }
    }

    @Override
    public Dept findDept(Integer deptno) throws ModelException {
        Dept dept = new Dept();
        DeptRemote deptRemote = null;
        try {
            deptRemote = deptHome.findByPrimaryKey(deptno);
            if (deptRemote == null) {
                throw new ModelException("No Dept were found");
            }
            dept.setDeptno(deptRemote.getDeptno());
            dept.setDname(deptRemote.getDname());
            dept.setLoc(deptRemote.getLoc());
        } catch (Exception e) {
            logger.error(e.getMessage());
            throw new ModelException(e);
        }
        return dept;

    }

    @Override
    public SalGrade findSalGrade(int grade) throws ModelException {
        SalGrade salGrade = new SalGrade();
        SalGradeRemote salGradeRemote = null;
        try {
            salGradeRemote = salGradeHome.findByPrimaryKey(grade);
            if (salGradeRemote == null) {
                throw new ModelException("No SalGrade were found");
            }
            salGrade.setGrade(salGradeRemote.getGrade());
            salGrade.setMaxSal(salGradeRemote.getMaxSal());
            salGrade.setMinSal(salGradeRemote.getMinSal());
        } catch (Exception e) {
            logger.error(e.getMessage());
            throw new ModelException(e);
        }
        return salGrade;
    }

    @Override
    public Emp findEmp(int empno) throws ModelException {
        Emp emp = new Emp();
        EmpRemote empRemote = null;
        try {
            empRemote = empHome.findByPrimaryKey(empno);
            if (empRemote == null) {
                throw new ModelException("No Emp were found");
            }
            emp.setEmpno(empRemote.getEmpno());
            emp.setEname(empRemote.getEname());
            emp.setJob(empRemote.getJob());
            emp.setMgr(empRemote.getMgr());
            emp.setHiredate(empRemote.getHiredate());
            emp.setSal(empRemote.getSal());
            emp.setComm(empRemote.getComm());
            emp.setDeptno(empRemote.getDeptno());
        } catch (Exception e) {
            logger.error(e.getMessage());
            throw new ModelException(e);
        }
        return emp;
    }

    @Override
    public Collection<Dept> findDepts(String dname, String loc) throws ModelException {
        try {
            Collection<DeptRemote> deptsRemote = deptHome.findByDnameAndLoc(dname, loc);
            return getDeptsFromDeptsRemote(deptsRemote);
        } catch (Exception e) {
            logger.error(e.getMessage());
            throw new ModelException(e);
        }
    }

    @Override
    public Collection<Dept> findDeptsByDname(String dname) throws ModelException {
        try {
            Collection<DeptRemote> deptsRemote = deptHome.findByDname(dname);
            return getDeptsFromDeptsRemote(deptsRemote);
        } catch (Exception e) {
            logger.error(e.getMessage());
            throw new ModelException(e);
        }
    }

    @Override
    public Collection<Dept> findDeptsByLoc(String loc) throws ModelException {
        try {
            Collection<DeptRemote> deptsRemote = deptHome.findByLoc(loc);
            return getDeptsFromDeptsRemote(deptsRemote);
        } catch (Exception e) {
            logger.error(e.getMessage());
            throw new ModelException(e);
        }
    }

    @Override
    public Collection<SalGrade> findSalGradesByGrade(int grade) throws ModelException {
        Collection<SalGrade> salGradeCol = new ArrayList<SalGrade>();
        salGradeCol.add(findSalGrade(grade));
        return salGradeCol;
    }

    @Override
    public Collection<Emp> findEmps(String ename, double sal) throws ModelException {
        try {
            Collection<EmpRemote> empsRemote = empHome.findByEnameAndSal(ename, sal);
            return getEmpsFromEmpsRemote(empsRemote);
        } catch (Exception e) {
            logger.error(e.getMessage());
            throw new ModelException(e);
        }
    }

    @Override
    public Collection<Emp> findEmpsByEname(String ename) throws ModelException {
        try {
            Collection<EmpRemote> empsRemote = empHome.findByEname(ename);
            return getEmpsFromEmpsRemote(empsRemote);
        } catch (Exception e) {
            logger.error(e.getMessage());
            throw new ModelException(e);
        }
    }

    @Override
    public Collection<Emp> findEmpsBySal(double sal) throws ModelException {
        try {
            Collection<EmpRemote> empsRemote = empHome.findBySal(sal);
            return getEmpsFromEmpsRemote(empsRemote);
        } catch (Exception e) {
            logger.error(e.getMessage());
            throw new ModelException(e);
        }
    }
}
