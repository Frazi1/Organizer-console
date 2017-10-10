package com.company.repositories;

import com.company.models.events.Event;
import org.springframework.data.repository.CrudRepository;

public interface DbEventsRepository extends CrudRepository<Event, Integer> { }
