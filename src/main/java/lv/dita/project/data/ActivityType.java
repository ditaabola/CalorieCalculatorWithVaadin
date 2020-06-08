package lv.dita.project.data;

import lombok.AllArgsConstructor;
import lombok.Data;
import lv.dita.project.data.interfaces.EntityBase;
import org.jetbrains.annotations.NotNull;

import java.sql.ResultSet;
import java.sql.SQLException;

import static lv.dita.project.data.enums.Constants.SCHEMA_NAME;

@AllArgsConstructor
@Data
public class ActivityType implements EntityBase {

    private String type;

    public static final String SELECT_QUERY = "select activity_type from " + SCHEMA_NAME + ".activity_type";

    public static ActivityType createActivityType(@NotNull ResultSet rs) {
        ActivityType activityType = null;
        try {
            activityType = new ActivityType(rs.getString("activity_type"));

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return activityType;
    }

    public static String getSelectSql() {
        return SELECT_QUERY;
    }
    public static EntityBase getEntity(ResultSet rs) {
        return ActivityType.createActivityType(rs);
    }
}


