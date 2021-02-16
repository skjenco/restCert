package com.skjenco.app;

import com.skjenco.app.dto.EmployeeAddressDTO;
import com.skjenco.app.dto.EmployeeDTO;
import com.skjenco.app.dto.EmployeePhoneDTO;
import com.skjenco.app.dto.EmployeeSalaryDTO;
import com.skjenco.app.model.Employee;
import com.skjenco.app.model.EmployeeAddress;
import com.skjenco.app.model.EmployeePhone;
import com.skjenco.app.model.EmployeeSalary;
import com.skjenco.app.repo.EmployeeRepository;
import com.skjenco.app.repo.EmployeeAddressRepository;
import com.skjenco.app.repo.EmployeePhoneRepository;
import com.skjenco.app.repo.EmployeeSalaryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@Service
public class EmployeeService {
    @Autowired
    EmployeeRepository employeeRepository;
    @Autowired
    EmployeeAddressRepository employeeAddressRepository;
    @Autowired
    EmployeePhoneRepository employeePhoneRepository;
    @Autowired
    EmployeeSalaryRepository employeeSalaryRepository;
    private static final String EMP_NOT_FOUND = "Employee not found";

    public EmployeeDTO getEmployeeDTOById(Long id){
        return getEmployeeDTOById(id, "GET");
    }

    public EmployeeDTO getEmployeeDTOById(Long id, String operation){
        return new EmployeeDTO(getEmployeeById(id), operation);
    }

    public Employee getEmployeeById(Long id){
        return employeeRepository.findById(id);
    }

    public List<EmployeeDTO> getAllEmployees(Integer pageSize, Integer pageNum, String sort){
        List<EmployeeDTO> dtoList = new ArrayList<>(0);
        if(pageNum == null || pageSize == null) {
            pageNum = 0;
            pageSize = Integer.MAX_VALUE;
        }
        Pageable sortedByName = PageRequest.of(pageNum, pageSize, Sort.by("name"));
        if("D".equals(sort)) {
            sortedByName = PageRequest.of(pageNum, pageSize, Sort.by("name").descending());
        }
        List<Employee> empList = employeeRepository.findAll(sortedByName).toList();
        for(Employee emp : empList) {
            dtoList.add(new EmployeeDTO(emp, "GET"));
        }
        return dtoList;
    }

    public List<Employee> getEmployeeByName(String name){
        return employeeRepository.findByName(name);
    }

    public List<Employee> getEmployeeBySupervisorId(Long supervisorId) {
        return employeeRepository.findByOrgSupervisorIdOrderByNameDesc(supervisorId);
    }

    public List<EmployeeDTO> getEmployeeDTOBySupervisorId(Long supervisorId) {
        return getEmployeeDTOBySupervisorId(supervisorId, true);
    }

    public List<EmployeeDTO> getEmployeeDTOBySupervisorId(Long supervisorId, boolean sortDsc) {
        List<Employee> empList;
        List<EmployeeDTO> dtoList = new ArrayList<>(0);
        if(sortDsc) {
            empList = employeeRepository.findByOrgSupervisorIdOrderByNameDesc(supervisorId);
            for(Employee emp : empList) {
                dtoList.add(new EmployeeDTO(emp, "GET"));
            }
        } else {
            empList = employeeRepository.findByOrgSupervisorIdOrderByNameAsc(supervisorId);
            for(Employee emp : empList) {
                dtoList.add(new EmployeeDTO(emp, "GET"));
            }
        }
        return dtoList;
    }

