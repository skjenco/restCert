package com.skjenco.app.model;

import com.skjenco.app.dto.EmployeePhoneDTO;

import javax.persistence.*;

@Entity
@Table(name = "employee_phone")
public class EmployeePhone {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String type;
    private String number;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "employee_id")
    private Employee employee;

    protected EmployeePhone() {}

    public EmployeePhone(EmployeePhoneDTO phoneDTO, Long employeeId) {
        this(phoneDTO);
        this.employee = new Employee(employeeId);
    }

    public EmployeePhone(EmployeePhoneDTO phoneDTO) {
        this.id = phoneDTO.getId();
        this.type = phoneDTO.getType();
        this.number = phoneDTO.getNumber();
    }

    @Override
    public String toString() {
        return String.format(
                "Org[id=%d, employeeId='%d', type='%s', number='%s']",
                id, employee.getId(), type, number);
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }
}
