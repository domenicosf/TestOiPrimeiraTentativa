package com.oi.internet.teste.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.oi.internet.teste.domain.Owner;

public interface OwnerRepository extends JpaRepository<Owner, Long> {

}
