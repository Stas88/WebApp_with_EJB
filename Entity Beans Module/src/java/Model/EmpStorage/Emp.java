package Model.EmpStorage;

import java.util.*;
import java.io.*;

/**
 * Employee wrapper
 * @author Admin
 */
public class Emp implements Serializable {

    private Integer empno;
    private String ename;
    private String job;
    private Integer mgr;
    private Date hiredate;
    private Double sal;
    private Double comm;
    private Integer deptno;

    /**
     * 
     * @param empno  - number of employee
     * @param ename  - name of employee
     * @param job - job of employee
     * @param mgr - manager of employee
     * @param hiredate - hiredate of employee
     * @param sal  - salary of employee
     * @param comm - commissions of employee
     * @param deptno - department number of employee
     */
    public Emp(Integer empno, String ename, String job, Integer mgr, Date hiredate, Double sal, Double comm, Integer deptno) {
        this.empno = empno;
        this.ename = ename;
        this.job = job;
        this.mgr = mgr;
        this.hiredate = hiredate;
        this.sal = sal;
        this.comm = comm;
        this.deptno = deptno;
    }

    public Emp() {
    }

    //Getters
    /**
     * 
     * @return  id of employee
     */
    public Integer getEmpno() {
        return empno;
    }

    /**
     * 
     * @return name of employee
     */
    public String getEname() {
        return ename;
    }

    /**
     * 
     * @return job of employee
     */
    public String getJob() {
        return job;
    }

    /**
     * 
     * @return manager of employee
     */
    public Integer getMgr() {
        return mgr;
    }

    /**
     * 
     * @return hiredate of employee
     */
    public Date getHiredate() {
        return hiredate;
    }

    /**
     * 
     * @return salary of employee
     */
    public Double getSal() {
        return sal;
    }

    /**
     * 
     * @return commissions of employee
     */
    public Double getComm() {
        return comm;
    }

    /**
     * 
     * @return department`s number of employee
     */
    public Integer getDeptno() {
        return deptno;
    }

    //Setters
    /**
     * 
     * @param empno - id of employee
     */
    public void setEmpno(Integer empno) {
        this.empno = empno;
    }

    /**
     * @param ename - name of employee
     */
    public void setEname(String ename) {
        this.ename = ename;
    }

    /**
     * 
     * @param job - job of employee
     */
    public void setJob(String job) {
        this.job = job;
    }

    /**
     * 
     * @param mgr - manager`s id of employee
     */
    public void setMgr(Integer mgr) {
        this.mgr = mgr;
    }

    /**
     * 
     * @param hiredate - hiredate of employee 
     */
    public void setHiredate(Date hiredate) {
        this.hiredate = hiredate;
    }

    /**
     * 
     * @param sal - salary if employee
     */
    public void setSal(Double sal) {
        this.sal = sal;
    }

    /**
     * 
     * @param comm - commissions of employee
     */
    public void setComm(Double comm) {
        this.comm = comm;
    }

    /**
     * 
     * @param deptno department`s number of employee
     */
    public void setDeptno(Integer deptno) {
        this.deptno = deptno;
    }
}
