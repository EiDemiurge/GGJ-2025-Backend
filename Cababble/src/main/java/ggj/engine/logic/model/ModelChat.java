package ggj.engine.logic.model;

public class ModelChat {

    public interface EntityModel {
    }

    public record ChatGame(int agency, String base) implements EntityModel {

    }
    public record ChatUser(int roomId, String name, String role, String avatar, int upvotes, int downvotes) implements EntityModel {
        public ChatUser(String name){
            this(0, name, "Newbie", "", 0, 0);
        }
    }

    public record ChatUserMessage(int userId, int roomId, String message, int upvotes, int downvotes) implements EntityModel {

    }
    //maybe not? Can it be just a string without anything else?
    public record ChatSystemMessage() implements EntityModel {

    }

    public record ChatRoom(String name, String icon) implements EntityModel {

    }

    //also entity?
    public record ChatKeyword() {

    }
}
