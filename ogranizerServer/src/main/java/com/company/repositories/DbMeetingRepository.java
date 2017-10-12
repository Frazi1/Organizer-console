package com.company.repositories;

import com.company.models.events.Event;
import com.company.models.events.Meeting;
import org.springframework.data.repository.CrudRepository;

public interface DbMeetingRepository extends CrudRepository<Meeting, Integer> { }
