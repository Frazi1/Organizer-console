package com.company.models.json;

import com.company.models.Person;
import com.company.models.events.Birthday;
import com.company.models.events.Event;
import com.company.models.events.Meeting;
import com.google.gson.*;
import com.google.gson.annotations.JsonAdapter;

import java.lang.reflect.Type;
import java.time.LocalDate;
import java.util.Date;

public class EventAdapter implements JsonSerializer<Event>, JsonDeserializer<Event> {
    private final String DESCRIPTION = "description";
    private final String DATE = "date";
    private final String PERSON = "person";
    private final String PRESENT = "present";
    private final String BIRTH_HOUR = "birthHour";
    private final String ID = "id";

    @Override
    public Event deserialize(JsonElement jsonElement,
                               Type type,
                               JsonDeserializationContext context)
                                throws JsonParseException {
        JsonObject object = jsonElement.getAsJsonObject();
        Event result = null;

        JsonElement present = object.get(PRESENT);
        if(present != null){
            Birthday birthday = new Birthday();
            birthday.setPresent(context.deserialize(present, String.class));
            birthday.setBirthHour(context.deserialize(
                    object.get(BIRTH_HOUR), Integer.class)
            );
            result = birthday;
        }
        else {
            result = new Meeting();
        }

        result.setId(context.deserialize(
                object.get(ID), Integer.class)
        );

        result.setDate(context.deserialize(
                        object.get(DATE), LocalDate.class
                )
        );
        result.setDescription(context.deserialize(
                object.get(DESCRIPTION), String.class)
        );
        result.setPerson(context.deserialize(
                object.get(PERSON), Person.class
        ));

        return result;

    }

    @Override
    public JsonElement serialize(Event event, Type type,
                                 JsonSerializationContext context) {
        JsonObject result = new JsonObject();

        result.add(ID,
                context.serialize(event.getId(), Integer.class));

        result.add(PERSON,
                context.serialize(event.getPerson(), Person.class));

        result.add(DATE,
                context.serialize(event.getDate(), LocalDate.class));

        result.add(DESCRIPTION,
                context.serialize(event.getDescription(), String.class));

        if(event instanceof Birthday){
            Birthday birthday = (Birthday) event;
            result.add(PRESENT,
                    context.serialize(birthday.getPresent(), String.class));
            result.add(BIRTH_HOUR,
                    context.serialize(birthday.getBirthHour(), Integer.class));
        }
        return result;
    }
}
