package Model.DeptStorage;

import java.io.*;

/**
 * Wrapper of department
 * @author Admin
 */
public class Dept implements Serializable {

    private Integer deptno;
    private String dname;
    private String loc;

    /**
     * 
     * @param deptno - id of department
     * @param dname - name of department
     * @param loc - location of department
     */
    public Dept(Integer deptno, String dname, String loc) {
        this.deptno = deptno;
        this.dname = dname;
        this.loc = loc;
    }

    public Dept() {
    }

    /**
     * 
     * @return number  of department
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
     * @return location of department
     */
    public String getLoc() {
        return loc;
    }

    /**
     * 
     * @param deptno number of department
     */
    public void setDeptno(Integer deptno) {
        this.deptno = deptno;
    }

    /**
     * 
     * @param dname name of department 
     */
    public void setDname(String dname) {
        this.dname = dname;
    }

    /**
     * 
     * @param loc location of department 
     */
    public void setLoc(String loc) {
        this.loc = loc;
    }
}
