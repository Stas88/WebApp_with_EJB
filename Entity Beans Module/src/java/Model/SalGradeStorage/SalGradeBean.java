package Model.SalGradeStorage;


import javax.ejb.*;
import java.util.*;
import javax.naming.*;
import java.sql.*;
import java.rmi.*;
import Model.Util.*;

/**
 * SalGrade bean class
 * @author sikorskyi
 */
public class SalGradeBean extends ConnectionUtil implements EntityBean {

    private EntityContext context;
    private Integer grade;
    private double minSal;
    private double maxSal;
    private Connection connection = null;
    private Statement statement = null;
    private PreparedStatement preparedStatement = null;
    private ResultSet resultSet = null;
    
    /**
     * 
     * @return grade of salary 
     */
    public int getGrade() {
        return grade;
    }

    /**
     * 
     * @return minimal salary 
     */
    public double getMinSal() {
        return minSal;
    }

    /**
     * 
     * @return maximal salary 
     */
    public double getMaxSal() {
        return maxSal;
    }

    /**
     * 
     * @return Collection of grades` ids
     * @throws FinderException
     */
    public Collection ejbFindAll() throws FinderException {
        try {
            connection = getConnection(context);
            Collection col = new ArrayList();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(Commands.GET_ALL_SALGRADES);
            while (resultSet.next()) {
                col.add(new Integer(resultSet.getInt(Constants.GRADE)));
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
     * @param id - id of salary grade
     * @return id of salary grade
     * @throws FinderException
     */
    public Integer ejbFindByPrimaryKey(Integer id) throws FinderException {
        try {
            connection = getConnection(context);
            preparedStatement = connection.prepareStatement(Commands.GET_SALGRADE_BY_GRADE);
            preparedStatement.setInt(1, id.intValue());
            resultSet = preparedStatement.executeQuery();
            if (!resultSet.next()) {
                throw new FinderException("No such SalGrade exists");
            }
            this.grade = id;
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
     * @param sortBy - sorting parameter
     * @return Collection of grades` ids
     * @throws FinderException
     */
    public Collection ejbFindSortedSalGrades(SortDatas sortBy) throws FinderException {
        try {
            connection = getConnection(context);
            statement = connection.createStatement();
            if (SortDatas.ByGradeAsc.equals(sortBy)) {
                resultSet = statement.executeQuery(Commands.GET_ALL_SALGRADES_GRADE);
            } else if (SortDatas.ByGradeDsc.equals(sortBy)) {
                resultSet = statement.executeQuery(Commands.GET_ALL_SALGRADES_GRADE + " desc");
            }
            Collection list = new ArrayList();
            while (resultSet.next()) {
                list.add(new Integer(resultSet.getInt(Constants.GRADE)));
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
     * @throws EJBException
     * @throws RemoteException
     */
    public void ejbLoad() throws EJBException, RemoteException {
        try {
            //deptno = (Integer) context.getPrimaryKey();
            connection = getConnection(context);
            preparedStatement = connection.prepareStatement(Commands.GET_SALGRADE_BY_GRADE);
            preparedStatement.setInt(1, this.grade);
            resultSet = preparedStatement.executeQuery();
            if (!resultSet.next()) {
                throw new NoSuchEntityException("Dept does not exist");
            }
            this.grade = resultSet.getInt(Constants.GRADE);
            this.maxSal = resultSet.getDouble("minsal");
            this.minSal = resultSet.getDouble("hisal");
        } catch (SQLException e) {
            throw new RemoteException(e.getMessage());
        } catch (NamingException ex) {
            throw new RemoteException("Cannot modify task", ex);
        } finally {
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
        this.grade = (Integer) context.getPrimaryKey();
    }

    /**
     * 
     * @throws EJBException
     * @throws RemoteException
     */
    public void ejbPassivate() throws EJBException, RemoteException {
        grade = null;
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
            preparedStatement = connection.prepareStatement(Commands.DELETE_SALGRADE_BY_GRADE);
            preparedStatement.setInt(1, this.grade.intValue());
            preparedStatement.execute();
        } catch (SQLException ex) {
            throw new RemoteException("Cannot remove salGrade: " + ex.getMessage());
        } catch (NamingException ex) {
            throw new RemoteException("Cannot modify task", ex);
        } finally {
            try {
                close(resultSet, preparedStatement, connection);
            } catch (SQLException ex) {
                throw new RemoteException("Cannot remove salGrade. Cannot free resources", ex);
            }
        }
    }

    
    /**
     * 
     * @param salGrade - salary grade
     * @throws RemoteException
     */
    public void ejbHomeEditSalGrade(SalGrade salGrade) throws RemoteException {
        try {
            connection = getConnection(context);
            preparedStatement = connection.prepareStatement(Commands.UPDATE_SALGRADE);
            preparedStatement.setInt(3, salGrade.getGrade());
            preparedStatement.setDouble(1, salGrade.getMinSal());
            preparedStatement.setDouble(2, salGrade.getMaxSal());
            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            throw new RemoteException("Cannot modify task", ex);
        } catch (NamingException ex) {
            throw new RemoteException("Cannot modify task", ex);
        } 
        finally {
            try {
                close(resultSet, preparedStatement, connection);
            } catch (SQLException ex) {
                throw new RemoteException("Cannot modify task. Cannot free resources", ex);
            }
        }
    }

    /**
     * 
     * @param salGrade - salary grade
     * @return id of salary grade
     * @throws CreateException
     * @throws RemoteException
     */
    public Integer ejbCreate(SalGrade salGrade) throws CreateException, RemoteException {
        try {
            connection = getConnection(context);
            preparedStatement = connection.prepareStatement(Commands.INSERT_IN_SALGRADE);
            preparedStatement.setDouble(1, salGrade.getMinSal());
            preparedStatement.setDouble(2, salGrade.getMaxSal());
            preparedStatement.executeQuery();

            preparedStatement = connection.prepareStatement(Commands.GET_SALGRADE_ID_BY_MINSAL_AND_MAXSAL);
            preparedStatement.setDouble(1, salGrade.getMinSal());
            preparedStatement.setDouble(2, salGrade.getMaxSal());
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                grade = Integer.valueOf(resultSet.getInt(Constants.GRADE));
            } else {
                throw new CreateException("Result set is empty");
            }
        } catch (SQLException ex) {
            throw new CreateException("Cannot create dept: " + ex);
        } catch (NamingException ex) {
            throw new RemoteException("Cannot modify task", ex);
        } finally {
            try {
                close(resultSet, preparedStatement, connection);
            } catch (SQLException ex) {
                throw new CreateException("Cannot create task. Cannot free resources: " + ex.getMessage());
            }
        }
        return grade;
    }

    /**
     * 
     * @param salGrade - salary grade
     * @throws CreateException
     */
    public void ejbPostCreate(SalGrade salGrade)
            throws CreateException {
    }

   
}
