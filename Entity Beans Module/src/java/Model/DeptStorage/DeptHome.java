/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.DeptStorage;

import javax.ejb.*;
import java.rmi.RemoteException;
import java.util.*;
import Model.Util.*;

/**
 * Home interface of DeptBean
 * @author sikorskyi
 */
public interface DeptHome extends EJBHome {
    
    /**
     * @param deptno - id of department
     * @return Remote interface 
     * @throws RemoteException
     * @throws FinderException
     */
    public DeptRemote findByPrimaryKey(Integer deptno) throws RemoteException, FinderException;
    
    /**
     * 
     * @return Collection of Remote interfaces
     * @throws FinderException
     * @throws RemoteException
     */
    public Collection<DeptRemote> findAll() throws FinderException, RemoteException;
    
    /**
     * 
     * @param dept - Department 
     * @throws RemoteException
     */
    public void editDept(Dept dept) throws RemoteException;
    
    /**
     * 
     * @param dept - department 
     * @return Remote interface
     * @throws CreateException
     * @throws RemoteException
     */
    public DeptRemote create(Dept dept) throws CreateException, RemoteException;
    
    /**
     * 
     * @param dname - name of department 
     * @return Collection of Remote interfaces
     * @throws FinderException
     * @throws RemoteException
     */
    public Collection<DeptRemote> findByDname(String dname) throws  FinderException, RemoteException;
    
    /**
     * 
     * @param dname - name of department 
     * @return Collection of Remote interfaces
     * @throws FinderException
     * @throws RemoteException
     */
    public Collection<DeptRemote> findByLoc(String dname) throws  FinderException, RemoteException;
    
    /**
     * 
     * @param dname name of department 
     * @param loc location of  department 
     * @return Collection of Remote interfaces
     * @throws FinderException
     * @throws RemoteException
     */
    public Collection<DeptRemote> findByDnameAndLoc(String dname, String loc) throws FinderException, RemoteException;
    
    /**
     * 
     * @param sortBy - parameter of sorting
     * @return Collection of Remote interfaces
     * @throws FinderException
     * @throws RemoteException
     */
    public Collection<DeptRemote> findSortedDepts (SortDatas sortBy) throws FinderException, RemoteException;
    
    
    
}
