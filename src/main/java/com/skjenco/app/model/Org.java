package com.skjenco.app.model;

import com.skjenco.app.dto.OrgDTO;

import javax.persistence.*;

@Entity
@Table(name = "org")
public class Org {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long supervisorId;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "employee_id")
    private Employee employee;

    protected Org() {}

    public Org(OrgDTO orgDTO) {
        this.id = orgDTO.getId();
        this.supervisorId = orgDTO.getSupervisorId();
    }

    public Org(Long id, Long supervisorId) {
        this.id = id;
        this.supervisorId = supervisorId;
    }

    @Override
    public String toString() {
        return String.format(
                "Org[id=%d, employeeId='%d', supervisorId='%d']",
                id, employee.getId(), supervisorId);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getSupervisorId() {
        return supervisorId;
    }

    public void setSupervisorId(Long supervisorId) {
        this.supervisorId = supervisorId;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }
}
