package ggj.engine;

import ggj.engine.logic.entity.Game;
import ggj.engine.logic.entity.Message;
import ggj.engine.logic.entity.Room;
import ggj.engine.logic.entity.User;
import ggj.engine.logic.entity.action.impl.ScriptActionProvider;
import ggj.engine.logic.generator.UserActGen;
import ggj.engine.logic.generator.UserActProcessor;
import ggj.engine.logic.state.Entities;
import ggj.engine.logic.state.EntityMapper;
import ggj.engine.logic.state.EntityProvider;
import ggj.engine.logic.state.GameState;
import ggj.engine.rules.Rules;
import ggj.event.ProcessorLauncher;
import ggj.event.process.QueueProcessor;
import ggj.event.process.handle.EngineEventHandler;
import ggj.event.process.handle.GameEventHandler;
import ggj.event.process.handle.MockGuiEventHandler;
import ggj.event.process.handle.UserEventHandler;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import static ggj.demo.Demo.StdUsers.*;
import static ggj.engine.logic.model.ModelChat.*;
import static ggj.event.build.Events.*;
import static ggj.event.model.Engine_Event.*;
import static ggj.event.model.Game_Event.*;
import static ggj.event.model.User_Event.*;
import static ggj.event.model.Gui_Event.*;

public class Setup {

    /**
     * All in one place at least
     */
    public static void run() {

        var engineProcessor = new QueueProcessor<>(newQueue(EngineEvent.class), new EngineEventHandler());
        var userProcessor = new QueueProcessor<>(newQueue(UserEvent.class), new UserEventHandler(
                () -> Entities.PLAYER.createModel(),
                () -> Entities.PLAYER.getUuid()));
        var guiProcessor = new QueueProcessor<>(newQueue(GuiEvent.class), new MockGuiEventHandler(
                id -> Entities.findUser(id).createModel(),
                id -> Entities.findRoom(id).createModel()
        ));
        var gameProcessor = new QueueProcessor<>(newQueue(GameEvent.class), new GameEventHandler());

        ProcessorLauncher.setup(engineProcessor, userProcessor, guiProcessor, gameProcessor);

        ////---------------------------------------------------------------------------\\\\

        UserActProcessor.setup(new ScriptActionProvider());


        ////---------------------------------------------------------------------------\\\\

        Set<User> users = new LinkedHashSet<>();
        Set<Message> messages = new LinkedHashSet<>();
        Set<Room> rooms = new LinkedHashSet<>();
        EntityMapper.setup(messages::add, rooms::add, users::add);

        ////---------------------------------------------------------------------------\\\\

        User player = new User(new ChatUser("The Hero"));
        EntityProvider<User> userProvider = id -> GameState.getState().users.stream().filter(user -> user.getId() == id).findAny().get();
        EntityProvider<Room> roomProvider = id -> GameState.getState().rooms.stream().filter(room -> room.getId() == id).findAny().get();
        EntityProvider<Message> messageProvider = id -> GameState.getState().messages.stream().filter(msg -> msg.getId() == id).findAny().get();

        Entities.setup(player, userProvider, roomProvider);
        Rules.setup(messageProvider, userProvider);

        ////---------------------------------------------------------------------------\\\\

        users.add(Entities.PLAYER);
        users.add(new User(Bob.get()));
        users.add(new User(Bob.get()));
        users.add(new User(Admin.get()));
        // ggj.demo.Demo.ADMIN_ID = ChatEntity.ID;
        users.add(Entities.PLAYER);

        //take from DEMO?
        rooms.add(new Room(new ChatRoom("Talkinists", "")));
        rooms.add(new Room(new ChatRoom("Dawkinists", "")));
        GameState.create(new Game(new ChatGame(50, "You bastards!")),
                users, rooms, messages);



        ////---------------------------------------------------------------------------\\\\

        List<UserActGen> gens = new ArrayList<>();
        gens.add(new UserActGen());
        // for (int i = 1; i < users.size(); i++) {
            // int finalI = i;
            // gens.add(new UserActGen(()-> users.get(finalI).createModel()));
        // }
        UserActProcessor.gameStarted(gens);
    }

}
