package ggj.engine.logic.entity;

import ggj.engine.logic.entity.stat.Parameter;
import ggj.engine.logic.entity.stat.Property;
import ggj.engine.logic.entity.stat.param.MsgParam;
import ggj.engine.logic.entity.stat.prop.MsgProp;
import ggj.engine.logic.state.EntityMapper;

import static ggj.engine.logic.model.ModelChat.*;

public class Message extends ChatEntity<ChatUserMessage> {

    private static int MSG_ID=0;

    public Message(ChatUserMessage model) {
        super(model);
    }

    @Override
    protected void added() {
        EntityMapper.addedMsg(current.get(MsgParam.ROOM_ID), current.get(MsgParam.USER_ID), this);
    }

    @Override
    protected int newId() {
        return MSG_ID++;
    }

    @Override
    protected Property<ChatUserMessage>[] getPropConstants() {
        return MsgProp.values();
    }

    @Override
    protected Parameter<ChatUserMessage>[] getParamConstants() {
        return MsgParam.values();
    }

    @Override
    public EntityType getType() {
        return EntityType.Message;
    }
}
