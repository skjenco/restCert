package com.skjenco.app.dto;

public class EmployeeHyperlinksDTO {
    private static String empBase = "/employees/";
    private static String moveBase = "/move/{supervisorId}";
    private static String reportsBase = "/reports";
    private String get;
    private String delete;
    private String update;
    private String move;
    private String reports;

    public EmployeeHyperlinksDTO() {
    }

    public EmployeeHyperlinksDTO(Long id) {
        String idString = id.toString();
        get = empBase.concat(idString);
        delete = empBase.concat(idString);
        update = empBase.concat(idString);
        move = empBase.concat(idString.concat(moveBase));
        reports = empBase.concat(idString.concat(reportsBase));
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

    public String getMove() {
        return move;
    }

    public void setMove(String move) {
        this.move = move;
    }

    public String getReports() {
        return reports;
    }

    public void setReports(String reports) {
        this.reports = reports;
    }
}
