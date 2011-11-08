/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.DAO;
import Model.SalGradeStorage.*;
import Model.EmpStorage.*;

/**
 *
 * @author sikorskyi
 */
public class Validator {
    
    public static void validate(SalGrade salGrade) throws ValidateException {
        if (salGrade.getGrade() < 0) throw new ValidateException("Grade could not be negative");
        if (salGrade.getMinSal() < 0) throw new ValidateException("Salary could not be negative");
        if (salGrade.getMaxSal() < 0) throw new ValidateException("Salary could not be negative");
    }
    
    public static void validate(Emp emp) throws ValidateException {
        if (emp.getSal() < 0) throw new ValidateException("Salary could not be negative");
        if (emp.getComm() < 0) throw new ValidateException("Commissions could not be negative");
    }
    
}
