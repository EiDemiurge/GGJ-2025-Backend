package ggj.event.build;

import ggj.event.model.api.Event;
import ggj.event.model.api.EventType;
import ggj.util.datastruct.StringMap;

import java.util.function.BiFunction;

public class EventBuilder<T extends EventType> {

    private final T type;
    private BiFunction<T, Events.EventArgs, Event<T>> creator;
    private String[] keys;
    private Object[] values;
    private Events.EventArgs args;

    public EventBuilder<T> creator(BiFunction<T, Events.EventArgs, Event<T>> creator) {
        this.creator = creator;
        return this;
    }

    public EventBuilder<T> args(Events.EventArgs args) {
        this.args = args;
        return this;
    }

    public EventBuilder<T> keys(String... keys) {
        this.keys = keys;
        return this;
    }

    public EventBuilder<T> values(Object... values) {
        this.values = values;
        return this;
    }

    public Event<T> build() {
        initArgs();
        return creator.apply(type, args);
    }

    public void fire() {
        Events.fire(build());
    }

    private void initArgs() {
        if (keys !=null && values !=null){
            this.args = new Events.EventArgs(new StringMap(keys, values));
        }
        if (args == null){
            this.args = new Events.EmptyEventArgs();
        }
    }

    public EventBuilder(T type) {
        this.type = type;
    }
}
