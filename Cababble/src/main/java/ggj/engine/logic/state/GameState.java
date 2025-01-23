package ggj.engine.logic.state;

import ggj.engine.logic.entity.Game;
import ggj.engine.logic.entity.Message;
import ggj.engine.logic.entity.Room;
import ggj.engine.logic.entity.User;

import java.util.Set;

//could be a record?
public class GameState {

    private static GameState state;

    public static GameState getState() {
        return state;
    }
    public static void create(Game game, Set<User> users, Set<Room> rooms, Set<Message> messages){
        state = new GameState(game, users, rooms, messages);
    }

    public final Game game;
    public final Set<User> users;
    public final Set<Room> rooms;
    public final Set<Message> messages;

    private GameState(Game game, Set<User> users, Set<Room> rooms, Set<Message> messages) {
        this.game = game;
        this.users = users;
        this.rooms = rooms;
        this.messages = messages;
    }
}
