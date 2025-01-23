package ggj.engine.logic.entity;

import ggj.engine.logic.entity.stat.ParamMap;
import ggj.engine.logic.entity.stat.Parameter;
import ggj.engine.logic.entity.stat.PropMap;
import ggj.engine.logic.entity.stat.Property;
import ggj.engine.logic.entity.stat.param.RoomParam;
import ggj.engine.logic.entity.stat.prop.RoomProp;
import ggj.engine.logic.state.EntityMapper;

import static ggj.engine.logic.model.ModelChat.*;

public class Room extends ChatEntity<ChatRoom> {

    private static int ROOM_ID = 0;

    public Room(ChatRoom model) {
        super(model);
    }

    @Override
    protected void added() {
        EntityMapper.addedRoom(this);
    }

    @Override
    protected ChatRoom copyWith(ChatRoom model, ParamMap<ChatRoom> current, PropMap<ChatRoom> props) {
        return new ChatRoom(props.get(RoomProp.NAME), null);
    }

    @Override
    protected int newId() {
        return ROOM_ID++;
    }

    @Override
    protected Property<ChatRoom>[] getPropConstants() {
        return RoomProp.values();
    }

    @Override
    protected Parameter<ChatRoom>[] getParamConstants() {
        return RoomParam.values();
    }

    public int getId() {
        return id;
    }

    @Override
    public EntityType getType() {
        return EntityType.Room;
    }
}
