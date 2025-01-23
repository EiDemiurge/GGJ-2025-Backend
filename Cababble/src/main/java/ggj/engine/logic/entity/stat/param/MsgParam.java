package ggj.engine.logic.entity.stat.param;

import ggj.engine.logic.entity.stat.Parameter;

import java.util.function.Function;

import static ggj.engine.logic.model.ModelChat.ChatUserMessage;

public enum MsgParam implements Parameter<ChatUserMessage> {

    USER_ID(ChatUserMessage::userId),
    ROOM_ID(ChatUserMessage::roomId),
    UPVOTES(ChatUserMessage::upvotes),
    DOWNVOTES(ChatUserMessage::downvotes),
    ;
    private final Function<ChatUserMessage, Integer> getter;

    MsgParam(Function<ChatUserMessage, Integer> getter) {
        this.getter = getter;
    }

    @Override
    public int value(ChatUserMessage model) {
        return getter.apply(model);
    }
}
