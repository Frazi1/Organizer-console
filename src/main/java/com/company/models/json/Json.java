package com.company.models.json;

import com.company.models.events.Event;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class Json {
    public static Gson getGson(){
        return new GsonBuilder()
                    .registerTypeAdapter(Event.class, new EventAdapter())
                    .setPrettyPrinting()
                    .create();
    }
}
