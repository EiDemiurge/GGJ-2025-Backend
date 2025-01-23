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

        EngineLauncher.clientQueue(userEvents);

        if (args.length>0){
            testUserEventsViaString(channel.getScanner());
            testUserEvents(userEvents);
        }
    }

    private static void testUserEventsViaString(Scanner scanner) {
        // System.in.transferTo()
    }

    private static void testUserEvents(EventQueue<UserEvent> userEvents) {
        //some scenario
    }
}
