package e2e;

import ggj.engine.Setup;
import ggj.engine.logic.state.GameState;
import ggj.event.ProcessorLauncher;
import ggj.event.model.User_Event;
import ggj.util.Log;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import static ggj.event.build.Events.*;

@Disabled
public class TestSequence  {

    @Test
    public void run() throws InterruptedException {

        Setup.run();
        //check setup
        Log.info("Setup done!");
        // stdKeysForType()
        user(User_Event.MSG_SENT).keys("roomId", "Message").values(0, "My Message 1").fire();
        Thread.sleep(1100);
        user(User_Event.MSG_SENT).keys("roomId", "Message").values(0, "My Message 2").fire();
        user(User_Event.MSG_SENT).keys("roomId", "Message").values(0, "My Message 3").fire();
        Thread.sleep(1100);
        user(User_Event.MSG_SENT).keys("roomId", "Message").values(1, "My NEW Message").fire();

        ProcessorLauncher.launch();
        Thread.sleep(11100);

        //print all messages in a room?
        Log.info(ggj.util.Format.state(GameState.getState()));
    }
}
