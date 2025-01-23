package ggj.engine.logic.entity.action;

import ggj.event.model.Game_Event;

public class ActionBuilder {

    private final ActionType type;
    private Object[] values;
    private String[] keys;
    private String keyString;

    public ActionBuilder(ActionType type) {
        this.type = type;
    }

    public ActionBuilder values(Object... values) {
        this.values = values;
        return this;
    }

    public ActionBuilder values(String valuesString) {
        this.values = valuesString.split("\\|");
        return this;
    }

    public ActionBuilder keys(String[] keys) {
        this.keys = keys;
        return this;
    }

    public ActionBuilder stdKeys() {
        this.keys = type.linkedEvent.stdKeys();
        return this;
    }

    public ActionBuilder keys(String keyString) {
        this.keyString = keyString;
        return this;
    }

    public Actions.Action build() {
        Game_Event eventType = type.linkedEvent;
        if (eventType != null) {
            if (keys == null && keyString != null) {
                keys = keyString.split("\\|");
            } else {
                stdKeys();
            }
            if (keys.length != values.length) {
                // throw new RuntimeException("Invalid action data: (keys, values)" + Format.arrays(keys, values));
            }
        }
        return new Actions.Action(eventType, keys, values);
    }
}
