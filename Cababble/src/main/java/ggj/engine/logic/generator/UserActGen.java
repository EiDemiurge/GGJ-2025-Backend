package ggj.engine.logic.generator;

import ggj.engine.logic.entity.User;
import ggj.engine.logic.entity.action.Actions;
import ggj.engine.rpg.model.ModelRpg;

import java.util.function.Supplier;

/*
A naive impl at least - what does user do over time?
 */
public class UserActGen { //interface and mock
    /*
    state - last action, ...
    all RPG data is passed via events so that the supplier will have updated READ ONLY data for us
     */
    Supplier<ModelRpg.RpgUser> userData;
    ModelRpg.RpgUser data;
    Actions.Action previousAction;

    public boolean update() {
        // data = userData.get();
        // return data != null;
        return true;
    }

    public Actions.Action getPreviousAction() {
        return previousAction;
    }

    public ModelRpg.RpgUser getData() {
        return data;
    }
}
