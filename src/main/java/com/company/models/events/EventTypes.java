package com.company.models.events;

public enum EventTypes {

    BirthDayEvent {
        @Override
        public String toString() {
            return "Birthday";
        }
    },

    MeetingEvent {
        @Override
        public String toString() {
            return "Meeting";
        }
    };
}
