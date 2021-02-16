package com.skjenco.app.model;

import com.skjenco.app.dto.EmployeeDTO;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "employees")
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @OneToOne(fetch = FetchType.LAZY, mappedBy = "employee", cascade=CascadeType.ALL)
    private Org org;
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "employee", cascade=CascadeType.ALL)
    private List<EmployeeAddress> employeeAddress;
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "employee", cascade=CascadeType.ALL)
    private List<EmployeePhone> employeePhone;
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "employee", cascade=CascadeType.ALL)
    private List<EmployeeSalary> employeeSalary;

    protected Employee() {}

    public Employee(EmployeeDTO employeeDTO) {
        if(employeeDTO != null) {
            this.id = employeeDTO.getId();
            this.name = employeeDTO.getName();
            if(employeeDTO.getSupervisorId() != null) {
                this.org = new Org(id, employeeDTO.getSupervisorId());
                this.org.setEmployee(this);
            }
        }
    }

    public Employee(Long orgId, Long id, Long supervisorId, String name) {
        this.id = id;
        this.name = name;
        org = new Org();
        org.setId(orgId);
        org.setSupervisorId(supervisorId);
    }

    public Employee(String name) {
        this.name = name;
    }

    public Employee(Long id) { this.id = id; }

    @Override
    public String toString() {
        return String.format(
                "Employee[id=%d, name='%s']",
                id, name);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Org getOrg() {
        return org;
    }

    public void setOrg(Org org) {
        this.org = org;
    }

    public List<EmployeeAddress> getEmployeeAddress() {
        return employeeAddress;
    }

    public void setEmployeeAddress(List<EmployeeAddress> employeeAddress) {
        this.employeeAddress = employeeAddress;
    }

    public List<EmployeePhone> getEmployeePhone() {
        return employeePhone;
    }

    public void setEmployeePhone(List<EmployeePhone> employeePhone) {
        this.employeePhone = employeePhone;
    }

    public List<EmployeeSalary> getEmployeeSalary() {
        return employeeSalary;
    }

    public void setEmployeeSalary(List<EmployeeSalary> employeeSalary) {
        this.employeeSalary = employeeSalary;
    }
}
