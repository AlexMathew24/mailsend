package com.data.mailssend;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DataDAO {
    public  static List<Data> fetchData(Connection connection,String query) throws SQLException{
        List<Data> dataList= new ArrayList<>();
        try(PreparedStatement statement=connection.prepareStatement(query);
            ResultSet resultSet=statement.executeQuery()){
            while(resultSet.next()){

                //------------change ||sql dependency
                long userId=resultSet.getLong("user_id");
                String forename=resultSet.getString("forename");
                String roleFlag=resultSet.getString("role_flag");
//                MailssendApplication.Data data =new MailssendApplication.Data(id,name,descrption);
//                dataList.add(data);
                Data data =new Data(userId,forename,roleFlag);
                dataList.add(data);
            }
        }
        return dataList;
    }
}
