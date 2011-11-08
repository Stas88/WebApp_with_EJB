package Model.SalGradeStorage;

import javax.ejb.*;
import java.rmi.RemoteException;
import java.util.*;
import Model.Util.*;

/**
 * Home interface of SalGrade bean
 * @author sikorskyi
 */
public interface  SalGradeHome extends EJBHome {
    
    /**
     * 
     * @param grade - id of salary grade
     * @returnRemote interface
     * @throws RemoteException
     * @throws FinderException
     */
    public SalGradeRemote findByPrimaryKey(Integer grade) throws RemoteException, FinderException;
    
    /**
     * 
     * @return Collection of ids of grades
     * @throws FinderException
     * @throws RemoteException
     */
    public Collection findAll() throws FinderException, RemoteException;
    
    /**
     * 
     * @param salGrade Salary grade
     * @throws RemoteException
     */
    public void editSalGrade(SalGrade salGrade) throws RemoteException;
    
    /**
     * 
     * @param salGrade salary grade
     * @return Remote Interface 
     * @throws CreateException
     * @throws RemoteException
     */
    public SalGradeRemote create(SalGrade salGrade) throws CreateException, RemoteException;
    
    /**
     * 
     * @param sortBy - sorting parameter
     * @return Collection of remote interfaces
     * @throws FinderException
     * @throws RemoteException
     */
    public Collection<SalGradeRemote> findSortedSalGrades(SortDatas sortBy) throws FinderException, RemoteException;
    
}
