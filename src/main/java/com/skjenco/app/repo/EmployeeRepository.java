package com.skjenco.app.repo;

import java.util.List;

import com.skjenco.app.model.Employee;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface EmployeeRepository extends PagingAndSortingRepository<Employee, Integer> {
    List<Employee> findByName(String name);

    List<Employee> findByOrgSupervisorIdOrderByNameDesc(Long supervisorId);

    List<Employee> findByOrgSupervisorIdOrderByNameAsc(Long supervisorId);

    Employee findById(long id);
}
