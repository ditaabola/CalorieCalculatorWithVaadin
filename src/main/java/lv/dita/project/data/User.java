package lv.dita.project.data;

import lombok.Getter;
import lv.dita.project.data.enums.DailyActivityLevel;
import lv.dita.project.data.enums.PersonsGender;
import lv.dita.project.data.enums.WeightGoal;


@Getter
public class User {

    private int height;
    private double weight;
    private int age;
    private PersonsGender gender;
    private DailyActivityLevel activityLevel;
    private WeightGoal goal;
}
