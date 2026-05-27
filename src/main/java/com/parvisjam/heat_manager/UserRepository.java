package com.parvisjam.heat_manager;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<CustomUser, Integer> {

    Optional<CustomUser> findByName(String name);
}
