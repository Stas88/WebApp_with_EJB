package Model.EmpStorage;

import javax.ejb.*;
import javax.naming.*;
import javax.sql.*;
import java.sql.*;
import java.rmi.*;
import java.util.*;
import Model.Util.*;
import java.io.*;

/**
 * Emp bean class
 * @author Admin
 */
public class EmpBean extends ConnectionUtil implements EntityBean {

    private EntityContext context;
    private Integer empno;
    private String ename;
    private String job;
    private Integer mgr;
    private java.util.Date hiredate;
    private Double sal;
    private Double comm;
    private Integer deptno;
    private Connection connection = null;
    private Statement statement = null;
    private PreparedStatement preparedStatement = null;
    private ResultSet resultSet = null;
    

    /**
     * 
     * @return  id of employee
     */
    public Integer getEmpno() {
        return empno;
    }

    /**
     * 
     * @return name of employee
     */
    public String getEname() {
        return ename;
    }

    /**
     * 
     * @retur job of employee
     */
    public String getJob() {
        return job;
    }

    /**
     * 
     * @return manager`s id of employee
     */
    public Integer getMgr() {
        return mgr;
    }

    /**
     * 
     * @return hiredate of employee
     */
    public java.util.Date getHiredate() {
        return hiredate;
    }

    /**
     * 
     * @return salary of employee
     */
    public Double getSal() {
        return sal;
    }

    /**
     * 
     * @return commissions of employee
     */
    public Double getComm() {
        return comm;
    }

    /**
     * 
     * @return department`s id of employee
     */
    public Integer getDeptno() {
        return deptno;
    }

    /**
     * 
     * @return Collection of ids of employee
     * @throws FinderException
     */
    public Collection ejbFindAll() throws FinderException {
        try {
            connection = getConnection(context);
            Collection col = new ArrayList();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(Commands.GET_ALL_EMPS);
            while (resultSet.next()) {
                col.add(new Integer(resultSet.getInt(Constants.EMPNO)));
            }
            return col;
        } catch (SQLException e) {
            throw new FinderException(e.getMessage());
        } catch (NamingException e) {
            throw new FinderException(e.getMessage());
        } finally {
            try {
                close(resultSet, statement, connection);
            } catch (SQLException e) {
                throw new FinderException(e.getMessage());
            }
        }
    }

    /**
     * 
     * @param id - id of employee
     * @return id of employee
     * @throws FinderException
     */
    public Integer ejbFindByPrimaryKey(Integer id) throws FinderException {;
        try {
            connection = getConnection(context);
            preparedStatement = connection.prepareStatement(Commands.GET_EMP_BY_EMPNO);
            preparedStatement.setInt(1, id.intValue());
            resultSet = preparedStatement.executeQuery();
            if (!resultSet.next()) {
                throw new FinderException("No such emp exists");
            }
            this.deptno = id;
        } catch (SQLException e) {
            throw new FinderException(e.getMessage());
        } catch (NamingException e) {
            throw new FinderException(e.getMessage());
        } finally {
            try {
                close(resultSet, preparedStatement, connection);
            } catch (SQLException e) {
                throw new FinderException(e.getMessage());
            }
        }
        return id;
    }

    /**
     * 
     * @param ename -  name of employee
     * @return Collection of ids of employees
     * @throws FinderException
     */
    public Collection ejbFindByEname(String ename) throws FinderException {
        try {
            connection = getConnection(context);
            preparedStatement = connection.prepareStatement(Commands.FIND_EMPS_ENAME);
            preparedStatement.setString(1, ename);
            resultSet = preparedStatement.executeQuery();
            Collection list = new ArrayList();
            while (resultSet.next()) {
                list.add(new Integer(resultSet.getInt(Constants.EMPNO)));
            }
            return list;
        } catch (SQLException ex) {
            throw new FinderException("No emp was found: " + ex.getMessage());
        } catch (NamingException e) {
            throw new FinderException(e.getMessage());
        } finally {
            try {
                close(resultSet, preparedStatement, connection);
            } catch (SQLException ex) {
                throw new FinderException("No emp was found. Cannot free resources: " + ex.getMessage());
            }
        }
    }

