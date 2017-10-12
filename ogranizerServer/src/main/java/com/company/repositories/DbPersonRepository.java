package com.company.repositories;

import com.company.models.Person;
import org.springframework.data.repository.CrudRepository;

public interface DbPersonRepository extends CrudRepository<Person, Integer> {
}
