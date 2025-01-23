package ggj.util.datastruct;

import java.util.HashMap;

public class GenericStringMap<T> extends HashMap<String, T> {
    public GenericStringMap() {
    }

    public GenericStringMap(String[] keys, T[] values) {
        super();
        int i = 0;
        for (String key : keys) {
            put(key, values[i++]);
        }
    }

    @Override
    public T get(Object key) {
        return super.get(key.toString().toLowerCase());
    }

    @Override
    public T put(String key, T value) {
        return super.put(key.toLowerCase(), value);
    }
}
