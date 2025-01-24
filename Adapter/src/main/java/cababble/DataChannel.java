package cababble;

import ggj.event.model.api.Event;
import ggj.event.process.EventQueue;

import java.util.Scanner;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

public class DataChannel<I extends Event, O extends Event> implements Runnable {
    private EventQueue<O> engineQueue;
    private EventQueue<I> clientQueue;

    private final Function<String, I> eventParser;
    private final Function<O, String> outputParser;
    private final Consumer<String> output;
    private final Scanner scanner = new Scanner(System.in);
    private Predicate<I> exitSignalPredicate;
    private Predicate<O> exitEventPredicate;

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
                    if (isExitEvent(o)){
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

    private boolean isExitEvent(O event) {
        if (exitEventPredicate != null) {
            return exitEventPredicate.test(event);
        }
        return false;
    }

    private boolean isExitSignal(I signal) {
        if (exitSignalPredicate != null) {
            return exitSignalPredicate.test(signal);
        }
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
            String input = scanner.nextLine();
            try {
                I event = eventParser.apply(input);
                if (isExitSignal(event)){
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

    public void setExitEventPredicate(Predicate<O> exitEventPredicate) {
        this.exitEventPredicate = exitEventPredicate;
    }

    public void setExitSignalPredicate(Predicate<I> exitSignalPredicate) {
        this.exitSignalPredicate = exitSignalPredicate;
    }
}
