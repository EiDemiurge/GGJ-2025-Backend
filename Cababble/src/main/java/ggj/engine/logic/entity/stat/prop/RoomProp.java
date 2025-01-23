package ggj.engine.logic.entity.stat.prop;

import ggj.engine.logic.entity.stat.Property;

import java.util.function.Function;

import static ggj.engine.logic.model.ModelChat.ChatRoom;

public enum RoomProp implements Property<ChatRoom> {

    NAME(ChatRoom::name),
    ICON(ChatRoom::icon),
    ;
    private final Function<ChatRoom, String> getter;

    RoomProp(Function<ChatRoom, String> getter) {
        this.getter = getter;
    }

    @Override
    public String value(ChatRoom model) {
        return getter.apply(model);
    }
}
