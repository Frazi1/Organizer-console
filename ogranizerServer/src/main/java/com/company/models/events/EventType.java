package com.company.models.events;

public enum EventType {
    Meeting {
        @Override
        public String toString() {
            return "Meeting";
        }
    },
    Birthday {
        @Override
        public String toString() {
            return "Birthday";
        }
    }
}
