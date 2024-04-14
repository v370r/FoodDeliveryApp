package com.vijay.userinfo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.vijay.userinfo.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

}
