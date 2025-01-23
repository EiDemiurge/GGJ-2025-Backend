package ggj.event;

import ggj.event.build.Events;
import ggj.event.process.EventQueue;
import ggj.event.process.QueueProcessor;

import java.util.concurrent.BlockingQueue;

import static ggj.event.model.Engine_Event.*;
import static ggj.event.model.User_Event.*;
import static ggj.event.model.Gui_Event.*;
import static ggj.event.model.Game_Event.*;

public class ProcessorLauncher {

    private static QueueProcessor<EngineEvent> engineProcessor;
    private static QueueProcessor<UserEvent> userProcessor;
    private static QueueProcessor<GuiEvent> guiProcessor;
    private static QueueProcessor<GameEvent> gameProcessor;

    // @Injection
    public static void setup(QueueProcessor<EngineEvent> engineProcessor,
                             QueueProcessor<UserEvent> userProcessor,
                             QueueProcessor<GuiEvent> guiProcessor,
                             QueueProcessor<GameEvent> gameProcessor) {
        ProcessorLauncher.engineProcessor = engineProcessor;
        ProcessorLauncher.userProcessor = userProcessor;
        ProcessorLauncher.guiProcessor = guiProcessor;
        ProcessorLauncher.gameProcessor = gameProcessor;
    }

    public static EventQueue<GuiEvent> launch() {
        launch(engineProcessor, "engine");
        launch(gameProcessor, "game");
        return guiProcessor.getQueue();
    }

    private static void launch(QueueProcessor<?> processor, String hint) {
        new Thread(processor, hint + " event processor").start();
    }

    public static void mockClientQueue() {
        clientQueue(Events.newQueue(UserEvent.class));
        launch(guiProcessor, "gui"); //instead of a real consumer of gui events
    }

    public static void clientQueue(EventQueue<UserEvent> userEvents) {
        Events.newQueue(UserEvent.class, userEvents);
        launch(userProcessor.copyWith(userEvents), "user");
    }
}
