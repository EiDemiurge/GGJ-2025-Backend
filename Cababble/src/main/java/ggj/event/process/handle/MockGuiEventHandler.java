package ggj.event.process.handle;

import ggj.engine.logic.model.ModelChat;
import ggj.util.Log;

import java.util.function.Function;

import static ggj.event.model.Gui_Event.*;

public class MockGuiEventHandler implements EventHandler<GuiEvent> {

    private final Function<Integer, ModelChat.ChatUser> userDataProvider;
    private final Function<Integer, ModelChat.ChatRoom> roomDataProvider;

    public MockGuiEventHandler(Function<Integer, ModelChat.ChatUser> userDataProvider,
                               Function<Integer, ModelChat.ChatRoom> roomDataProvider) {
        this.userDataProvider = userDataProvider;
        this.roomDataProvider = roomDataProvider;
    }

    @Override
    public void accept(GuiEvent event) {
        switch (event.type()) {
            case CHAT_MSG_APPEND -> {

                Integer userId = event.args().num("userId");
                Integer roomId = event.args().num("roomId");
                ModelChat.ChatUser data = userDataProvider.apply(userId);
                ModelChat.ChatRoom room = roomDataProvider.apply(roomId);

                Log.info(
                        "[%s] %s writes: \n - '%s'".formatted(room.name(), data.name(), event.args().string("message")));
            }
        }
    }
}
