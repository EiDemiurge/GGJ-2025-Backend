package ggj.engine.logic.state;

import ggj.engine.logic.entity.ChatEntity;

public interface EntityProvider<E extends ChatEntity> {
    E find(int id);

}
