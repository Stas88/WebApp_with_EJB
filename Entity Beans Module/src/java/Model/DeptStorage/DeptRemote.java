package Model.DeptStorage;

import javax.ejb.*;
import java.rmi.RemoteException;

/**
 * Remote interface of DeptBean
 * @author sikorskyi
 */
public interface DeptRemote extends EJBObject {
    
    /**
     * 
     * @return id of department
     * @throws RemoteException
     */
    public int getDeptno() throws RemoteException;
    
    /**
     * 
     * @return location of department 
     * @throws RemoteException
     */
    public String getLoc() throws RemoteException;
    
    /**
     * @return name of department  
     * @throws RemoteException
     */
    public String getDname() throws RemoteException;
    
}
