package ggj.event.model;

import ggj.event.build.Events;
import ggj.event.model.api.Event;
import ggj.event.model.api.EventType;

public enum User_Event implements EventType {
    MSG_SENT("roomId", "message"),
    RE_ROLL,
    ROOM_SWITCH("roomId"),

;
    String[] stdKeys;

    User_Event(String... stdKeys) {
        this.stdKeys = stdKeys;
    }

    @Override
    public String[] stdKeys() {
        return stdKeys;
    }

    public record UserEvent(User_Event type, Events.EventArgs args) implements Event<User_Event> {

    }
}
