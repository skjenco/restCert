package com.skjenco.app;

import com.skjenco.app.dto.EmployeeDTO;
import com.skjenco.app.dto.EmployeeSalaryDTO;
import com.skjenco.app.dto.EmployeeAddressDTO;
import com.skjenco.app.dto.EmployeePhoneDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.CacheControl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.List;
import java.util.concurrent.TimeUnit;

@RestController
@CrossOrigin
public class EmployeeController {
    @Autowired
    EmployeeService employeeService;
    String NOT_FOUND = "No employee with this ID could be found.";

    @RequestMapping("/employees")
    public ResponseEntity<List<EmployeeDTO>> listEmployees(@RequestParam(value = "pageSize", required = false) Integer pageSize, @RequestParam(value = "pageNum", required = false) Integer pageNum, @RequestParam(value = "sort", required = false) String sort) {
        return ResponseEntity.ok().cacheControl(CacheControl.maxAge(30, TimeUnit.SECONDS).cachePrivate()).body(employeeService.getAllEmployees(pageSize, pageNum, sort));
    }

    @RequestMapping(value = "/employees/{id}", method = RequestMethod.GET)
    public ResponseEntity<EmployeeDTO> getEmployee(@PathVariable Long id) {
        EmployeeDTO emp = employeeService.getEmployeeDTOById(id);
        if(emp == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Employee not found");
        }
        return ResponseEntity.ok().cacheControl(CacheControl.maxAge(30, TimeUnit.SECONDS).cachePrivate()).body(emp);
    }

    @RequestMapping(value = "/employees/{id}/reports", method = RequestMethod.GET)
    public ResponseEntity<List<EmployeeDTO>> getDirectReports(@PathVariable Long id) {
        EmployeeDTO emp = employeeService.getEmployeeDTOById(id);
        if(emp == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Employee not found");
        }
        return ResponseEntity.ok().cacheControl(CacheControl.maxAge(30, TimeUnit.SECONDS).cachePrivate()).body(employeeService.getDirectReports(id));
    }

