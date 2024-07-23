package com.jwt.First.repository;

import com.jwt.First.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;
public interface UserRepository extends JpaRepository<User,String> {

    public Optional<User> findByEmail(String email);
}
