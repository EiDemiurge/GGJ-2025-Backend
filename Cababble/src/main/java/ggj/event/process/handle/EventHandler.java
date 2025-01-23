package ggj.event.process.handle;

import ggj.event.model.api.Event;

import java.util.function.Consumer;

public interface EventHandler<E extends Event>  extends Consumer<E> {
}
