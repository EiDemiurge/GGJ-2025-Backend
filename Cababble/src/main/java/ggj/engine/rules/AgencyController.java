package ggj.engine.rules;

import ggj.engine.logic.entity.stat.param.GameParam;
import ggj.engine.logic.entity.stat.prop.GameProp;
import ggj.engine.logic.state.GameState;

import java.util.function.Predicate;

public class AgencyController {

    public static Predicate<String> canSend(){
        return AgencyController::canSendMessage;
    }
    public static boolean canSendMessage(String message){
        int agency = GameState.getState().game.reader().param(GameParam.AGENCY);
        String base = GameState.getState().game.reader().prop(GameProp.BASE_MESSAGE);
        return DiffCalc.calc(base, message) <= agency;
    }

}
