package ggj.engine.logic.entity.action.impl;

import ggj.engine.logic.entity.action.ActionProvider;
import ggj.engine.logic.entity.action.ActionType;
import ggj.engine.logic.entity.action.Actions;
import ggj.engine.rpg.model.ModelRpg;

import static ggj.engine.logic.entity.action.Actions.action;

public class ScriptActionProvider implements ActionProvider {
    int step = 0;
    public static final Actions.Action[] scriptedAction = {

            action(ActionType.MESSAGE).stdKeys().values("1|0|Admin is a muggle").build(),
            action(ActionType.WAIT).build(),
            //WAIT action?
            action(ActionType.MESSAGE).stdKeys().values("3|1|Admin is a WIZARD").build(),

    };

    @Override
    public Actions.Action getNextAction(Actions.Action previous, ModelRpg.RpgUser data) {
        if (step >= scriptedAction.length) {
            throw new RuntimeException("No more actions in the script!");
        }
        return scriptedAction[step++];
    }
}
