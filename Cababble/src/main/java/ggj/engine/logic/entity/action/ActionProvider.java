package ggj.engine.logic.entity.action;

import ggj.engine.rpg.model.ModelRpg;

public interface ActionProvider {

    Actions.Action getNextAction(Actions.Action previous, ModelRpg.RpgUser data);
}
