package ggj.event.process;

import ggj.event.model.api.Event;
import ggj.event.process.handle.EventHandler;

public class QueueProcessor<E extends Event> implements Runnable {

    private final EventQueue<E> queue;
    private final EventHandler<E> handler;
    private boolean stopped = false;

    public QueueProcessor(EventQueue<E> queue, EventHandler<E> handler) {
        this.queue = queue;
        this.handler = handler;
    }

    @Override
    public void run() {
        while (!stopped) {
            try {
                E take = queue.take();
                if (!stopped) {
                    try{
                        handler.accept(take);
                    }catch(Exception e){
                        e.printStackTrace();
                    }
                }
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void stop() {
        stopped = true;
    }

    public EventQueue<E> getQueue() {
        return queue;
    }

    public QueueProcessor<E> copyWith(EventQueue<E> queue) {
        return new QueueProcessor<>(queue, handler);
    }
}
