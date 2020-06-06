package lv.dita.project.data;

import lombok.AllArgsConstructor;
import lombok.Data;
import lv.dita.project.data.interfaces.EntityBase;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;

import static lv.dita.project.data.enums.Constants.SCHEMA_NAME;

@AllArgsConstructor
@Data
public class Activity implements EntityBase {

    public static final String SELECT_QUERY = "select * from " + SCHEMA_NAME + ".activities_met_values";

    private int id;
    private String name;
    private String type;
    private BigDecimal metValue;
    private String level;

    public static Activity createActivity(@NotNull ResultSet rs) {
        Activity activity = null;
        try {
            activity = new Activity(rs.getInt("activity_id"),
                    rs.getString("activity_name"),
                    rs.getString("activity_type"),
                    rs.getBigDecimal("activity_met_value"),
                    rs.getString("activity_level"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return activity;
    }

    public static String getSelectSql() {
        return SELECT_QUERY;
    }

    public static EntityBase getEntity(ResultSet rs) {
        return Activity.createActivity(rs);
    }

    @Contract(pure = true)
    public static @NotNull String getSelectByIdSql() {
        return getSelectSql() + " where activity_id = ?";
    }
}