    /**
     * 
     * @param sal salary of employees
     * @return Collection o f ids of employee
     * @throws FinderException
     */
    public Collection ejbFindBySal(Double sal) throws FinderException {
        try {
            connection = getConnection(context);
            preparedStatement = connection.prepareStatement(Commands.FIND_EMPS_SAL);
            preparedStatement.setDouble(1, sal);
            resultSet = preparedStatement.executeQuery();
            Collection list = new ArrayList();
            while (resultSet.next()) {
                list.add(new Integer(resultSet.getInt(Constants.EMPNO)));
            }
            return list;
        } catch (SQLException ex) {
            throw new FinderException("No emp was found: " + ex.getMessage());
        } catch (NamingException e) {
            throw new FinderException(e.getMessage());
        }finally {
            try {
                close(resultSet, preparedStatement, connection);
            } catch (SQLException ex) {
                throw new FinderException("No emp was found. Cannot free resources: " + ex.getMessage());
            }
        }
    }

    /**
     * 
     * @param sortBy - sorting parameter
     * @return Collection of ids of employee
     * @throws FinderException
     */
    public Collection ejbFindSortedEmps(SortDatas sortBy) throws FinderException {
        try {
            connection = getConnection(context);
            statement = connection.createStatement();
            if (sortBy.equals(SortDatas.ByNameDsc)) {
                resultSet = statement.executeQuery(Commands.GET_ALL_EMPS_NAME + " desc");
            } else if (SortDatas.ByNameAsc.equals(sortBy)) {
                resultSet = statement.executeQuery(Commands.GET_ALL_EMPS_NAME);
            } else if (SortDatas.BySalAsc.equals(sortBy)) {
                resultSet = statement.executeQuery(Commands.GET_ALL_EMPS_SAL);
            } else if (SortDatas.BySalDsc.equals(sortBy)) {
                resultSet = statement.executeQuery(Commands.GET_ALL_EMPS_SAL + " desc");
            } else if (SortDatas.ByHiredateAsc.equals(sortBy)) {
                resultSet = statement.executeQuery(Commands.GET_ALL_EMPS_HIREDATE);
            } else if (SortDatas.ByHiredateDsc.equals(sortBy)) {
                resultSet = statement.executeQuery(Commands.GET_ALL_EMPS_HIREDATE + " desc");
            }
            Collection list = new ArrayList();
            while (resultSet.next()) {
                list.add(new Integer(resultSet.getInt(Constants.EMPNO)));
            }
            return list;
        } catch (SQLException ex) {
            throw new FinderException("No emp was found: " + ex.getMessage());
        } catch (NamingException e) {
            throw new FinderException(e.getMessage());
        } finally {
            try {
                close(resultSet, statement, connection);
            } catch (SQLException ex) {
                throw new FinderException("No emp was found. Cannot free resources: " + ex.getMessage());
            }
        }
    }

    /**
     * 
     * @param ename -  name of employee
     * @param sal -salary of employee
     * @return Collection of ids of employee
     * @throws FinderException
     */
    public Collection ejbFindByEnameAndSal(String ename, Double sal) throws FinderException {
        try {
            connection = getConnection(context);
            preparedStatement = connection.prepareStatement(Commands.FIND_EMPS_ENAME_SAL);
            preparedStatement.setString(1, ename);
            preparedStatement.setDouble(2, sal);
            resultSet = preparedStatement.executeQuery();
            Collection list = new ArrayList();
            while (resultSet.next()) {
                list.add(new Integer(resultSet.getInt(Constants.EMPNO)));
            }
            return list;
        } catch (SQLException ex) {
            throw new FinderException("No emp was found: " + ex.getMessage());
        } catch (NamingException e) {
            throw new FinderException(e.getMessage());
        }finally {
            try {
                close(resultSet, preparedStatement, connection);
            } catch (SQLException ex) {
                throw new FinderException("No emp was found. Cannot free resources: " + ex.getMessage());
            }
        }
    }

