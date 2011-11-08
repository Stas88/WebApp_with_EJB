package Model.DeptStorage;

import javax.ejb.*;
import java.util.*;
import javax.naming.*;
import javax.sql.*;
import java.sql.*;
import java.rmi.*;
import Model.Util.*;
import java.io.*;

/**
 * DeptBean class
 * @author sikorskyi
 */
public class DeptBean extends ConnectionUtil implements EntityBean {

    private EntityContext context;
    private Integer deptno;
    private String dname;
    private String loc;
    private Connection connection = null;
    private Statement statement = null;
    private PreparedStatement preparedStatement = null;
    private ResultSet resultSet = null; 
    

    /**
     * 
     * @return id of department
     */
    public int getDeptno() {
        return deptno;
    }

    /**
     * 
     * @return name of department 
     */
    public String getDname() {
        return dname;
    }

    /**
     * 
     * @return - location of department 
     */
    public String getLoc() {
        return loc;
    }

    /**
     * 
     * @return Collection of ids of department
     * @throws FinderException
     */
    public Collection ejbFindAll() throws FinderException {
        try {
            connection = getConnection(context);
            Collection col = new ArrayList();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(Commands.GET_ALL_DEPTS);
            while (resultSet.next()) {
                col.add(new Integer(resultSet.getInt(Constants.DEPTNO)));
            }
            return col;
        } catch (SQLException e) {
            throw new FinderException(e.getMessage());
        } catch (NamingException ex) {
            throw new FinderException(ex.getMessage());
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
     * @param id of department
     * @return id of department
     * @throws FinderException
     */
    public Integer ejbFindByPrimaryKey(Integer id) throws FinderException {;
        try {
            connection = getConnection(context);
            preparedStatement = connection.prepareStatement(Commands.GET_DEPT_BY_DEPTNO);
            preparedStatement.setInt(1, id.intValue());
            resultSet = preparedStatement.executeQuery();
            if (!resultSet.next()) {
                throw new FinderException("No such dept exists");
            }
            this.deptno = id;
        } catch (SQLException e) {
            throw new FinderException(e.getMessage());
        } catch (NamingException ex) {
            throw new FinderException(ex.getMessage());
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
     * @param dname - name of department
     * @return Collection of ids of departments
     * @throws FinderException
     */
    public Collection ejbFindByDname(String dname) throws FinderException {
        try {
            connection = getConnection(context);
            preparedStatement = connection.prepareStatement(Commands.FIND_DEPTS_DNAME);
            preparedStatement.setString(1, dname);
            resultSet = preparedStatement.executeQuery();
            Collection list = new ArrayList();
            while (resultSet.next()) {
                list.add(new Integer(resultSet.getInt(Constants.DEPTNO)));
            }
            return list;
        } catch (SQLException ex) {
            throw new FinderException("No dept was found: " + ex.getMessage());
        } catch (NamingException ex) {
            throw new FinderException(ex.getMessage());
        } finally {
            try {
                close(resultSet, preparedStatement, connection);
            } catch (SQLException ex) {
                throw new FinderException("No dept was found. Cannot free resources: " + ex.getMessage());
            }
        }
    }

    /**
     * 
     * @param loc - location of department
     * @return Collection of ids of department
     * @throws FinderException
     */
    public Collection ejbFindByLoc(String loc) throws FinderException {
        try {
            connection = getConnection(context);
            preparedStatement = connection.prepareStatement(Commands.FIND_DEPTS_LOC);
            preparedStatement.setString(1, loc);
            resultSet = preparedStatement.executeQuery();
            Collection list = new ArrayList();
            while (resultSet.next()) {
                list.add(new Integer(resultSet.getInt(Constants.DEPTNO)));
            }
            return list;
        } catch (SQLException ex) {
            throw new FinderException("No task was found: " + ex.getMessage());
        } catch (NamingException ex) {
            throw new FinderException(ex.getMessage());
        } finally {
            try {
                close(resultSet, preparedStatement, connection);
            } catch (SQLException ex) {
                throw new FinderException("No task was found. Cannot free resources: " + ex.getMessage());
            }
        }
    }

    /**
     * @param sortBy - parameter of sorting 
     * @return Collection of ids of department
     * @throws FinderException
     */
    public Collection ejbFindSortedDepts(SortDatas sortBy) throws FinderException {
        try {
            connection = getConnection(context);
            statement = connection.createStatement();
            if (SortDatas.ByLocAsc.equals(sortBy)) {
                resultSet = statement.executeQuery(Commands.GET_ALL_DEPTS_LOC + " asc");
            } else if (SortDatas.ByLocDsc.equals(sortBy)) {
                resultSet = statement.executeQuery(Commands.GET_ALL_DEPTS_LOC + " desc");
            }
            Collection list = new ArrayList();
            while (resultSet.next()) {
                list.add(new Integer(resultSet.getInt(Constants.DEPTNO)));
            }
            return list;
        } catch (SQLException ex) {
            throw new FinderException("No task was found: " + ex.getMessage());
        } catch (NamingException ex) {
            throw new FinderException(ex.getMessage());
        } finally {
            try {
                close(resultSet, preparedStatement, connection);
            } catch (SQLException ex) {
                throw new FinderException("No task was found. Cannot free resources: " + ex.getMessage());
            }
        }
    }

    /**
     * 
     * @param dname - name of department 
     * @param loc - location of department
     * @return Collection of ids of department
     * @throws FinderException
     */
    public Collection ejbFindByDnameAndLoc(String dname, String loc) throws FinderException {
        try {
            connection = getConnection(context);
            preparedStatement = connection.prepareStatement(Commands.FIND_DEPTS_DNAME_LOC);
            preparedStatement.setString(1, dname);
            preparedStatement.setString(2, loc);
            resultSet = preparedStatement.executeQuery();
            Collection list = new ArrayList();
            while (resultSet.next()) {
                list.add(new Integer(resultSet.getInt(Constants.DEPTNO)));
            }
            return list;
        } catch (SQLException ex) {
            throw new FinderException("No task was found: " + ex.getMessage());
        } catch (NamingException ex) {
            throw new FinderException(ex.getMessage());
        } finally {
            try {
                close(resultSet, preparedStatement, connection);
            } catch (SQLException ex) {
                throw new FinderException("No task was found. Cannot free resources: " + ex.getMessage());
            }
        }
    }

    /**
     * @throws EJBException
     * @throws RemoteException
     */
    public void ejbLoad() throws EJBException, RemoteException {
        try {
            connection = getConnection(context);
            preparedStatement = connection.prepareStatement(Commands.GET_DEPT_BY_DEPTNO);
            preparedStatement.setInt(1, this.deptno);
            resultSet = preparedStatement.executeQuery();
            if (!resultSet.next()) {
                throw new NoSuchEntityException("Dept does not exist");
            }
            this.deptno = resultSet.getInt(Constants.DEPTNO);
            this.loc = resultSet.getString(Constants.LOC);
            this.dname = resultSet.getString(Constants.DNAME);
        } catch (SQLException e) {
            throw new RemoteException(e.getMessage());
        } catch (NamingException ex) {
            throw new RemoteException(ex.getMessage());
        } finally {
            try {
                close(resultSet, preparedStatement, connection);
            } catch (SQLException e) {
                throw new RemoteException(e.getMessage());
            }
        }
    }

    /**
     * @throws EJBException
     * @throws RemoteException
     */
    public void ejbStore() throws EJBException, RemoteException {
    }

    /**
     * @throws EJBException
     * @throws RemoteException
     */
    public void ejbActivate() throws EJBException, RemoteException {
        this.deptno = (Integer)context.getPrimaryKey();
    }

    /**
     * @throws EJBException
     * @throws RemoteException
     */
    public void ejbPassivate() throws EJBException, RemoteException {
        deptno = null;
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
            preparedStatement = connection.prepareStatement(Commands.DELETE_DEPT_BY_DEPTNO);
            preparedStatement.setInt(1, this.deptno.intValue());
            preparedStatement.execute();
        } catch (SQLException ex) {
            throw new RemoteException("Cannot remove task: " + ex.getMessage());
        } catch (NamingException ex) {
            throw new RemoteException(ex.getMessage());
        } finally {
            try {
                close(resultSet, preparedStatement, connection);
            } catch (SQLException ex) {
                throw new RemoteException("Cannot remove task. Cannot free resources", ex);
            }
        }
    }
    
    

    /**
     * @param dept - Department
     * @throws RemoteException
     */
    public void ejbHomeEditDept(Dept dept) throws RemoteException {
        try {
            connection = getConnection(context);
            preparedStatement = connection.prepareStatement(Commands.UPDATE_DEPT);
            preparedStatement.setString(1, dept.getDname());
            preparedStatement.setString(2, dept.getLoc());
            preparedStatement.setInt(3, dept.getDeptno());
            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            throw new RemoteException("Cannot modify task", ex);
        } catch (NamingException ex) {
            throw new RemoteException(ex.getMessage());
        } finally {
            try {
                close(resultSet, preparedStatement, connection);
            } catch (SQLException ex) {
                throw new RemoteException("Cannot modify task. Cannot free resources", ex);
            }
        }
    }

    /**
     * 
     * @param dept - Department
     * @return id of department
     * @throws CreateException
     * @throws RemoteException
     */
    public Integer ejbCreate(Dept dept) throws CreateException, RemoteException {
        try {
            connection = getConnection(context);
            preparedStatement = connection.prepareStatement(Commands.INSERT_IN_DEPT);
            preparedStatement.setString(1, dept.getDname());
            preparedStatement.setString(2, dept.getLoc());
            preparedStatement.executeQuery();

            preparedStatement = connection.prepareStatement(Commands.GET_DEPT_ID_BY_DNAME);
            preparedStatement.setString(1, dept.getDname());
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                deptno = Integer.valueOf(resultSet.getInt(Constants.DEPTNO));
            } else {
                throw new CreateException("Result set is empty");
            }
        } catch (SQLException ex) {
            throw new CreateException("Cannot create dept: " + ex);
        } catch (NamingException ex) {
            throw new RemoteException(ex.getMessage());
        } finally {
            try {
                close(resultSet, preparedStatement, connection);
            } catch (SQLException ex) {
                throw new CreateException("Cannot create task. Cannot free resources: " + ex.getMessage());
            }
        }
        return deptno;
    }

    /**
     * 
     * @param dept - Department
     * @throws CreateException
     */
    public void ejbPostCreate(Dept dept)
            throws CreateException {
    }

}
