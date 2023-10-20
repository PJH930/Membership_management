package com.example.MM_project.repository;

import com.example.MM_project.entity.Membership;
import org.springframework.data.repository.CrudRepository;

import java.util.ArrayList;

public interface MembershipRepository extends CrudRepository<Membership, Long> {
    @Override
    ArrayList<Membership> findAll();
}
