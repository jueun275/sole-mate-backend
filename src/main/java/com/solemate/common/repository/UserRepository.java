package com.solemate.common.repository;

import com.solemate.common.domain.User;
import org.aspectj.weaver.ast.And;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUserId(String userId);
    User findByUserIdAndUserPassword(String userId, String usePw);
}
