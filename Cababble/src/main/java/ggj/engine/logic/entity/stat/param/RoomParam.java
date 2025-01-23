package ggj.engine.logic.entity.stat.param;

import ggj.engine.logic.entity.stat.Parameter;

import java.util.function.Function;

import static ggj.engine.logic.model.ModelChat.ChatRoom;

public enum RoomParam implements Parameter<ChatRoom> {


    ;
    private final Function<ChatRoom, Integer> getter;

    RoomParam(Function<ChatRoom, Integer> getter) {
        this.getter = getter;
    }

    @Override
    public int value(ChatRoom model) {
        return getter.apply(model);
    }
}
