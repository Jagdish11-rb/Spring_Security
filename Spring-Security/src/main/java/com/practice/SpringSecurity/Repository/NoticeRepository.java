package com.practice.SpringSecurity.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.practice.SpringSecurity.Entity.Notice;

@Repository
public interface NoticeRepository extends CrudRepository<Notice, Long> {

	@Query(value = "select * from notice_details",nativeQuery = true)
	List<Notice> findAllActiveNotices();

}