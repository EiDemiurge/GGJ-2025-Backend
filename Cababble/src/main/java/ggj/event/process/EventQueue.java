package ggj.event.process;

import ggj.event.model.api.Event;

import java.util.concurrent.LinkedBlockingDeque;

public class EventQueue<E extends Event> extends LinkedBlockingDeque<E> {

    @Override
    public E take() throws InterruptedException {
        return super.take();
    }
}
