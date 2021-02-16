package com.skjenco.app.repo;

import com.skjenco.app.model.EmployeeSalary;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface EmployeeSalaryRepository extends PagingAndSortingRepository<EmployeeSalary, Integer> {
    EmployeeSalary findById(Long id);
}
