package com.skjenco.app.model;

import com.skjenco.app.dto.EmployeeSalaryDTO;

import javax.persistence.*;

@Entity
@Table(name = "employee_salary")
public class EmployeeSalary {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long salary;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "employee_id")
    private Employee employee;

    protected EmployeeSalary() {}

    public EmployeeSalary(EmployeeSalaryDTO salaryDTO, Long employeeId) {
        this(salaryDTO);
        this.employee = new Employee(employeeId);
    }

    public EmployeeSalary(EmployeeSalaryDTO salaryDTO) {
        this.id = salaryDTO.getId();
        this.salary = salaryDTO.getSalary();
    }

    @Override
    public String toString() {
        return String.format(
                "Org[id=%d, employeeId='%d', salary='%d']",
                id, employee.getId(), salary);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public Long getSalary() {
        return salary;
    }

    public void setSalary(Long salary) {
        this.salary = salary;
    }
}
