package ggj.engine.logic.entity.stat.prop;

import ggj.engine.logic.entity.stat.Property;
import ggj.engine.logic.model.ModelChat;

import java.util.function.Function;

import static ggj.engine.logic.model.ModelChat.*;

public enum UserProp implements Property<ChatUser> {

    NAME(ChatUser::name),
    // ROLE,
    // AVATAR,
    ;
    private final Function<ChatUser, String> getter;

    UserProp(Function<ChatUser, String> getter) {
        this.getter = getter;
    }

    @Override
    public String value(ChatUser model) {
        return getter.apply(model);
    }
}
