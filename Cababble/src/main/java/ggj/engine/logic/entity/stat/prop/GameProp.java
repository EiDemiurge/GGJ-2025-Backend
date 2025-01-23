package ggj.engine.logic.entity.stat.prop;

import ggj.engine.logic.entity.stat.Property;

import java.util.function.Function;

import static ggj.engine.logic.model.ModelChat.ChatGame;

public enum GameProp implements Property<ChatGame> {

    BASE_MESSAGE(ChatGame::base),
    // ROLE,
    // AVATAR,
    ;
    private final Function<ChatGame, String> getter;

    GameProp(Function<ChatGame, String> getter) {
        this.getter = getter;
    }

    @Override
    public String value(ChatGame model) {
        return getter.apply(model);
    }
}
