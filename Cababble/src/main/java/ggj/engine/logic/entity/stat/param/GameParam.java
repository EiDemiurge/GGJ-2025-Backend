package ggj.engine.logic.entity.stat.param;

import ggj.engine.logic.entity.stat.Parameter;

import java.util.function.Function;

import static ggj.engine.logic.model.ModelChat.*;

public enum GameParam implements Parameter<ChatGame> {

    AGENCY(ChatGame::agency),

    ;
    private final Function<ChatGame, Integer> getter;

    GameParam(Function<ChatGame, Integer> getter) {
        this.getter = getter;
    }

    @Override
    public int value(ChatGame model) {
        return getter.apply(model);
    }
}
