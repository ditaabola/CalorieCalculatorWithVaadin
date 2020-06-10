package lv.dita.project.data;

import lombok.Getter;
import lombok.Setter;
import lv.dita.project.data.interfaces.EntityBase;

import java.sql.ResultSet;
import java.sql.SQLException;

@Getter
@Setter
public class ActivityListByCalories implements EntityBase {

    String name;
    String level;
    double met;
    String type;
    double calories;

    public ActivityListByCalories(String name, String level, double met, String type, double calories) {
        this.name = name;
        this.level = level;
        this.met = met;
        this.type = type;
        this.calories = calories;
    }

    public static ActivityListByCalories create(ResultSet rs) {
        ActivityListByCalories activity = null;
        try {
            activity = new ActivityListByCalories(rs.getString("activity_name"),
                    rs.getString("activity_level"),
                    rs.getDouble("activity_met_value"),
                    rs.getString("activity_type"),
                    rs.getDouble("calories"));
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return activity;
    }
}
