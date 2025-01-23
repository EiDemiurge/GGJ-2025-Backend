package cababble;

import ggj.event.model.api.Event;
import ggj.event.process.EventQueue;

import java.util.Scanner;
import java.util.function.Consumer;
import java.util.function.Function;

public class DataChannel<I extends Event, O extends Event> implements Runnable {
    private EventQueue<O> engineQueue;
    private EventQueue<I> clientQueue;

    private final Function<String, I> eventParser;
    private final Function<O, String> outputParser;
    private final Consumer<String> output;
    private final Scanner scanner = new Scanner(System.in);

    public DataChannel(Consumer<String> output,
                       Function<String, I> eventParser,
                       Function<O, String> outputParser) {
        this.eventParser = eventParser;
        this.outputParser = outputParser;
        this.output = output;
    }

    public EventQueue<I> start(EventQueue<O> engineQueue) {

        this.clientQueue = new EventQueue<>();
        this.engineQueue = engineQueue;

        new Thread(this).start();
        new Thread(()->{
            while (true) {
                O o = null;
                try {
                    o = this.engineQueue.take();
                    if (isExitSignal(o)){
                        exit();
                        return;
                    }
                    String toSend = outputParser.apply(o);
                    output.accept(toSend);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }).start();

       return clientQueue;
    }

    private boolean isExitSignal(O o) {
        return false;
    }

    private boolean isExitEvent(I event) {
        return false;
    }
    private void exit() {
        output.accept("Java Exits!");
        System.exit(0);
    }

    //for testing purposes!
    public Scanner getScanner() {
        return scanner;
    }

    @Override
    public void run() {
        while (true) {
            String input = scanner.next();
            try {
                I event = eventParser.apply(input);
                if (isExitEvent(event)){
                    exit();
                    return;
                } else
                if (event != null) {
                    clientQueue.put(event);
                }
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

}
