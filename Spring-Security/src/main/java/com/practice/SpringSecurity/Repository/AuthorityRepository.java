package com.practice.SpringSecurity.Repository;

import com.practice.SpringSecurity.Entity.AccountTransactions;
import com.practice.SpringSecurity.Entity.Authority;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AuthorityRepository extends CrudRepository<Authority, Long> {

	@Query(value="select name from authorities where customer_id =:id",nativeQuery = true)
	List<String> findAuthoritiesById(@Param("id") int id);

}