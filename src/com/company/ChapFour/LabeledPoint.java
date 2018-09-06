package com.company.ChapFour;

public class LabeledPoint extends Point {
    protected String label;
    public LabeledPoint(String label, double x, double y) {
        super(x, y);
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}
