package cababble.translate.impl;

import ggj.event.build.Events;
import ggj.event.model.User_Event;
import ggj.util.datastruct.StringMap;

import java.util.function.Function;

import static ggj.event.model.User_Event.*;

public class ToUserEvent implements Function<String, UserEvent> {
    private static final String EVENT_TYPE_SEPARATOR = ":";
    private static final String VAL_PAIR_SEPARATOR = "\\|";
    private static final String KEY_VAL_SEPARATOR = "=";

    public static final String illegal="{}";

    private boolean isValid(String s) {
        if (s.contains(EVENT_TYPE_SEPARATOR))
            return true;
        return false;
    }
    @Override
    public UserEvent apply(String s) {
        if (!isValid(s)){
            return null;
        }
        String[] head_body = s.split(EVENT_TYPE_SEPARATOR);
        User_Event type = valueOf(head_body[0].toUpperCase());
        if (type == EXIT){
            //TODO improve this
            return new UserEvent(EXIT, null);
        }
        String body = head_body[1];
        for (char c : illegal.toCharArray()) {
            body = body.replace(c + "", "");
        }
        StringMap map = new StringMap();
        for (String pair : body.split(VAL_PAIR_SEPARATOR)) {
            String key = pair.split(KEY_VAL_SEPARATOR)[0];
            String val = pair.split(KEY_VAL_SEPARATOR)[1];
            try {
                map.put(key, Integer.valueOf(val));
            } catch (NumberFormatException e) {
                map.put(key, val);
            }
        }
        Events.EventArgs args = new Events.EventArgs(map);
        return new UserEvent(type, args);
    }

}
