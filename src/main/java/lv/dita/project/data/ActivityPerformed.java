package lv.dita.project.data;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ActivityPerformed {


    private int id;
    private String name;
    private int minutes;
    private double calories;

    public ActivityPerformed(int id, String name, int minutes, double calories) {
        this.id = id;
        this.name = name;
        this.minutes = minutes;
        this.calories = calories;
    }

}
