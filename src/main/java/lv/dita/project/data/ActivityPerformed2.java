package lv.dita.project.data;

import lombok.Getter;
import lombok.Setter;

@Getter
public class ActivityPerformed2 {

    private String name;
    private int minutes;
    private double calories;

    public ActivityPerformed2(String name, int minutes, double calories) {
        this.name = name;
        this.minutes = minutes;
        this.calories = calories;
    }

}
