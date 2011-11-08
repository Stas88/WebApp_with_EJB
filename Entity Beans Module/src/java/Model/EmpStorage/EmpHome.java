package Model.EmpStorage;


import javax.ejb.*;
import java.rmi.RemoteException;
import java.util.*;
import Model.Util.*;
/**
 * Home interface of Emp bean
 * @author Admin
 */
public interface EmpHome extends EJBHome {
    
    /**
     * 
     * @param empno - id of employee
     * @return Remote Interface
     * @throws RemoteException
     * @throws FinderException
     */
    public EmpRemote findByPrimaryKey(Integer empno) throws RemoteException, FinderException;
    
    /**
     * 
     * @return Collection of remote interfaces
     * @throws FinderException
     * @throws RemoteException
     */
    public Collection<EmpRemote> findAll() throws FinderException, RemoteException;
    
    /**
     * 
     * @param emp 
     * @throws RemoteException
     */
    public void editEmp(Emp emp) throws RemoteException;
    
    /**
     * 
     * @param emp - employee
     * @return Remote Interface
     * @throws CreateException
     * @throws RemoteException
     */
    public EmpRemote create(Emp emp) throws CreateException, RemoteException;
    
    /**
     * 
     * @param ename - name of employee
     * @return Collection of remote interfaces
     * @throws FinderException
     * @throws RemoteException
     */
    public Collection<EmpRemote> findByEname(String ename) throws  FinderException, RemoteException;
    
    /**
     * 
     * @param sal - salary of employee
     * @return Collection of remote interfaces
     * @throws FinderException
     * @throws RemoteException
     */
    public Collection<EmpRemote> findBySal(Double sal) throws  FinderException, RemoteException;
    
    /**
     * 
     * @param ename - name of employee
     * @param sal - salary of employee
     * @return Collection of remote interfaces
     * @throws FinderException
     * @throws RemoteException
     */
    public Collection<EmpRemote> findByEnameAndSal(String ename, Double sal) throws FinderException, RemoteException;
    
    /**
     * 
     * @param empno - number of employee
     * @return Collection of remote interfaces
     * @throws FinderException
     * @throws RemoteException
     */
    public Collection<EmpRemote> findParentIerarhy(Integer empno) throws FinderException, RemoteException;
    
    /**
     * 
     * @param empno - number of employee
     * @return Collection of remote interfaces
     * @throws FinderException
     * @throws RemoteException
     */
    public Collection<EmpRemote> findIerarhy(Integer empno) throws FinderException, RemoteException;
    
    /**
     * 
     * @param sortBy - sorting parameter
     * @return Collection of remote interfaces
     * @throws FinderException
     * @throws RemoteException
     */
    public Collection<EmpRemote> findSortedEmps(SortDatas sortBy) throws FinderException, RemoteException;
}
