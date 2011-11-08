package Model.SalGradeStorage;

import javax.ejb.*;
import java.rmi.RemoteException;
/**
 * Remote interface of SalGrade bean
 * @author sikorskyi
 */
public interface SalGradeRemote extends EJBObject{
    
    /**
     * 
     * @return id of grade
     * @throws RemoteException
     */
    public int getGrade() throws RemoteException;
    
    /**
     * 
     * @return minimal salary 
     * @throws RemoteException
     */
    public double getMinSal() throws RemoteException;
    
    /**
     * 
     * @return maximal salary 
     * @throws RemoteException
     */
    public double getMaxSal() throws RemoteException;
}
