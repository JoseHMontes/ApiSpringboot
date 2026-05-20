package com.CrudProject.API.repository;

import com.CrudProject.API.models.Person;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IPerson extends JpaRepository<Person, Long> {
}
