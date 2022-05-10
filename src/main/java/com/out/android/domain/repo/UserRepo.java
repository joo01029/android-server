package com.out.android.domain.repo;

import com.out.android.domain.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepo extends JpaRepository<User, Long> {
	Optional<User> findById(String id);
	Optional<User> findByIdx(Long idx);
}
