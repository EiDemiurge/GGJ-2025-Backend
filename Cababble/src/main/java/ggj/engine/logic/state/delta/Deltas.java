package ggj.engine.logic.state.delta;

import ggj.engine.logic.entity.ChatEntity;
import ggj.engine.logic.entity.stat.Parameter;

import java.util.List;

public class Deltas {

    List<Delta> deltas;

    public Deltas(List<Delta> deltas) {
        this.deltas = deltas;
    }

    public interface Delta { }
    public record IntDelta(ChatEntity.TargetRef ref,
                           Parameter param,
                           int value ) implements Delta {
    }

}
