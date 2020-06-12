package lv.dita.project.data.tabs.foodDatabase;

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
public class Food implements EntityBase {

    public static final String SELECT_QUERY = "select * from " + SCHEMA_NAME + ".food_calories";

    private int id;
    private String name;
    private String type;
    private double caloriesPer100G;
    private BigDecimal proteinGPer100G;
    private BigDecimal carbGPer100G;
    private BigDecimal fatGPer100G;

    public static Food createFood(@NotNull ResultSet rs) {
        Food food = null;
        try {
            food = new Food(
                    rs.getInt("food_id"),
                    rs.getString("food_name"),
                    rs.getString("food_type"),
                    rs.getDouble("food_calories_per_100g"),
                    rs.getBigDecimal("food_protein_per_100_g"),
                    rs.getBigDecimal("food_carbohydrates_per_100_g"),
                    rs.getBigDecimal("food_fats_per_100_g"));

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return food;
    }

    public static String getSelectSql() {
        return SELECT_QUERY;
    }

    public static EntityBase getEntity(ResultSet rs) {
        return Food.createFood(rs);
    }

    @Contract(pure = true)
    public static @NotNull String getSelectByIdSql() {
        return getSelectSql() + " where food_id = ?";
    }
}