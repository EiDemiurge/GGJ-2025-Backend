package ggj.engine.logic.entity.action;

import ggj.event.model.Game_Event;

public enum ActionType {

    MESSAGE(Game_Event.MSG_SENT_NPC),

    UPVOTE(Game_Event.UPVOTE),
    DOWNVOTE(Game_Event.DOWNVOTE),
    UPVOTE_PLAYER(Game_Event.UPVOTE),
    DOWNVOTE_PLAYER(Game_Event.UPVOTE),

    BAN(Game_Event.BAN_PLAYER),
    BAN_PLAYER(Game_Event.BAN_PLAYER),
    EXIT(Game_Event.EXIT_NPC),
    WAIT(null);
    Game_Event linkedEvent;

    ActionType(Game_Event linkedEvent) {
        this.linkedEvent = linkedEvent;
    }
}
