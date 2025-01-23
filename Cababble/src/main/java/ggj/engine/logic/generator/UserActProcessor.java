package ggj.engine.logic.generator;

import ggj.engine.logic.entity.action.ActionProvider;
import ggj.util.meta.Inject;

import java.util.List;

import static ggj.engine.logic.entity.action.Actions.*;
import static ggj.engine.logic.entity.action.impl.ScriptActionProvider.scriptedAction;

public class UserActProcessor {

    private static List<UserActGen> userGens;
    private static ActionProvider actionsProvider;

    @Inject
    public static void setup(ActionProvider actionsProvider) {
        UserActProcessor.actionsProvider = actionsProvider;
    }

    public static void gameStarted(List<UserActGen> userGens) {
        UserActProcessor.userGens = userGens;
        start();
    }

    public static void start() {
        //TODO does it ever pause? it should be able to exit for main menu e.g.
        // while (true) {
        for (int i = 0; i < scriptedAction.length; i++) {
            updateGenerators();
            process();
        }
        // }
    }

    private static void updateGenerators() {
        userGens.removeIf(gen -> !gen.update());
    }

    public static void process() {
        for (UserActGen userGen : userGens) {
            Action action = actionsProvider.getNextAction(
                    userGen.getPreviousAction(), userGen.getData());
            handle(action);
        }
    }
}
