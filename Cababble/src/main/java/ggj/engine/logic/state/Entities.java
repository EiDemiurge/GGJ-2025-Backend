package ggj.engine.logic.state;

import ggj.engine.logic.entity.Room;
import ggj.engine.logic.entity.User;
import ggj.util.meta.Inject;

public class Entities {

    public static User PLAYER;
    private static EntityProvider<User> userProvider;
    private static EntityProvider<Room> roomProvider;

    @Inject
    public static void setup(User PLAYER, EntityProvider<User> userProvider, EntityProvider<Room> roomProvider){
        Entities.PLAYER = PLAYER;
        Entities.userProvider = userProvider;
        Entities.roomProvider = roomProvider;
    }
    //findByName?

    public static User findUser(int id){
        return userProvider.find(id);
    }

    public static Room findRoom(int id){
        return roomProvider.find(id);
    }
}
