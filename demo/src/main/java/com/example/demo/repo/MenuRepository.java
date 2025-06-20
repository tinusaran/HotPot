package com.example.demo.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Menu;

@Repository
public interface MenuRepository extends JpaRepository<Menu, Integer> {
    List<Menu> findByMenSpeciality(String menSpeciality);
}
