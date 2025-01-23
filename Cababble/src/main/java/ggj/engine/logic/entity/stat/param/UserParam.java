package ggj.engine.logic.entity.stat.param;

import ggj.engine.logic.entity.stat.Parameter;
import ggj.engine.logic.model.ModelChat;

import java.util.function.Function;

import static ggj.engine.logic.model.ModelChat.ChatUser;

public enum UserParam implements Parameter<ModelChat.ChatUser> {

    UPVOTES(ChatUser::upvotes),
    DOWNVOTES(ChatUser::downvotes),
    ROOM_ID(ChatUser::roomId)
    ;
    private final Function<ChatUser, Integer> getter;

    UserParam(Function<ChatUser, Integer> getter) {
        this.getter = getter;
    }

    @Override
    public int value(ChatUser model) {
        return getter.apply(model);
    }
}
