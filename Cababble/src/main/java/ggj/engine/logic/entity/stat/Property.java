package ggj.engine.logic.entity.stat;

import java.util.function.Function;

import static ggj.engine.logic.model.ModelChat.*;

public interface Property<E extends EntityModel> {

    String value(E model);

}