    /**
     * 
     * @throws EJBException
     * @throws RemoteException
     */
    public void ejbLoad() throws EJBException, RemoteException {
        try {
            //deptno = (Integer) context.getPrimaryKey();
            connection = getConnection(context);
            preparedStatement = connection.prepareStatement(Commands.GET_EMP_BY_EMPNO);
            preparedStatement.setInt(1, this.empno);
            resultSet = preparedStatement.executeQuery();
            if (!resultSet.next()) {
                throw new NoSuchEntityException("Emp does not exist");
            }
            this.empno = resultSet.getInt(Constants.EMPNO);
            this.ename = resultSet.getString(Constants.ENAME);
            this.job = resultSet.getString(Constants.JOB);
            this.mgr = resultSet.getInt(Constants.MGR);
            this.hiredate = resultSet.getDate(Constants.HIREDATE);
            this.sal = resultSet.getDouble(Constants.SAL);
            this.comm = resultSet.getDouble(Constants.COMM);
            this.deptno = resultSet.getInt(Constants.DEPTNO_EMP);
        } catch (SQLException e) {
            throw new RemoteException(e.getMessage());
        } catch (NamingException e) {
            throw new RemoteException(e.getMessage());
        }finally {
            try {
                close(resultSet, preparedStatement, connection);
            } catch (SQLException e) {
                throw new RemoteException(e.getMessage());
            }
        }
    }

    /**
     * 
     * @throws EJBException
     * @throws RemoteException
     */
    public void ejbStore() throws EJBException, RemoteException {
    }

    /**
     * 
     * @throws EJBException
     * @throws RemoteException
     */
    public void ejbActivate() throws EJBException, RemoteException {
        this.empno = (Integer) context.getPrimaryKey();
    }

    /**
     * 
     * @throws EJBException
     * @throws RemoteException
     */
    public void ejbPassivate() throws EJBException, RemoteException {
        empno = null;
    }

    /**
     * 
     * @param context - context in which bean works
     * @throws EJBException
     * @throws RemoteException
     */
    public void setEntityContext(EntityContext context) throws EJBException, RemoteException {
        this.context = context;
    }

    /**
     * 
     * @throws EJBException
     * @throws RemoteException
     */
    public void unsetEntityContext() throws EJBException, RemoteException {
        this.context = null;
    }

    /**
     * 
     * @throws RemoteException
     * @throws EJBException
     */
    public void ejbRemove() throws RemoteException, EJBException {
        try {
            connection = getConnection(context);
            preparedStatement = connection.prepareStatement(Commands.DELETE_EMP_BY_EMPNO);
            preparedStatement.setInt(1, this.empno.intValue());
            preparedStatement.execute();
        } catch (SQLException ex) {
            throw new RemoteException("Cannot remove emp: " + ex.getMessage());
        } catch (NamingException e) {
            throw new RemoteException(e.getMessage());
        }finally {
            try {
                close(resultSet, preparedStatement, connection);
            } catch (SQLException ex) {
                throw new RemoteException("Cannot remove emp. Cannot free resources", ex);
            }
        }
    }

    /**
     * 
     * @param emp - employee
     * @throws RemoteException
     */
    public void ejbHomeEditEmp(Emp emp) throws RemoteException {
        try {
            connection = getConnection(context);
            preparedStatement = connection.prepareStatement(Commands.UPDATE_EMP);
            preparedStatement.setString(1, emp.getEname());
            preparedStatement.setString(2, emp.getJob());
            preparedStatement.setInt(3, emp.getMgr());
            preparedStatement.setDate(4, new java.sql.Date(emp.getHiredate().getYear(), emp.getHiredate().getMonth(), emp.getHiredate().getDay()));
            preparedStatement.setDouble(5, emp.getSal());
            preparedStatement.setDouble(6, emp.getComm());
            preparedStatement.setInt(7, emp.getDeptno());
            preparedStatement.setInt(8, emp.getEmpno());
            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            throw new RemoteException("Cannot modify emp", ex);
        } catch (NamingException e) {
            throw new RemoteException(e.getMessage());
        } finally {
            try {
                close(resultSet, preparedStatement, connection);
            } catch (SQLException ex) {
                throw new RemoteException("Cannot modify emp. Cannot free resources", ex);
            }
        }
    }

