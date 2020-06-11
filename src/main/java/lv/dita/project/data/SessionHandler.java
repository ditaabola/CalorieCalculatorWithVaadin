package lv.dita.project.data;

import lombok.Getter;
import lombok.Setter;


public class SessionHandler {
    @Getter
    @Setter
    private static double userEEr;

    @Getter
    @Setter
    private static double userWeight;

    @Getter
    @Setter
    private static double foodCalories;

    @Getter
    @Setter
    private static double activitiesCalories;

}
