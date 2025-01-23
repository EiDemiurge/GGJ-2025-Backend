package ggj.engine.logic.entity.stat;

import java.util.function.Function;

import static ggj.engine.logic.model.ModelChat.*;

public interface Parameter<E extends EntityModel> {

    int value(E model);


}
