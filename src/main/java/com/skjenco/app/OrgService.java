package com.skjenco.app;

import com.skjenco.app.dto.EmployeeDTO;
import com.skjenco.app.dto.OrgChartDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrgService {
    @Autowired
    EmployeeService employeeService;
    private static final String EMP_NOT_FOUND = "Employee not found";

    public List<OrgChartDTO> getOrgChart(Integer pageSize, Integer pageNum, String sort) {
        List<EmployeeDTO> empList = employeeService.getEmployeeDTOBySupervisorId(null);
        List<OrgChartDTO> orgList = new ArrayList<>(0);
        int recordStart = 0;
        int recordEnd = 0;
        if(pageSize != null && pageNum != null) {
            recordStart = pageSize * (pageNum - 1);
            recordEnd = recordStart + pageSize;
        }
        boolean sortDsc = true;
        if("A".equalsIgnoreCase(sort)) {
            sortDsc = false;
        }
        if(empList != null && !empList.isEmpty()) {
            if(empList.size() != 1) {
                throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "More than one employee with no direct report");
            } else {
                OrgChartDTO org = new OrgChartDTO(empList.get(0));
                org.setReports(processOrgChart(empList.get(0).getId(), sortDsc));
                orgList.add(org);
            }
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No employee found with no direct report");
        }
        if(recordEnd <= recordStart) {
            recordEnd = orgList.size();
        }
        return pageResult(orgList, recordStart, recordEnd);
    }

    private List<OrgChartDTO> processOrgChart(Long id, boolean sortDsc) {
        List<OrgChartDTO> orgList = new ArrayList<>(0);
        List<EmployeeDTO> empList = employeeService.getEmployeeDTOBySupervisorId(id, sortDsc);
        for(EmployeeDTO emp : empList) {
            OrgChartDTO org = new OrgChartDTO(emp);
            org.setReports(processOrgChart(emp.getId(), sortDsc));
            orgList.add(org);
        }
        return orgList;
    }

    private List<OrgChartDTO> pageResult(List<OrgChartDTO> orgList, int recordStart, int recordEnd) {
        //TODO Paging
        return orgList;
    }
}
