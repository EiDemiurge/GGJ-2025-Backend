package ggj.engine.logic.state;

import ggj.engine.logic.entity.Message;
import ggj.engine.logic.entity.Room;
import ggj.engine.logic.entity.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

public class EntityMapper {

    // how to keep these up to date?
    // LIKE A SQL?
    private static final Map<Integer, List<Message>> roomIdToMsg = new HashMap<>();
    private static final Map<Integer, List<User>> roomIdToUser = new HashMap<>();
    private static final Map<Integer, List<Message>> userIdToMsg = new HashMap<>();

    private static Consumer<Message> msgAdded;
    private static Consumer<Room> roomAdded;
    private static Consumer<User> userAdded;

    public static void setup(Consumer<Message> msgAdded, Consumer<Room> roomAdded, Consumer<User> userAdded) {
        EntityMapper.msgAdded = msgAdded;
        EntityMapper.roomAdded = roomAdded;
        EntityMapper.userAdded = userAdded;
    }

    public static void addedRoom(Room room) {
        roomIdToUser.computeIfAbsent(room.getId(), k -> new ArrayList<>());
        roomAdded.accept(room);
    }
    public static void addedUser(Integer roomId, User user) {
        //user can only be in one Room at a time? I think this will simplify things!
        roomIdToUser.computeIfAbsent(roomId, k -> new ArrayList<>()).add(user);
        userAdded.accept(user);
    }
    public static void addedMsg(Integer roomId, Integer userId, Message message) {
        roomIdToMsg.computeIfAbsent(roomId, k -> new ArrayList<>()).add(message);
        userIdToMsg.computeIfAbsent(userId, k -> new ArrayList<>()).add(message);
        msgAdded.accept(message);
    }

    public static boolean isUserInRoom(int userId, int roomId) {
        return roomIdToUser.get(roomId).stream().anyMatch(user -> user.getId() == userId);
    }

    public static void userExitRoom(User user, int roomId) {
        roomIdToUser.get(roomId).remove(user);
        // roomIdToUser.computeIfPresent(roomId, (id, list) -> )
    }

    public static Map<Integer, List<User>> getRoomIdToUser() {
        return roomIdToUser;
    }

    public static Map<Integer, List<Message>> getRoomIdToMsg() {
        return roomIdToMsg;
    }

    public static Map<Integer, List<Message>> getUserIdToMsg() {
        return userIdToMsg;
    }
}
