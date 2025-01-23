package ggj.engine.logic.entity;

import ggj.engine.logic.entity.stat.Parameter;
import ggj.engine.logic.entity.stat.Property;
import ggj.engine.logic.entity.stat.param.GameParam;
import ggj.engine.logic.entity.stat.prop.GameProp;
import ggj.engine.logic.model.ModelChat;

public class Game extends ChatEntity<ModelChat.ChatGame> {

    public static final Property<ModelChat.ChatGame>[] props = GameProp.values();
    public static final Parameter<ModelChat.ChatGame>[] params = GameParam.values();

    public Game(ModelChat.ChatGame model) {
        super(model);
    }

    @Override
    protected void added() {
        //do nothing
    }

    @Override
    protected int newId() {
        return 0;
    }

    @Override
    protected Property<ModelChat.ChatGame>[] getPropConstants() {
        return props;
    }

    @Override
    protected Parameter<ModelChat.ChatGame>[] getParamConstants() {
        return params;
    }

    @Override
    public EntityType getType() {
        return EntityType.Game;
    }
}
