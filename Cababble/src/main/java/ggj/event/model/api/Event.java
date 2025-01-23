package ggj.event.model.api;

import ggj.event.build.Events;

public interface Event<E extends EventType> {

    Events.EventArgs args();
    E type();

}
