package lv.dita.project.data;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
public class ActivityPerformed2 {

    private int id;
    private String name;
    private int minutes;
    private double calories;

    public ActivityPerformed2(int id, String name, int minutes, double calories) {
        this.id = id;
        this.name = name;
        this.minutes = minutes;
        this.calories = calories;
    }

}
