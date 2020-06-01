package lv.dita.project.data;

import lv.dita.project.enums.Constants;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

public class MySqlDataRepository  {

    private Connection connection;
    private static final String connectionString = "jdbc:mysql://localhost:3306/"+ Constants.SCHEMA_NAME;
    private static final String userName = Constants.DB_USER;
    private static final String passWord = Constants.DB_PASSWORD;

    public MySqlDataRepository() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

//    @Override
//    public <T extends EntityBase> List<T> getList(Class<T> cl) {
//        List<T> items = new ArrayList<>();
//        try {
//            connection = DriverManager.getConnection(connectionString, userName, passWord);
//            Statement statement = connection.createStatement();
//
//            var getSqlQueryMethod = cl.getMethod("getSelectSql");
//            var sql = getSqlQueryMethod.invoke(null);
//
//            ResultSet rs = statement.executeQuery(sql.toString());
//            var createObjectMethod = cl.getMethod("getEntity", ResultSet.class);
//            while (rs.next()) {
//                items.add((T) createObjectMethod.invoke(null, rs));
//            }
//            connection.close();
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return items;
//    }
//
//    @Override
//    public <T extends EntityBase> T getById(Class<T> cl, int id) {
//        try {
//            connection = DriverManager.getConnection(connectionString, userName, passWord);
//            var getSqlQueryMethod = cl.getMethod("getSelectByIdSql");
//            var sql = getSqlQueryMethod.invoke(null);
//
//            PreparedStatement stat = connection.prepareStatement(sql.toString());
//            stat.setInt(1, id);
//
//            var createObjectMethod = cl.getMethod("getEntity", ResultSet.class);
//
//            ResultSet rs = stat.executeQuery();
//
//            while (rs.next()) {
//                return (T) createObjectMethod.invoke(null, rs);
//            }
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return null;
//    }
//
//    @Override
//    public int addActivity(Activity activity) {
//        try {
//            connection = DriverManager.getConnection(connectionString, userName, passWord);
//            CallableStatement statement = connection.prepareCall("{call spAddActivity(?,?,?,?,?,?)}");
//
//            statement.setString("activity_name", activity.getName());
//            statement.setString("activity_type", activity.getType());
//            statement.setString("activity_level", activity.getLevel());
//            statement.setBigDecimal("activity_level", activity.getMetValue());
//            statement.registerOutParameter("activity_id", Types.INTEGER);
//            statement.execute();
//            var id = statement.getInt("region_id");
//            return id;
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return 0;
//    }
//
//    @Override
//    public List<Activity> getActivityByLevel(String level) {
//        try {
//            connection = DriverManager.getConnection(connectionString, userName, passWord);
//            CallableStatement statement = connection.prepareCall("{call spGetActivityByLevel(?)}");
//            statement.setString("level", level);
//
//            var rs = statement.executeQuery();
//            List<Activity> items = new ArrayList<>();
//            while (rs.next()) {
//                items.add(Activity.createActivity(rs));
//            }
//            return items;
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return null;
//    }
//
//    @Override
//    public List<Activity> getActivityByType(String type) {
//        try {
//            connection = DriverManager.getConnection(connectionString, userName, passWord);
//            CallableStatement statement = connection.prepareCall("{call spGetActivityByType(?)}");
//            statement.setString("type", type);
//
//            var rs = statement.executeQuery();
//            List<Activity> items = new ArrayList<>();
//            while (rs.next()) {
//                items.add(Activity.createActivity(rs));
//            }
//            return items;
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return null;
//    }
//
//    @Override
//    public List<Activity> getMetValueByActivityName(String name) {
//        try {
//            connection = DriverManager.getConnection(connectionString, userName, passWord);
//            CallableStatement statement = connection.prepareCall("{call spMetValueByActivityName(?,?)}");
//            statement.setString("name", name);
//
//            var rs = statement.executeQuery();
//            List<Activity> items = new ArrayList<>();
//            while (rs.next()) {
//                items.add(Activity.createActivity(rs));
//            }
//            return items;
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return null;
//    }
//
//
//
//    @Override
//    public int addFood(Food food) {
//        try {
//            connection = DriverManager.getConnection(connectionString, userName, passWord);
//            CallableStatement statement = connection.prepareCall("{call spAddFood(?,?,?,?,?,?,?)}");
//            statement.setString("food_name", food.getName());
//            statement.setString("food_type", food.getType());
//            statement.setInt("food_calories_per_100_g", food.getCaloriesPer100G());
//            statement.setBigDecimal("food_carbohydrates_per_100_g", food.getCarbGPer100G());
//            statement.setBigDecimal("food_fats_per_100_g", food.getFatGPer100G());
//            statement.setBigDecimal("food_protein_per_100_g", food.getProteinGPer100G());
//            statement.registerOutParameter("food_id", Types.INTEGER);
//            statement.execute();
//            var id = statement.getInt("food_id");
//            return id;
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return 0;
//    }
//    @Override
//    public List<Food> getFoodItemsByType(String type) {
//        try {
//            connection = DriverManager.getConnection(connectionString, userName, passWord);
//            CallableStatement statement = connection.prepareCall("{call spGetFoodItemsByType(?)}");
//            statement.setString("type", type);
//
//            var rs = statement.executeQuery();
//            List<Food> items = new ArrayList<>();
//            while (rs.next()) {
//                items.add(Food.createFood(rs));
//            }
//            return items;
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return null;
//    }
//
//    @Override
//    public List<Food> getFoodItemsByMaxCaloriesPer100G(int calories) {
//        try {
//            connection = DriverManager.getConnection(connectionString, userName, passWord);
//            CallableStatement statement = connection.prepareCall("{call spFoodItemsByMaxCaloriesPer100g(?)}");
//            statement.setInt("calories", calories);
//
//            var rs = statement.executeQuery();
//            List<Food> items = new ArrayList<>();
//            while (rs.next()) {
//                items.add(Food.createFood(rs));
//            }
//            return items;
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return null;
//    }
//
//    //šeit vajadzētu storetajā procedūrā "contains", bet neizpētīju vēl, jāmaina tā, manuprāt
//    @Override
//    public List<Food> getCaloriesByName(String name) {
//        try {
//            connection = DriverManager.getConnection(connectionString, userName, passWord);
//            CallableStatement statement = connection.prepareCall("{call spCaloriesByName(?,?)}");
//            statement.setString("name", name);
//
//            var rs = statement.executeQuery();
//            List<Food> items = new ArrayList<>();
//            while (rs.next()) {
//                items.add(Food.createFood(rs));
//            }
//            return items;
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return null;
//    }


}