    public void deleteEmployeeById(Long id) {
        Employee emp = getEmployeeById(id);
        if (emp == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, EMP_NOT_FOUND);
        }
        if(emp.getOrg() != null && emp.getOrg().getSupervisorId() != null) {
            updateDirectReports(id, emp.getOrg().getSupervisorId());
        }
        employeeRepository.deleteById(id.intValue());
    }

    public void updateDirectReports(Long id, Long supervisorId) {
        List<Employee> empList = getEmployeeBySupervisorId(id);
        for(Employee emp : empList) {
            emp.getOrg().setSupervisorId(supervisorId);
            employeeRepository.save(emp);
        }
    }

    public EmployeeDTO createEmployee(EmployeeDTO employee) {
        checkEmployee(employee.getSupervisorId(), HttpStatus.INTERNAL_SERVER_ERROR);
        return new EmployeeDTO(employeeRepository.save(new Employee(employee)), "CREATE");
    }

    public EmployeeDTO updateEmployee(EmployeeDTO employee) {
        checkEmployee(employee.getId(), HttpStatus.NOT_FOUND);
        checkEmployee(employee.getSupervisorId(), HttpStatus.BAD_REQUEST);
        Employee emp = getEmployeeById(employee.getId());
        if(emp != null) {
            if(emp.getOrg() != null && employee.getSupervisorId() != null) {
                emp.getOrg().setSupervisorId(employee.getSupervisorId());
            }
            if(employee.getName() != null) {
                emp.setName(employee.getName());
            }
        }
        return new EmployeeDTO(employeeRepository.save(emp), "UPDATE");
    }

    public List<EmployeeDTO> getDirectReports(Long id) {
        List<EmployeeDTO> dtoList = new ArrayList<>(0);
        Long currentId = id;
        while(currentId != null) {
            Employee e = employeeRepository.findById(currentId);
            if(e != null) {
                dtoList.add(new EmployeeDTO(e, "REPORTS"));
                if(e.getOrg() != null) {
                    currentId = e.getOrg().getSupervisorId();
                } else {
                    currentId = null;
                }
            }
        }
        return dtoList;
    }

    private void checkEmployee(Long id, HttpStatus status) {
        if(id != null && getEmployeeDTOById(id) == null) {
            throw new ResponseStatusException(status, EMP_NOT_FOUND);
        }
    }

    //Employee Address
    public List<EmployeeAddressDTO> getEmployeeAddressDTOByEmployeeId(Long id){
        Employee emp = getEmployeeById(id);
        if(emp != null) {
            if(emp.getEmployeeAddress() != null && !emp.getEmployeeAddress().isEmpty()) {
                List<EmployeeAddressDTO> addrList = new ArrayList<>(0);
                for(EmployeeAddress empAddr : emp.getEmployeeAddress()) {
                    addrList.add(new EmployeeAddressDTO(empAddr));
                }
                return addrList;
            } else {
                return null;
            }
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, EMP_NOT_FOUND);
    }

    public EmployeeAddressDTO getEmployeeAddressDTOById(Long id){
        return new EmployeeAddressDTO(employeeAddressRepository.findById(id));
    }

    public EmployeeAddressDTO createEmployeeAddress(EmployeeAddressDTO address, Long employeeId) {
        checkEmployee(employeeId, HttpStatus.NOT_FOUND);
        return new EmployeeAddressDTO(employeeAddressRepository.save(new EmployeeAddress(address, employeeId)));
    }

    public EmployeeAddressDTO updateEmployeeAddress(EmployeeAddressDTO address, Long employeeId) {
        checkEmployee(employeeId, HttpStatus.NOT_FOUND);
        checkEmployeeAddress(address.getId(), HttpStatus.NOT_FOUND);
        EmployeeAddressDTO dto = getEmployeeAddressDTOById(address.getId());
        if(address.getStreetAddress() != null) {
            dto.setStreetAddress(address.getStreetAddress());
        }
        if(address.getCity() != null) {
            dto.setCity(address.getCity());
        }
        if(address.getState() != null) {
            dto.setState(address.getState());
        }
        if(address.getCountry() != null) {
            dto.setCountry(address.getCountry());
        }
        if(address.getPostalCode() != null) {
            dto.setPostalCode(address.getPostalCode());
        }
        return new EmployeeAddressDTO(employeeAddressRepository.save(new EmployeeAddress(dto, employeeId)));
    }

    private void checkEmployeeAddress(Long id, HttpStatus status) {
        if(id != null && getEmployeeAddressDTOById(id) == null) {
            throw new ResponseStatusException(status, EMP_NOT_FOUND);
        }
    }

    //Employee phone
    public List<EmployeePhoneDTO> getEmployeePhoneDTOByEmployeeId(Long id){
        Employee emp = getEmployeeById(id);
        if(emp != null) {
            if(emp.getEmployeePhone() != null && !emp.getEmployeePhone().isEmpty()) {
                List<EmployeePhoneDTO> phoneList = new ArrayList<>(0);
                for(EmployeePhone empPhone : emp.getEmployeePhone()) {
                    phoneList.add(new EmployeePhoneDTO(empPhone));
                }
                return phoneList;
            } else {
                return null;
            }
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, EMP_NOT_FOUND);
    }

    public EmployeePhoneDTO getEmployeePhoneDTOById(Long id){
        return new EmployeePhoneDTO(employeePhoneRepository.findById(id));
    }

    public EmployeePhoneDTO createEmployeePhone(EmployeePhoneDTO phone, Long employeeId) {
        checkEmployee(employeeId, HttpStatus.NOT_FOUND);
        return new EmployeePhoneDTO(employeePhoneRepository.save(new EmployeePhone(phone, employeeId)));
    }

    public EmployeePhoneDTO updateEmployeePhone(EmployeePhoneDTO phone, Long employeeId) {
        checkEmployee(employeeId, HttpStatus.NOT_FOUND);
        checkEmployeePhone(phone.getId(), HttpStatus.NOT_FOUND);
        EmployeePhoneDTO dto = getEmployeePhoneDTOById(phone.getId());
        if(phone.getNumber() != null) {
            dto.setNumber(phone.getNumber());
        }
        if(phone.getType() != null) {
            dto.setType(phone.getType());
        }
        return new EmployeePhoneDTO(employeePhoneRepository.save(new EmployeePhone(dto, employeeId)));
    }

    private void checkEmployeePhone(Long id, HttpStatus status) {
        if(id != null && getEmployeePhoneDTOById(id) == null) {
            throw new ResponseStatusException(status, EMP_NOT_FOUND);
        }
    }

    //Employee salary
    public List<EmployeeSalaryDTO> getEmployeeSalaryDTOByEmployeeId(Long id){
        Employee emp = getEmployeeById(id);
        if(emp != null) {
            if(emp.getEmployeeSalary() != null && !emp.getEmployeeSalary().isEmpty()) {
                List<EmployeeSalaryDTO> salaryList = new ArrayList<>(0);
                for(EmployeeSalary empSalary : emp.getEmployeeSalary()) {
                    salaryList.add(new EmployeeSalaryDTO(empSalary));
                }
                return salaryList;
            } else {
                return null;
            }
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, EMP_NOT_FOUND);
    }

    public EmployeeSalaryDTO getEmployeeSalaryDTOById(Long id){
        return new EmployeeSalaryDTO(employeeSalaryRepository.findById(id));
    }

    public EmployeeSalaryDTO createEmployeeSalary(EmployeeSalaryDTO salary, Long employeeId) {
        checkEmployee(employeeId, HttpStatus.NOT_FOUND);
        return new EmployeeSalaryDTO(employeeSalaryRepository.save(new EmployeeSalary(salary, employeeId)));
    }

    public EmployeeSalaryDTO updateEmployeeSalary(EmployeeSalaryDTO salary, Long employeeId) {
        checkEmployee(employeeId, HttpStatus.NOT_FOUND);
        checkEmployeeSalary(salary.getId(), HttpStatus.NOT_FOUND);
        EmployeeSalaryDTO dto = getEmployeeSalaryDTOById(salary.getId());
        if(salary.getSalary() != null) {
            dto.setSalary(salary.getSalary());
        }
        return new EmployeeSalaryDTO(employeeSalaryRepository.save(new EmployeeSalary(dto, employeeId)));
    }

    private void checkEmployeeSalary(Long id, HttpStatus status) {
        if(id != null && getEmployeeSalaryDTOById(id) == null) {
            throw new ResponseStatusException(status, EMP_NOT_FOUND);
        }
    }
}
