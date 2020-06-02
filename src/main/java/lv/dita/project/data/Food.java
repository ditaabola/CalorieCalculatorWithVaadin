package lv.dita.project.data;

import lv.dita.project.data.interfaces.EntityBase;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import static lv.dita.project.data.enums.Constants.SCHEMA_NAME;

public class Food implements EntityBase {

    public static final String SELECT_QUERY = "select * from " + SCHEMA_NAME + ".food_calories";

    private int id;
    private String name;
    private String type;
    private int caloriesPer100G;
    private BigDecimal proteinGPer100G;
    private BigDecimal carbGPer100G;
    private BigDecimal fatGPer100G;

    public Food(int id, String name, String type, int caloriesPer100G, BigDecimal proteinGPer100G, BigDecimal carbGPer100G, BigDecimal fatGPer100G) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.caloriesPer100G = caloriesPer100G;
        this.proteinGPer100G = proteinGPer100G;
        this.carbGPer100G = carbGPer100G;
        this.fatGPer100G = fatGPer100G;
    }

    public static Food createFood(ResultSet rs) {
        Food food = null;
        try {
            food = new Food(
                    rs.getInt("food_id"),
                    rs.getString("food_name"),
                    rs.getString("food_type"),
                    rs.getInt("food_calories_per_100g"),
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

    public static String getSelectByIdSql() {
        return getSelectSql() + " where food_id = ?";
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

    public int getCaloriesPer100G() {
        return caloriesPer100G;
    }

    public void setCaloriesPer100G(int caloriesPer100G) {
        this.caloriesPer100G = caloriesPer100G;
    }

    public BigDecimal getProteinGPer100G() {
        return proteinGPer100G;
    }

    public void setProteinGPer100G(BigDecimal proteinGPer100G) {
        this.proteinGPer100G = proteinGPer100G;
    }

    public BigDecimal getCarbGPer100G() {
        return carbGPer100G;
    }

    public void setCarbGPer100G(BigDecimal carbGPer100G) {
        this.carbGPer100G = carbGPer100G;
    }

    public BigDecimal getFatGPer100G() {
        return fatGPer100G;
    }

    public void setFatGPer100G(BigDecimal fatGPer100G) {
        this.fatGPer100G = fatGPer100G;
    }
}