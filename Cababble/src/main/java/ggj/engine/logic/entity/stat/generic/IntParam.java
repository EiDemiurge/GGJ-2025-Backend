package ggj.engine.logic.entity.stat.generic;

public class IntParam {
    public final int max;
    public final int min;
    private int value;

    public IntParam(int max, int min) {
        this(max, min, 0);
    }
    public IntParam(int max, int min, int value) {
        this.max = max;
        this.min = min;
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
