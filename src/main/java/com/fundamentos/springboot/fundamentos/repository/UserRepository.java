package com.fundamentos.springboot.fundamentos.repository;

import com.fundamentos.springboot.fundamentos.pojo.UserPojo;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserPojo, String> {
    @Query("SELECT u FROM UserPojo u WHERE u.email = :email")
    Optional<UserPojo> findUserByEmail(String email);

    @Query("SELECT u FROM UserPojo u WHERE u.email like :email%")
    List<UserPojo> findAndSort(String email, Sort sort);

    List<UserPojo> findByEmailAndAge(String email, int age);
}
