package com.skjenco.app.dto;

import com.skjenco.app.model.Org;

public class OrgDTO {
    private Long id;
    private Long supervisorId;

    public OrgDTO(){
    }

    public OrgDTO(Org org) {
        id = org.getId();
        supervisorId = org.getSupervisorId();
    }

    public OrgDTO(Long supervisorId) {
        this.supervisorId = supervisorId;
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
}
