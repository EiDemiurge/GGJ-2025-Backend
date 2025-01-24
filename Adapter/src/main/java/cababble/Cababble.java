package cababble;

import cababble.translate.impl.FromGuiEvent;
import cababble.translate.impl.ToUserEvent;
import ggj.engine.EngineLauncher;
import ggj.event.process.EventQueue;

import java.util.Scanner;

import static ggj.event.model.Gui_Event.*;
import static ggj.event.model.User_Event.*;

public class Cababble {
    public static void main(String[] args) {
        EventQueue<GuiEvent> guiEventQueue = EngineLauncher.launch();

        DataChannel<UserEvent, GuiEvent> channel = new DataChannel<>(System.out::println,
                new ToUserEvent(), new FromGuiEvent());
        EventQueue<UserEvent> userEvents = channel.start(guiEventQueue);

        channel.setExitSignalPredicate(e -> e.type() == EXIT);
        channel.setExitEventPredicate(e -> e.type() == GAME_EXIT);

        //kill if idle?

        if (args.length>0){
            EngineLauncher.mockClientQueue();
            testUserEventsViaString(channel.getScanner());
            testUserEvents(userEvents);
        } else {

            EngineLauncher.clientQueue(userEvents);
        }
    }

    private static void testUserEventsViaString(Scanner scanner) {
        // System.in.transferTo()
    }

    private static void testUserEvents(EventQueue<UserEvent> userEvents) {
        //some scenario
    }
}
