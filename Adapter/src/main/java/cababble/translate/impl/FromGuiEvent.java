package cababble.translate.impl;

import ggj.util.Format;

import java.util.function.Function;

import static ggj.event.model.Gui_Event.*;

public class FromGuiEvent implements Function<GuiEvent, String> {
    @Override
    public String apply(GuiEvent event) {
        /*
        EVENT_TYPE:{key1=val1| ...
         */
        return Format.eventToClient(event);
    }
}
