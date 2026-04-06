package com.finance.Finance_dashboard.repository;

import com.finance.Finance_dashboard.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {}
