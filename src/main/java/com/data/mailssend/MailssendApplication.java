package com.data.mailssend;



import jakarta.mail.*;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeBodyPart;
import jakarta.mail.internet.MimeMessage;
import jakarta.mail.internet.MimeMultipart;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Path;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

//@SpringBootApplication
public class MailssendApplication {

	public static void main(String[] args) {
		//SpringApplication.run(MailssendApplication.class, args);

		try (Connection connection = DatabaseUtil.getConnection()) {

			List<User> users = UserDAO.getUsers(connection);
			for (User user : users) {
				List<Data> dataList = DataDAO.fetchData(connection, user.getQuery());
				Workbook workbook = WorkbookCreator.createWorkbook(dataList);
				System.out.println("1");
				String filePath = "src/main/resources/Static/Data.xlsx";
				WorkbookWriter.saveWorkbook(workbook, filePath);
				EmailSender.sendEmailWithAttachment(user.getEmail(), filePath);
				System.out.println("data export success and email send");

			}
		} catch (SQLException | IOException | MessagingException e) {
			e.printStackTrace();
		}
	}
}




