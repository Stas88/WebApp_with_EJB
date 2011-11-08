package Model.SalGradeStorage;

import java.io.*;
/**
 * Salary Grade wrapper
 * @author sikorskyi
 */
public class SalGrade implements Serializable {
    
    private Integer grade;
    private double  minSal;
    private double maxSal;
    

    public SalGrade() {}
    
    /**
     * 
     * @param grade - grade of salaries 
     * @param minSal - minimal salary
     * @param maxSal - maximal salary 
     */
    public SalGrade (Integer grade, double minSal, double maxSal) {
        this.grade = grade;
        this.minSal = minSal;
        this.maxSal = maxSal;
    }
    
    //Getters 
    
    /**
     * 
     * @return grade id
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
    
    //setters
    
    /**
     * 
     * @param grade id of grade
     */
    public void setGrade(Integer grade) {
        this.grade = grade;
    }
    
    /**
     * 
     * @param minSal minimal salary 
     */
    public void setMinSal(double minSal) {
        this.minSal = minSal;
    }
    
    /**
     * 
     * @param maxSal maximal salary
     */
    public void setMaxSal(double maxSal) {
        this.maxSal = maxSal;
    }
    
    
    
    
}
