package com.practice.SpringSecurity.Repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Repository;

import com.practice.SpringSecurity.Entity.Accounts;

@Repository
public interface AccountsRepository extends CrudRepository<Accounts, Long> {
	@PreAuthorize("hasAuthority('VIEW_ACCOUNT')")
	Accounts findByCustomerId(int customerId);
}