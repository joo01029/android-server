package com.out.android.domain.repo;

import com.out.android.domain.entity.Problem;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ProblemRepo extends JpaRepository<Problem, Long> {
	@EntityGraph(attributePaths = {"user"})
	Optional<Problem> findById(Long id);
	@Override
	@EntityGraph(attributePaths = {"user"})
	List<Problem> findAll();
}
