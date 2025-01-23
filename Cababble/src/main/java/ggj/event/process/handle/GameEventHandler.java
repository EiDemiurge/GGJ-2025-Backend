package ggj.event.process.handle;

import ggj.engine.logic.entity.Message;
import ggj.engine.logic.model.ModelChat;
import ggj.engine.logic.state.Entities;
import ggj.engine.logic.state.EntityMapper;
import ggj.event.build.Events;
import ggj.event.model.Game_Event;
import ggj.event.model.Gui_Event;
import ggj.util.Format;

import static ggj.engine.rules.Rules.*;

public class GameEventHandler implements EventHandler<Game_Event.GameEvent> {

    @Override
    public void accept(Game_Event.GameEvent gameEvent) {
        switch (gameEvent.type()) {
            case ENTER_NPC -> {
                entered(gameEvent.args().num("userId"), gameEvent.args().num("roomId"));

                Events.gui(Gui_Event.USER_ENTERED).args(gameEvent.args()).fire();
            }
            case BAN_PLAYER -> {
                banned(gameEvent.args().num("userId"));

                Events.gui(Gui_Event.PLAYER_BANNED).args(gameEvent.args()).fire();
            }
            case BAN_NPC -> {
                banned(Entities.PLAYER.getId());

                Events.gui(Gui_Event.USER_BANNED).args(gameEvent.args()).fire();
            }
            case UPVOTE -> {
                int msgId = gameEvent.args().num("msgId");
                int sourceId = gameEvent.args().num("sourceId");
                upvote(msgId, sourceId);
                //just update ui?
                Events.gui(Gui_Event.CHAT_MSG_UPVOTED).args(gameEvent.args()).fire();
            }
            case MSG_SENT_NPC -> {
                int userId = gameEvent.args().num("userId");
                int roomId = gameEvent.args().num("roomId");
                var message = gameEvent.args().string("message");
                if (!EntityMapper.isUserInRoom(userId, roomId)){
                    throw new RuntimeException("User cannot send message to this room: " + Format.event(gameEvent));
                }
                var msg = new Message(new ModelChat.ChatUserMessage(userId, roomId, message, 0, 0));
                //added to MAPS

                processMsgImpact(msg);
                //How to ensure that I don't get fucked by invisible args?


                // String username = gameEvent.args().string("username");
                //update some RPG state - how does this message impact others?
                //keyword may have been added

                // Events.engine(); //from there we actually might do some deltas?
                Events.gui(Gui_Event.CHAT_MSG_APPEND).args(gameEvent.args()).fire();
            }
        }
    }

}
