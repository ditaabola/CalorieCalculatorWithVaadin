package lv.dita.project.data.activityWithMysql;

import lombok.AllArgsConstructor;
import lombok.Data;
import lv.dita.project.data.interfaces.EntityBase;
import org.jetbrains.annotations.NotNull;

import java.sql.ResultSet;
import java.sql.SQLException;

import static lv.dita.project.data.enums.Constants.SCHEMA_NAME;

@AllArgsConstructor
@Data
public class ActivityPerformed implements EntityBase {

    private int id;
    private String name;
    private double met_value;
    private double user_weight;
    private int minutes;

    public static final String SELECT_QUERY = "select * from " + SCHEMA_NAME + ".activity_performed";

    public static ActivityPerformed createActivityPerformed(@NotNull ResultSet rs) {
        ActivityPerformed activityPerformed = null;
        try {
            activityPerformed = new ActivityPerformed(
                    rs.getInt("activity_performed_id"),
                    rs.getString("activity_performed_name"),
                    rs.getDouble("activity_performed_met_value"),
                    rs.getDouble("activity_performed_user_weight"),
                    rs.getInt("activity_performed_minutes"));

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return activityPerformed;
    }

    public static String getSelectSql() {
        return SELECT_QUERY;
    }

    public static EntityBase getEntity(ResultSet rs) {
        return ActivityPerformed.createActivityPerformed(rs);
    }
}

