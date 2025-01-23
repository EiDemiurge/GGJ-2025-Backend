package ggj.engine.logic.transform;

import ggj.engine.logic.entity.Message;
import ggj.engine.logic.state.delta.Deltas;

import java.util.function.Function;

public class MsgToDeltaTransform implements Function<Message, Deltas> {
    /*
    analyze how a new message affects internal state of each player
     */
    @Override
    public Deltas apply(Message message) {
        
        return null;
    }


}