    @RequestMapping(value = "/employees/{id}", method = RequestMethod.PUT)
    public ResponseEntity<EmployeeDTO> updateEmployee(@PathVariable Long id, @Valid @RequestBody EmployeeDTO employeeDTO) {
        EmployeeDTO employee = employeeService.getEmployeeDTOById(id);
        employeeDTO.setId(id);
        if(employee != null) {
            return ResponseEntity.ok().cacheControl(CacheControl.maxAge(30, TimeUnit.SECONDS).cachePrivate()).body(employeeService.updateEmployee(employeeDTO));
        }
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, NOT_FOUND);
    }

    @RequestMapping(value = "/employees/{id}/move/{supervisorId}", method = RequestMethod.PUT)
    public ResponseEntity<EmployeeDTO> updateSupervisor(@PathVariable Long id, @PathVariable Long supervisorId) {
        if(id == null || supervisorId == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "ID and Supervisor ID are required.");
        }
        EmployeeDTO employee = employeeService.getEmployeeDTOById(id);
        if(employee != null) {
            employee.setSupervisorId(supervisorId);
            return ResponseEntity.ok().cacheControl(CacheControl.maxAge(30, TimeUnit.SECONDS).cachePrivate()).body(employeeService.updateEmployee(employee));
        }
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, NOT_FOUND);
    }

    @RequestMapping(value = "/employees", method = RequestMethod.POST)
    public ResponseEntity<EmployeeDTO> createEmployee(@Valid @RequestBody EmployeeDTO employeeDTO) {
        return ResponseEntity.ok().cacheControl(CacheControl.maxAge(30, TimeUnit.SECONDS).cachePrivate()).body(employeeService.createEmployee(employeeDTO));
    }

    @RequestMapping(value = "/employees/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<EmployeeDTO> deleteEmployee(@PathVariable Long id) {
        EmployeeDTO emp = employeeService.getEmployeeDTOById(id, "DELETE");
        if(emp == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Employee not found");
        }
        employeeService.deleteEmployeeById(id);
        return ResponseEntity.ok().cacheControl(CacheControl.maxAge(30, TimeUnit.SECONDS).cachePrivate()).body(emp);
    }

    //Employee Addresses
    @RequestMapping(value = "/employees/{id}/addresses", method = RequestMethod.GET)
    public ResponseEntity<List<EmployeeAddressDTO>> getEmployeeAddresses(@PathVariable Long id) {
        List<EmployeeAddressDTO> emp = employeeService.getEmployeeAddressDTOByEmployeeId(id);
        if(emp == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Employee Address not found");
        }
        return ResponseEntity.ok().cacheControl(CacheControl.maxAge(30, TimeUnit.SECONDS).cachePrivate()).body(emp);
    }

    @RequestMapping(value = "/employees/{id}/addresses/{addressId}", method = RequestMethod.GET)
    public ResponseEntity<EmployeeAddressDTO> getEmployeeAddress(@PathVariable Long id, @PathVariable Long addressId) {
        EmployeeAddressDTO emp = employeeService.getEmployeeAddressDTOById(addressId);
        if(emp == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Employee Address not found");
        }
        return ResponseEntity.ok().cacheControl(CacheControl.maxAge(30, TimeUnit.SECONDS).cachePrivate()).body(emp);
    }

    @RequestMapping(value = "/employees/{employeeId}/addresses", method = RequestMethod.POST)
    public ResponseEntity<EmployeeAddressDTO> createEmployeeAddress(@PathVariable Long employeeId, @Valid @RequestBody EmployeeAddressDTO employeeAddressDTO) {
        employeeAddressDTO.setId(null);
        return ResponseEntity.ok().cacheControl(CacheControl.maxAge(30, TimeUnit.SECONDS).cachePrivate()).body(employeeService.createEmployeeAddress(employeeAddressDTO, employeeId));
    }

    @RequestMapping(value = "/employees/{employeeId}/addresses/{id}", method = RequestMethod.PUT)
    public ResponseEntity<EmployeeAddressDTO> updateEmployeeAddress(@PathVariable Long employeeId, @PathVariable Long id, @Valid @RequestBody EmployeeAddressDTO employeeAddressDTO) {
        employeeAddressDTO.setId(id);
        return ResponseEntity.ok().cacheControl(CacheControl.maxAge(30, TimeUnit.SECONDS).cachePrivate()).body(employeeService.updateEmployeeAddress(employeeAddressDTO, employeeId));
    }

    //Employee Phones
    @RequestMapping(value = "/employees/{id}/phones", method = RequestMethod.GET)
    public ResponseEntity<List<EmployeePhoneDTO>> getEmployeePhones(@PathVariable Long id) {
        List<EmployeePhoneDTO> emp = employeeService.getEmployeePhoneDTOByEmployeeId(id);
        if(emp == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Employee Phone not found");
        }
        return ResponseEntity.ok().cacheControl(CacheControl.maxAge(30, TimeUnit.SECONDS).cachePrivate()).body(emp);
    }

    @RequestMapping(value = "/employees/{id}/phones/{phoneId}", method = RequestMethod.GET)
    public ResponseEntity<EmployeePhoneDTO> getEmployeePhone(@PathVariable Long id, @PathVariable Long phoneId) {
        EmployeePhoneDTO emp = employeeService.getEmployeePhoneDTOById(phoneId);
        if(emp == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Employee Phone not found");
        }
        return ResponseEntity.ok().cacheControl(CacheControl.maxAge(30, TimeUnit.SECONDS).cachePrivate()).body(emp);
    }

    @RequestMapping(value = "/employees/{employeeId}/phones", method = RequestMethod.POST)
    public ResponseEntity<EmployeePhoneDTO> createEmployeePhone(@PathVariable Long employeeId, @Valid @RequestBody EmployeePhoneDTO employeePhoneDTO) {
        employeePhoneDTO.setId(null);
        return ResponseEntity.ok().cacheControl(CacheControl.maxAge(30, TimeUnit.SECONDS).cachePrivate()).body(employeeService.createEmployeePhone(employeePhoneDTO, employeeId));
    }

    @RequestMapping(value = "/employees/{employeeId}/phones/{id}", method = RequestMethod.PUT)
    public ResponseEntity<EmployeePhoneDTO> updateEmployeePhone(@PathVariable Long employeeId, @PathVariable Long id, @Valid @RequestBody EmployeePhoneDTO employeePhoneDTO) {
        employeePhoneDTO.setId(id);
        return ResponseEntity.ok().cacheControl(CacheControl.maxAge(30, TimeUnit.SECONDS).cachePrivate()).body(employeeService.updateEmployeePhone(employeePhoneDTO, employeeId));
    }

    //Employee Salaries
    @RequestMapping(value = "/employees/{id}/salaries", method = RequestMethod.GET)
    public ResponseEntity<List<EmployeeSalaryDTO>> getEmployeeSalaries(@PathVariable Long id) {
        List<EmployeeSalaryDTO> emp = employeeService.getEmployeeSalaryDTOByEmployeeId(id);
        if(emp == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Employee Salary not found");
        }
        return ResponseEntity.ok().cacheControl(CacheControl.maxAge(30, TimeUnit.SECONDS).cachePrivate()).body(emp);
    }

    @RequestMapping(value = "/employees/{id}/salaries/{salaryId}", method = RequestMethod.GET)
    public ResponseEntity<EmployeeSalaryDTO> getEmployeeSalary(@PathVariable Long id, @PathVariable Long salaryId) {
        EmployeeSalaryDTO emp = employeeService.getEmployeeSalaryDTOById(salaryId);
        if(emp == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Employee Salary not found");
        }
        return ResponseEntity.ok().cacheControl(CacheControl.maxAge(30, TimeUnit.SECONDS).cachePrivate()).body(emp);
    }

    @RequestMapping(value = "/employees/{employeeId}/salaries", method = RequestMethod.POST)
    public ResponseEntity<EmployeeSalaryDTO> createEmployeeSalary(@PathVariable Long employeeId, @Valid @RequestBody EmployeeSalaryDTO employeeSalaryDTO) {
        employeeSalaryDTO.setId(null);
        return ResponseEntity.ok().cacheControl(CacheControl.maxAge(30, TimeUnit.SECONDS).cachePrivate()).body(employeeService.createEmployeeSalary(employeeSalaryDTO, employeeId));
    }

    @RequestMapping(value = "/employees/{employeeId}/salaries/{id}", method = RequestMethod.PUT)
    public ResponseEntity<EmployeeSalaryDTO> updateEmployeeSalary(@PathVariable Long employeeId, @PathVariable Long id, @Valid @RequestBody EmployeeSalaryDTO employeeSalaryDTO) {
        employeeSalaryDTO.setId(id);
        return ResponseEntity.ok().cacheControl(CacheControl.maxAge(30, TimeUnit.SECONDS).cachePrivate()).body(employeeService.updateEmployeeSalary(employeeSalaryDTO, employeeId));
    }
}
