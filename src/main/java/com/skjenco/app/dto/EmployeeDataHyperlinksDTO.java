package com.skjenco.app.dto;

public class EmployeeDataHyperlinksDTO {
    private String get;
    private String delete;
    private String update;

    public EmployeeDataHyperlinksDTO() {
    }

    public EmployeeDataHyperlinksDTO(Long id, String baseUri) {
        String idString = id.toString();
        get = baseUri.concat(idString);
        delete = baseUri.concat(idString);
        update = baseUri.concat(idString);
    }

    public String getGet() {
        return get;
    }

    public void setGet(String get) {
        this.get = get;
    }

    public String getDelete() {
        return delete;
    }

    public void setDelete(String delete) {
        this.delete = delete;
    }

    public String getUpdate() {
        return update;
    }

    public void setUpdate(String update) {
        this.update = update;
    }
}
