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
public class FoodCategory implements EntityBase {

    private String type;

    public static final String SELECT_QUERY = "select food_type from " + SCHEMA_NAME + ".food_with_categories";

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
}
