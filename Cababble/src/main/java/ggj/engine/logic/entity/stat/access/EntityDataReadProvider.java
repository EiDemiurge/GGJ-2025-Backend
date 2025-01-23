package ggj.engine.logic.entity.stat.access;

import ggj.engine.logic.entity.ChatEntity;
import ggj.engine.logic.model.ModelChat;

public class EntityDataReadProvider {
    //entity.createModel()

    public static <E extends ModelChat.EntityModel,
                   T extends ChatEntity<E>> EntityDataRead<E> read(T entity) {
        E model = entity.createModel();
        return new EntityDataRead<>(model);
    }
}
