package com.data.mailssend;

import java.security.PrivilegedAction;
import java.sql.*;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class UserDAO {
private  static final String EMAIL_TO=ApplicationProperties.getProperty("email.to");;
    public static List<User> getUsers(Connection connection) throws SQLException {
        //date update
        LocalDate currentDate = LocalDate.now();
        LocalDate sevenDaysbefore =currentDate.minus(7, ChronoUnit.DAYS);

        System.out.println("currentDate"+currentDate+"  sevenDaysbefore"+sevenDaysbefore);

        List<User> users =new ArrayList<>();

        String query="select user.user_id,user.forename,user_extranet_access.role_flag from user inner join user_extranet_access on user.user_id=user_extranet_access.user_id;";
        //String query = "between ? and ?";
            try(PreparedStatement statement=connection.prepareStatement(query)){
               // Date.valueOf(startDate)
                //PreparedStatement.setDate(1,);
                ResultSet resultSet=statement.executeQuery();
                if(resultSet.next()){
                    users.add(new User(EMAIL_TO,query));
                }
            }

        return  users;

    }

}
