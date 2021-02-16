package com.skjenco.app.repo;

import com.skjenco.app.model.EmployeePhone;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface EmployeePhoneRepository extends PagingAndSortingRepository<EmployeePhone, Integer> {
    EmployeePhone findById(Long id);
}
