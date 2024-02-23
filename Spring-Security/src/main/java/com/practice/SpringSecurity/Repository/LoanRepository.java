package com.practice.SpringSecurity.Repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.practice.SpringSecurity.Entity.Loans;

@Repository
public interface LoanRepository extends CrudRepository<Loans, Long> {

	List<Loans> findByCustomerIdOrderByStartDtDesc(int customerId);

}