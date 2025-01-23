package ggj.engine.logic.entity.stat.access;

import ggj.engine.logic.entity.stat.Parameter;
import ggj.engine.logic.entity.stat.Property;
import ggj.engine.logic.model.ModelChat;

public class EntityDataRead<E extends ModelChat.EntityModel> {
    private final E model;

    public EntityDataRead(E model) {
        this.model = model;
    }

    public int param(Parameter<E> parameter) {
        return parameter.value(model);
    }

    public String prop(Property<E> prop) {
        return prop.value(model);
    }
}
