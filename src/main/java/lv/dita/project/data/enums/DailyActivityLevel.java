package lv.dita.project.data.enums;

public enum DailyActivityLevel {

    SEDENTARY(1.0),
    LIGHT(1.12),
    MODERATE(1.27),
    ACTIVE(1.45);

    private final double pa;

    DailyActivityLevel(double pa) {
        this.pa = pa;
    }

    public double getPa() {
        return pa;
    }
}
