package ggj.engine.logic.entity.stat.access;

import ggj.engine.logic.entity.stat.Parameter;
import ggj.engine.logic.entity.stat.Property;
import ggj.engine.logic.model.ModelChat;

import java.util.function.BiConsumer;
import java.util.function.Consumer;

public class EntityDataWriteProvider {
    //entity.createModel()

    public interface PropSetter<E extends ModelChat.EntityModel>{
        void set(Property<E> prop, String value);
    }
    public interface ParamSetter<E extends ModelChat.EntityModel>{
        void set(Parameter<E> prop, int value);
    }

    public static <E extends ModelChat.EntityModel> EntityDataWrite<E> write(BiConsumer<Parameter<E>, Integer> param,
                                                                    BiConsumer<Property<E>, String> prop,
                                                                             Consumer<Parameter<E>> increment) {
        return new EntityDataWrite<>(param, prop, increment);
    }
}
