package com.company.repositories;

import com.company.models.events.Birthday;
import org.springframework.data.repository.CrudRepository;

public interface DbBirthdayRepository extends CrudRepository<Birthday, Integer> {
}
