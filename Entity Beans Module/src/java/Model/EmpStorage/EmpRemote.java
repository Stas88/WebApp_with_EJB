
package Model.EmpStorage;

import javax.ejb.*;
import java.rmi.RemoteException;
import java.util.*;
/**
 * Remote Interface of Emp bean
 * @author Admin
 */
public interface EmpRemote extends EJBObject {

    /**
     * 
     * @return  number of employee
     * @throws RemoteException
     */
    public Integer getEmpno() throws RemoteException;
    
    /**
     * 
     * @return name of employee
     * @throws RemoteException
     */
    public String getEname() throws RemoteException;
    
    /**
     * 
     * @return job of employee
     * @throws RemoteException
     */
    public String getJob() throws RemoteException;
    
    /**
     * 
     * @return manager of employee
     * @throws RemoteException
     */
    public Integer getMgr() throws RemoteException;
    
    /**
     * 
     * @return hiredate of employee
     * @throws RemoteException
     */
    public Date getHiredate() throws RemoteException;
    
    /**
     * 
     * @return salary of employee
     * @throws RemoteException
     */
    public Double getSal() throws RemoteException;
    
    /**
     * 
     * @return commissions of employee
     * @throws RemoteException
     */
    public Double getComm() throws RemoteException;
    
    /**
     * 
     * @return id of employee
     * @throws RemoteException
     */
    public Integer getDeptno() throws RemoteException;
}
