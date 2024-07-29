package com.study.springboot.jpa;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {
	@Query("SELECT m FROM JPAMEMBER03 m WHERE m.name LIKE :name1 ORDER BY m.id DESC")
	List<Member> findMembers(@Param("name1") String name2);

	@Query("SELECT m FROM JPAMEMBER03 m WHERE m.name LIKE :name1")
	List<Member> findMembers(@Param("name1") String name2, Sort sort);

	@Query("SELECT m FROM JPAMEMBER03 m WHERE m.name LIKE :name1")
	Page<Member> findMembers(@Param("name1") String name2, Pageable pageable);

	@Query(value = "SELECT * FROM JPAMEMBER03 WHERE name LIKE :name1 ORDER BY id DESC",
			nativeQuery = true)
	List<Member> findMembersNative(@Param("name1") String name2);
	

}
