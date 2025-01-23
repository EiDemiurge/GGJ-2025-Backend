package ggj.engine.logic.entity.action;

import ggj.event.build.Events;
import ggj.event.model.Game_Event;

public class Actions {

    public static ActionBuilder action(ActionType type){
        return new ActionBuilder(type);

    }

    public static void handle(Action action) {
        if (action.eventType == null) {
            //TODO dirty!
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        } else {
            Events.game(action.eventType()).keys(action.keys()).values(action.values()).fire();
        }
    }

    public record Action(Game_Event eventType, String[] keys, Object... values){

    }
}
