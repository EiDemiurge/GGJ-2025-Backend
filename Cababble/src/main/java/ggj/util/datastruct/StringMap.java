package ggj.util.datastruct;

import java.util.HashMap;

public class StringMap extends HashMap<String, Object> {
    public StringMap() {
    }

    public StringMap(String[] keys, Object[] values) {
        super();
        int i = 0;
        for (String key : keys) {
            put(key, values[i++]);
        }
    }

    @Override
    public Object get(Object key) {
        return super.get(key.toString().toLowerCase());
    }

    @Override
    public Object put(String key, Object value) {
        return super.put(key.toLowerCase(), value);
    }
}
