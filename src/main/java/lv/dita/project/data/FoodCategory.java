package lv.dita.project.data;

import lv.dita.project.data.interfaces.EntityBase;
import org.jetbrains.annotations.NotNull;
import java.sql.ResultSet;
import java.sql.SQLException;
import static lv.dita.project.data.enums.Constants.SCHEMA_NAME;

public class FoodCategory implements EntityBase {

    public static final String SELECT_QUERY = "select food_type from " + SCHEMA_NAME + ".food_with_categories";

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    private String type;

    public FoodCategory (String type) {
        this.type = type;
    }

    public static FoodCategory createFoodCategory(@NotNull ResultSet rs) {
        FoodCategory foodCategory = null;
        try {
            foodCategory = new FoodCategory(rs.getString("food_type"));

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return foodCategory;
    }

    public static String getSelectSql() {
        return SELECT_QUERY;
    }
    public static EntityBase getEntity(ResultSet rs) {
        return FoodCategory.createFoodCategory(rs);
    }

    @Override
    public String toString() {
        return type;
    }
}
