package ggj.engine.logic.entity;

import ggj.engine.logic.entity.stat.Parameter;
import ggj.engine.logic.entity.stat.Property;
import ggj.engine.logic.entity.stat.param.MsgParam;
import ggj.engine.logic.entity.stat.param.UserParam;
import ggj.engine.logic.entity.stat.prop.UserProp;
import ggj.engine.logic.model.ModelChat;
import ggj.engine.logic.state.EntityMapper;

/*
created from a template, but they DO have their associated state!
 */
public class User extends ChatEntity<ModelChat.ChatUser> {

    // Npc npc; only when we actually impl some LOGIC
    private static int USER_ID = 0;
    private boolean banned;

    public User(ModelChat.ChatUser model) {
        super(model);
    }

    @Override
    protected void added() {
        EntityMapper.addedUser(current.get(UserParam.ROOM_ID), this);
    }

    @Override
    protected int newId() {
        return USER_ID++;
    }

    @Override
    public ModelChat.ChatUser createModel() {
        //TODO
        return new ModelChat.ChatUser(current.get(UserParam.ROOM_ID),
                                      props.get(UserProp.NAME), null, null,
                                    current.get(UserParam.UPVOTES),
                                    current.get(UserParam.DOWNVOTES));
    }

    @Override
    protected Property<ModelChat.ChatUser>[] getPropConstants() {
        return UserProp.values();
    }

    @Override
    protected Parameter<ModelChat.ChatUser>[] getParamConstants() {
        return UserParam.values();
    }


    @Override
    public EntityType getType() {
        return EntityType.User;
    }

    public int getRoomId() {
        return current.get(UserParam.ROOM_ID);
    }

    public void setBanned(boolean banned) {
        this.banned = banned;
    }

    public boolean isBanned() {
        return banned;
    }
}
