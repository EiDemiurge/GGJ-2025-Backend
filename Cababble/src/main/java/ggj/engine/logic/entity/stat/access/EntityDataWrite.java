package ggj.engine.logic.entity.stat.access;

import ggj.engine.logic.entity.stat.Parameter;
import ggj.engine.logic.entity.stat.Property;
import ggj.engine.logic.entity.stat.param.MsgParam;
import ggj.engine.logic.model.ModelChat;

import java.util.function.BiConsumer;
import java.util.function.Consumer;

import static ggj.engine.logic.model.ModelChat.*;
import static ggj.engine.logic.entity.stat.access.EntityDataWriteProvider.*;

public class EntityDataWrite<E extends EntityModel> implements ParamSetter<E>, PropSetter<E> {

    private final BiConsumer<Parameter<E>, Integer> paramFunc;
    private final BiConsumer<Property<E>, String> propFunc;
    private final Consumer<Parameter<E>> increment;

    protected EntityDataWrite(BiConsumer<Parameter<E>, Integer> paramFunc, BiConsumer<Property<E>, String> propFunc,
                              Consumer<Parameter<E>> increment) {
        this.paramFunc = paramFunc;
        this.propFunc = propFunc;
        this.increment = increment;
    }

    public void set(Parameter<E> parameter, int value) {
        paramFunc.accept(parameter, value);
    }

    public void set(Property<E> prop, String value) {
        propFunc.accept(prop, value);
    }

    public void increment(Parameter<E> parameter) {
        increment.accept(parameter);
    }
}
