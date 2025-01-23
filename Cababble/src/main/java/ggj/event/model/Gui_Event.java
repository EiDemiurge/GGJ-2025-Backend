package ggj.event.model;

import ggj.event.build.Events;
import ggj.event.model.api.Event;
import ggj.event.model.api.EventType;

public enum Gui_Event implements EventType {

    CHAT_MSG_APPEND,
    CHAT_MSG_UPVOTED,
    CHAT_MSG_DOWNVOTED,

    USER_BANNED,
    USER_ENTERED,
    USER_CHANGED,

    PLAYER_BANNED,
    PLAYER_ENTERED,

    BASE_MESSAGE_ROLLED,
    AGENCY_CHANGED,

    TASK_DONE,
    TASK_FAIL,
    TASK_NEW,

    INFO_CHANGED,

;
    String[] stdKeys;

    Gui_Event(String... stdKeys) {
        this.stdKeys = stdKeys;
    }

    @Override
    public String[] stdKeys() {
        return stdKeys;
    }

    public record GuiEvent(Gui_Event type, Events.EventArgs args) implements Event<Gui_Event> {

    }

}
