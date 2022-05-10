package com.out.android.domain.repo;

import com.out.android.domain.entity.Problem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProblemRepo extends JpaRepository<Problem, Long> {
}
