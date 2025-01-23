package ggj.event.build;

import ggj.event.model.Engine_Event;
import ggj.event.model.Engine_Event.EngineEvent;

import ggj.event.model.Game_Event;
import ggj.event.model.Game_Event.GameEvent;

import ggj.event.model.User_Event;
import ggj.event.model.User_Event.UserEvent;

import ggj.event.model.Gui_Event;
import ggj.event.model.Gui_Event.GuiEvent;
import ggj.event.model.api.Event;
import ggj.event.model.api.EventType;
import ggj.event.process.EventQueue;
import ggj.util.Format;
import ggj.util.Log;
import ggj.util.datastruct.StringMap;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.BlockingQueue;
import java.util.function.BiFunction;
import java.util.stream.Collectors;

import static ggj.util.Format.*;

// @Getter for static fields?
@SuppressWarnings({"rawtypes", "unchecked"})
public class Events {

    private static final Map<Class, EventQueue> queueMap = new HashMap<>();


    //add block() to wait until handled?

    public static <T extends EventType> void fire(Event<T> event) {
        validateEvent(event);
        try {
            queueMap.get(event.getClass()).put(event);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        Log.info("Added to queue: " + event(event));
    }

    public static <T extends EventType> void validateEvent(Event<T> event) {
        if (!event.args().kwargs.keySet().containsAll(
                Arrays.stream(event.type().stdKeys()).map(String::toLowerCase).collect(Collectors.toSet())
        )) {
            throw new RuntimeException("Invalid event: %s, required keys: %s"
                    .formatted(array(event.args().kwargs.keySet().toArray(new String[0])),
                            array(event.type().stdKeys())));
        }
    }

    public static <E extends Event<?>> EventQueue<E> newQueue(Class<E> clazz) {
        return newQueue(clazz, new EventQueue<>());
    }

    public static <E extends Event<?>> EventQueue<E> newQueue(Class<E> clazz, EventQueue<E> queue) {
        queueMap.put(clazz, queue);
        return queue;
    }

    public static EventBuilder<Engine_Event> engine(Engine_Event type) {
        return builder(type, EngineEvent::new);
    }

    public static EventBuilder<User_Event> user(User_Event type) {
        return builder(type, UserEvent::new);
    }

    public static EventBuilder<Game_Event> game(Game_Event type) {
        return builder(type, GameEvent::new);
    }

    public static EventBuilder<Gui_Event> gui(Gui_Event type) {
        return builder(type, GuiEvent::new);
    }

    private static <E extends EventType> EventBuilder<E> builder(E type, BiFunction<E, EventArgs, Event<E>> creator) {
        return new EventBuilder<>(type).creator(creator);
    }


    public static class EventArgs {
        public final StringMap kwargs;

        public EventArgs(StringMap kwargs) {
            this.kwargs = kwargs;
        }

        public String string(String key) {
            return kwargs.get(key).toString();
        }

        //should go into StringMapGeneric?
        public Integer num(String key) {
            // optional?
            return Integer.valueOf(kwargs.get(key).toString());
        }

        @Override
        public String toString() {
            return map(kwargs);
        }

        public void put(String key, Object value) {
            kwargs.put(key, value);
        }
    }

    public static class EmptyEventArgs extends EventArgs {
        public EmptyEventArgs() {
            super(new StringMap());
        }
    }
}
