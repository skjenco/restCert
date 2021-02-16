package com.skjenco.app.repo;

import com.skjenco.app.model.EmployeeAddress;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface EmployeeAddressRepository extends PagingAndSortingRepository<EmployeeAddress, Integer> {
    EmployeeAddress findById(Long id);
}
