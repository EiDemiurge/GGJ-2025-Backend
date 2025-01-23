package ggj.engine.rules;

import ggj.engine.logic.entity.Message;
import ggj.engine.logic.entity.User;
import ggj.engine.logic.entity.stat.param.MsgParam;
import ggj.engine.logic.state.EntityMapper;
import ggj.engine.logic.state.EntityProvider;
import ggj.util.meta.Inject;

public class Rules {

    private static EntityProvider<Message> messageProvider;
    private static EntityProvider<User> userProvider;

    @Inject
    public static void setup(EntityProvider<Message> messageProvider, EntityProvider<User> userProvider) {
        Rules.messageProvider = messageProvider;
        Rules.userProvider = userProvider;
    }

    public static void upvote(int msgId, int sourceId) {
        ////TODO add to the list of upvoters
        messageProvider.find(msgId).writer().increment(MsgParam.UPVOTES);
    }

    public static void entered(int userId, int roomId) {
        var user = userProvider.find(userId);
        user.setBanned(false);
        EntityMapper.addedUser(roomId, user);
    }

    public static void quitRoom(int userId) {
        quit(userId, false);
    }
    public static void banned(int userId) {
        quit(userId, true);
    }

    private static void quit(int userId, boolean ban) {
        var user = userProvider.find(userId);
        int roomId = user.getRoomId();
        if (ban){
            user.setBanned(true);
        }
        EntityMapper.userExitRoom(user, roomId);
    }

    public static void processMsgImpact(Message msg) {
        //TODO
    }


/*
At this point we just want to test.

A message must be in response to something? Tied to a keyword?

=> EngineEvents? Is it something that we reflect, or request?
 */
}
