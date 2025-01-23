package ggj.engine.logic.entity.stat;

import ggj.engine.logic.model.ModelChat;

import java.util.HashMap;
import java.util.Map;

public class ParamMap<E extends ModelChat.EntityModel> {

    private final Map<Parameter<E>, Integer> map = new HashMap<>();

    public Integer put(Parameter<E> key, Integer value) {
        return map.put(key, value);
    }
    public Integer get(Object key) {
        return map.get(key);
    }

    public void increment(Parameter<E> param) {
        put(param, map.get(param) + 1);
    }

    public void putAll(Map<Parameter<E>, Integer> m) {
        map.putAll(m);
    }

    public ParamMap<E> copy(){
        ParamMap<E> copy = new ParamMap<>();
        copy.putAll(map);
        return copy;
    }
}
