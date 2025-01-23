package ggj.engine.logic.entity;

import ggj.engine.logic.entity.stat.*;
import ggj.engine.logic.entity.stat.access.EntityDataRead;
import ggj.engine.logic.entity.stat.access.EntityDataReadProvider;
import ggj.engine.logic.entity.stat.access.EntityDataWrite;
import ggj.engine.logic.entity.stat.access.EntityDataWriteProvider;

import static ggj.engine.logic.model.ModelChat.*;

public abstract class ChatEntity<M extends EntityModel> {
    public static int GLOBAL_ID = 0;

    private final M model;
    protected int uuid; //global
    protected int id;   //within entity-group
    protected ParamMap<M> base;
    protected ParamMap<M> current;
    protected PropMap<M> props;
    //custom params

    public ChatEntity(M model) {
        this.model = model;
        uuid = GLOBAL_ID++;
        base = createParams(model);
        current = base.copy();
        props = createProps(model);
        id = newId();
        added();
    }

    protected abstract void added();

    protected abstract int newId();

    //MOVE
    private ParamMap<M> createParams(M model) {
        ParamMap<M> map = new ParamMap<>();
        for (Parameter<M> enumConstant : getParamConstants()) {
            int value = enumConstant.value(model);
            map.put(enumConstant, value);
        }
        return map;
    }

    private PropMap<M> createProps(M model) {
        PropMap<M> map = new PropMap<>();
        for (Property<M> enumConstant : getPropConstants()) {
            String value = enumConstant.value(model);
            map.put(enumConstant, value);
        }
        return map;
    }

    protected abstract Property<M>[] getPropConstants();

    protected abstract Parameter<M>[] getParamConstants();

    /* Implement this per class */
    protected M copyWith(M model, ParamMap<M> current, PropMap<M> props) {
        throw new RuntimeException("Can't copy " + this);
    }

    /* share only read-only data using such methods */
    public M createModel() {
        return copyWith(model, current, props);
    }

    public EntityDataWrite<M> writer() {
        return EntityDataWriteProvider.write(
                (param, i) -> current.put(param, i),
                (prop, val) -> props.put(prop, val),
                (param) -> current.increment(param)
                );
    }

    public EntityDataRead<M> reader() {
        return EntityDataReadProvider.read(this);
    }

    public enum EntityType {
        User,
        Message,
        Room,
        Game,
    }

    public abstract EntityType getType();

    public int getId() {
        return id;
    }

    public int getUuid() {
        return uuid;
    }

    public TargetRef getRef() {
        return new TargetRef(uuid);
    }

    public record TargetRef(int id) {
    }
}
