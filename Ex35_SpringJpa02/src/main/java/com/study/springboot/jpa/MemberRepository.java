package com.study.springboot.jpa;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {
	Optional<Member> findByName(String keyword);
	Optional<Member> findByEmail(String keyword);
	
	List<Member> findByNameLike(String keyword);
	List<Member> findByNameLikeOrderByNameDesc(String keyword);
	List<Member> findByNameLikeOrderByNameAscEmailDesc(String keyword);
	
	List<Member> findByNameLike(String keyword, Sort sort);

}
