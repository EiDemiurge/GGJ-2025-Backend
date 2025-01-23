package ggj.event.model;

import ggj.event.build.Events;
import ggj.event.model.api.Event;
import ggj.event.model.api.EventType;

public enum Game_Event implements EventType {

    MSG_SENT_SYSTEM,
    MSG_SENT_PLAYER("roomId", "message"),
    MSG_SENT_NPC("userId", "roomId", "message"),
    MSG_SENT_DM,

    UPVOTE, //TODO can player also do that? maybe they have their limit..?
    DOWNVOTE,

    TASK_DONE,
    TASK_NEW,
    TASK_FAIL,

    BAN_NPC,
    BAN_PLAYER,

    EXIT_NPC,
    ENTER_NPC,
    ENTER_PLAYER,

    AGENCY_DELTA,

    ;
    String[] stdKeys;

    Game_Event(String... stdKeys) {
        this.stdKeys = stdKeys;
    }

    @Override
    public String[] stdKeys() {
        return stdKeys;
    }

    public record GameEvent(Game_Event type, Events.EventArgs args) implements Event<Game_Event> {

    }
}
