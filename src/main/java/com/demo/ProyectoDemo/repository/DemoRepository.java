package com.demo.ProyectoDemo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.demo.ProyectoDemo.entity.DemoEntity;

public interface DemoRepository extends JpaRepository<DemoEntity, Integer> {
	
}
