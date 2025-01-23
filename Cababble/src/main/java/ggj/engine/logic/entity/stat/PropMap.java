package ggj.engine.logic.entity.stat;

import ggj.engine.logic.model.ModelChat;

import java.util.HashMap;
import java.util.Map;

public class PropMap <E extends ModelChat.EntityModel> {
    Map<Property<E>, String> map = new HashMap<>();

    public void put(Property<E> param, String value){
        map.put(param, value);
    }

    public String get(Object key) {
        return map.get(key);
    }
}