    /**
     * 
     * @param emp - employee
     * @return id of employee
     * @throws CreateException
     * @throws RemoteException
     */
    public Integer ejbCreate(Emp emp) throws CreateException, RemoteException {
        try {
            connection = getConnection(context);
            preparedStatement = connection.prepareStatement(Commands.INSERT_IN_EMP);
            preparedStatement.setString(1, emp.getEname());
            preparedStatement.setString(2, emp.getJob());
            preparedStatement.setInt(3, emp.getMgr());
            preparedStatement.setDate(4, new java.sql.Date(emp.getHiredate().getYear(), emp.getHiredate().getMonth(), emp.getHiredate().getDay()));
            preparedStatement.setDouble(5, emp.getSal());
            preparedStatement.setDouble(6, emp.getComm());
            preparedStatement.setInt(7, emp.getDeptno());
            preparedStatement.executeQuery();

            preparedStatement = connection.prepareStatement(Commands.GET_EMP_ID_BY_ENAME);
            preparedStatement.setString(1, emp.getEname());
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                empno = Integer.valueOf(resultSet.getInt(Constants.EMPNO));
            } else {
                throw new CreateException("Result set is empty");
            }
        } catch (SQLException ex) {
            throw new CreateException("Cannot create emp: " + ex);
        } catch (NamingException e) {
            throw new RemoteException(e.getMessage());
        } finally {
            try {
                close(resultSet, preparedStatement, connection);
            } catch (SQLException ex) {
                throw new CreateException("Cannot create emp. Cannot free resources: " + ex.getMessage());
            }
        }
        return empno;
    }

    /**
     * 
     * @param emp - employee
     * @throws CreateException
     */
    public void ejbPostCreate(Emp emp)
            throws CreateException {
    }



    /**
     * 
     * @param empno - id of employee
     * @return Collection of ids of employee
     * @throws FinderException
     */
    public Collection ejbFindIerarhy(Integer empno) throws FinderException {
        try {
            connection = getConnection(context);
            preparedStatement = connection.prepareStatement(Commands.GET_EMP_IERARHY);
            preparedStatement.setInt(1, empno);
            preparedStatement.setInt(2, empno);
            resultSet = preparedStatement.executeQuery();
            Collection list = new ArrayList();
            while (resultSet.next()) {
                list.add(new Integer(resultSet.getInt(Constants.EMPNO)));
            }
            return list;
        } catch (SQLException ex) {
            throw new FinderException("No emp was found: " + ex.getMessage());
        } catch (NamingException e) {
            throw new FinderException(e.getMessage());
        } 
        finally {
            try {
                close(resultSet, preparedStatement, connection);
            } catch (SQLException ex) {
                throw new FinderException("No emp was found. Cannot free resources: " + ex.getMessage());
            }
        }
    }

    /**
     * 
     * @param empno - id of employee
     * @return Collection of ide of employee
     * @throws FinderException
     */
    public Collection ejbFindParentIerarhy(Integer empno) throws FinderException {
        try {
            connection = getConnection(context);
            preparedStatement = connection.prepareStatement(Commands.GET_EMP_PARENT_IERARHY);
            preparedStatement.setInt(1, empno);
            resultSet = preparedStatement.executeQuery();
            List list = new ArrayList();
            while (resultSet.next()) {
                list.add(new Integer(resultSet.getInt(Constants.EMPNO)));
            }
            Collections.reverse(list);
            return list;
        } catch (SQLException ex) {
            throw new FinderException("No emp was found: " + ex.getMessage());
        } catch (NamingException e) {
            throw new FinderException(e.getMessage());
        } finally {
            try {
                close(resultSet, preparedStatement, connection);
            } catch (SQLException ex) {
                throw new FinderException("No emp was found. Cannot free resources: " + ex.getMessage());
            }
        }
    }
}
