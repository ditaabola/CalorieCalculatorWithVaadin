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
public class FoodEaten implements EntityBase {
    private int id;
    private String name;
    private double quantity;
    private int calories;

    public static final String SELECT_QUERY = "select * from " + SCHEMA_NAME + ".food_eaten";

    public static FoodEaten createFoodEaten(@NotNull ResultSet rs) {
        FoodEaten foodEaten = null;
        try {
            foodEaten = new FoodEaten(rs.getInt("food_eaten_id"),
                    rs.getString("food_eaten_name"),
                    rs.getDouble("food_eaten_quantity"),
                    rs.getInt("food_eaten_calories"));

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return foodEaten;
    }

    public static String getSelectSql() {
        return SELECT_QUERY;
    }

    public static EntityBase getEntity(ResultSet rs) {
        return FoodEaten.createFoodEaten(rs);
    }
}


