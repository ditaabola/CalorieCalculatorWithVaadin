package lv.dita.project.data;

import lv.dita.project.data.interfaces.EntityBase;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;

import static lv.dita.project.data.enums.Constants.SCHEMA_NAME;

public class Activity implements EntityBase {

    public static final String SELECT_QUERY = "select * from "+SCHEMA_NAME+".activities_met_values";

    private int id;
    private String name;
    private String type;
    private BigDecimal metValue;
    private String level;

    public Activity(int id, String name, String type, BigDecimal metValue, String level) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.metValue = metValue;
        this.level = level;
    }

    public static Activity createActivity(ResultSet rs) {
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

    public static String getSelectByIdSql() {
        return getSelectSql() + " where activity_id = ?";
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public BigDecimal getMetValue() {
        return metValue;
    }

    public void setMetValue(BigDecimal metValue) {
        this.metValue = metValue;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }
}