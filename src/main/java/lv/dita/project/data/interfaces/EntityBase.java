package lv.dita.project.data.interfaces;
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

