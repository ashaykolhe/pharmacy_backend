package com.pharmacy.repository;

import com.pharmacy.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    Optional<Employee> findByUserName(String userName);

//    Optional<List<Employee>> findByMobile(String mobile);
}
