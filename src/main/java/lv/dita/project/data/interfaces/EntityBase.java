package lv.dita.project.data;
import java.sql.ResultSet;

public interface EntityBase {

    static String getSelectSql(){
        return null;
    }
    static EntityBase getEntity(ResultSet rs){
        return null;
    }
    static String getSelectByIdSql(){
        return null;
    }

}

