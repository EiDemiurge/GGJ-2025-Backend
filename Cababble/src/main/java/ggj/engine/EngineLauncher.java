package ggj.engine;

import ggj.event.ProcessorLauncher;
import ggj.event.model.Gui_Event;
import ggj.event.model.User_Event;
import ggj.event.process.EventQueue;
import ggj.util.Log;

import java.util.concurrent.BlockingQueue;

public class EngineLauncher {
    public static void main(String[] args) {
        launch();
    }

    public static EventQueue<Gui_Event.GuiEvent> launch() {
        Log.info("Engine starting...");
        Setup.run();
        //check setup
        return ProcessorLauncher.launch();
    }

    public static void clientQueue(EventQueue<User_Event.UserEvent> userEvents) {
        ProcessorLauncher.clientQueue(userEvents);
    }
}
