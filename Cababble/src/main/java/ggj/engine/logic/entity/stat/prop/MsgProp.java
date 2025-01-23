package ggj.engine.logic.entity.stat.prop;

import ggj.engine.logic.entity.stat.Property;

import java.util.function.Function;

import static ggj.engine.logic.model.ModelChat.ChatUserMessage;

public enum MsgProp implements Property<ChatUserMessage> {

    MESSAGE(ChatUserMessage::message),
    ;
    private final Function<ChatUserMessage, String> getter;

    MsgProp(Function<ChatUserMessage, String> getter) {
        this.getter = getter;
    }

    @Override
    public String value(ChatUserMessage model) {
        return getter.apply(model);
    }
}
