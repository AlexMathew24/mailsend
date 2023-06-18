package com.data.mailssend;

import java.security.PrivilegedAction;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class UserDAO {
private  static final String EMAIL_TO=ApplicationProperties.getProperty("email.to");;
    public static List<User> getUsers(Connection connection) throws SQLException {

        List<User> users =new ArrayList<>();
        List<String> emailList =getEmailList();
//       for(String email:emailList){
    //    String query="SELECT * FROM details where email=?";
            String query="SELECT * FROM details";
            try(PreparedStatement statement=connection.prepareStatement(query)){
//                statement.setString(1,email);
                ResultSet resultSet=statement.executeQuery();
                if(resultSet.next()){
                    users.add(new User(EMAIL_TO,query));
                }
            }
//        }
        return  users;

    }
    private static List<String> getEmailList(){

      //  return Arrays.asList(EMAIL_TO.split(","));
        return Arrays.asList(EMAIL_TO);
    }

}
