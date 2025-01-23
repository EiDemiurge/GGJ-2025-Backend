package ggj.engine.logic.state.delta;

import ggj.engine.logic.entity.ChatEntity;
import ggj.engine.logic.entity.stat.Parameter;

import java.util.function.Function;

public class EntityMutator {

    Function<ChatEntity.TargetRef, ChatEntity> find;

    public EntityMutator(Function<ChatEntity.TargetRef, ChatEntity> find) {
        this.find = find;
    }

    public void apply(Deltas deltas) {
        for (Deltas.Delta delta : deltas.deltas) {
            //TODO can we do better?
            if (delta instanceof Deltas.IntDelta i) {
                applyInt(i);
            }
        }
    }

    public void applyInt(Deltas.IntDelta delta) {
        ChatEntity e = find.apply(delta.ref());
        //TODO what if it points to a WRONG TYPE ?
        mutate(e, delta.param(), delta.value());
    }

    private void mutate(ChatEntity e, Parameter param, int value) {
        //TODO
        // EngineEvents.
        //trigger engine event =>  state CHECK for game-events
    }
}
