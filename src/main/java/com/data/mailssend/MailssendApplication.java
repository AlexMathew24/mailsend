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

import javax.xml.crypto.Data;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Path;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

@SpringBootApplication
public class MailssendApplication {
private static final String JDBC_URL="jdbc:mysql://localhost:3306/data";
private static  final String USERNAME="root";
private static final String PASSWORD="123456";

	private static final String EMAIL_HOST="smtp.gmail.com";
	private static final String EMAIL_PORT="587";
	private  static final String EMAIL_USERNAME="alex2244mathew@gmail.com";
	private static final String EMAIL_PASSWORD="yvzofwxlvtrgpdih";

	private static final String EMAIL_RECIPIENT="alxmathew123@gmail.com";
	private static final String EMAIL_SUBJECT="Test Purpose";
	public static void main(String[] args) {
		SpringApplication.run(MailssendApplication.class, args);

		try(Connection connection= DriverManager.getConnection(JDBC_URL,USERNAME,PASSWORD)){
			String query="SELECT * FROM details ";
			List<Data> dataList =fetchData(connection,query);
			Workbook workbook=createWorkbook(dataList);
			String filePath="src/main/resources/static/Data.xlsx";
			saveWorkbook(workbook,filePath);
			sendEmailWithAttachment(filePath);
			System.out.println("data export success and email send");
		}catch(SQLException | IOException | MessagingException e){
			e.printStackTrace();
		}
	}


	private static List<Data> fetchData(Connection connection,String query) throws SQLException{
		List<Data> dataList=new ArrayList<>();
		try(PreparedStatement statement=connection.prepareStatement(query);
			ResultSet resultSet=statement.executeQuery()){
			while(resultSet.next()){
				long id=resultSet.getLong("id");
				String name=resultSet.getString("name");
				String descrption=resultSet.getString("descrption");
				Data data =new Data(id,name,descrption);
				dataList.add(data);
			}
		}
		return dataList;
	}

	private  static Workbook createWorkbook(List<Data> dataList){
		Workbook workbook=new XSSFWorkbook();
		Sheet sheet = workbook.createSheet("Data");
		createHederRow(sheet);
		populateDataRows(sheet,dataList);

		return workbook;

	}

	private static void createHederRow(Sheet sheet){
		Row headerRow=sheet.createRow(0);
		String[] header={"ID","Name","descrption"};
		for(int i=0;i<header.length;i++){
			Cell cell=headerRow.createCell(i);
			cell.setCellValue(header[i]);
		}
	}
	private  static  void populateDataRows(Sheet sheet,List<Data> dataList){
		for(int i=0;i<dataList.size();i++){
			Data data =dataList.get(i);
			Row dataRow=sheet.createRow(i+1);

			Cell idCell= dataRow.createCell(0);
			idCell.setCellValue(data.getId());

			Cell nameCell= dataRow.createCell(1);
			nameCell.setCellValue(data.getName());

			Cell descriptionCell= dataRow.createCell(2);
			descriptionCell.setCellValue(data.getDescrption());


		}
	}

	private static  void saveWorkbook(Workbook workbook,String filePath)throws IOException{
		Path path=Path.of(filePath);
		try(FileOutputStream fos =new FileOutputStream(path.toFile())){
			workbook.write(fos);
		}
	}

	private static  void sendEmailWithAttachment(String filePath) throws IOException, MessagingException {
		//Email configuration
		Properties props =new Properties();
		props.put("mail.smtp.host",EMAIL_HOST);
		props.put("mail.smtp.port",EMAIL_PORT);
		props.put("mail.smtp.auth","true");
		props.put("mail.smtp.starttls.enable","true");

		Session session= Session.getInstance(props,new Authenticator(){
			protected PasswordAuthentication getPasswordAuthentication(){
				return new PasswordAuthentication(EMAIL_USERNAME,EMAIL_PASSWORD);
			}
		});

		//create a new email message
		Message message =new MimeMessage(session);
		message.setFrom(new InternetAddress(EMAIL_USERNAME));
		message.setRecipients(Message.RecipientType.TO,InternetAddress.parse(EMAIL_RECIPIENT));
		message.setSubject(EMAIL_SUBJECT);

		//create the email body
		MimeBodyPart messageBodyPart = new MimeBodyPart();
		messageBodyPart.setText("Please find the attached data.");

		//Attach the Excel file
		MimeBodyPart attachmentBodyPart= new MimeBodyPart();
		attachmentBodyPart.attachFile(new java.io.File(filePath));

		Multipart multipart= new MimeMultipart();
		multipart.addBodyPart(messageBodyPart);
		multipart.addBodyPart(attachmentBodyPart);

		message.setContent(multipart);
		message.setHeader("Return-Path",EMAIL_SUBJECT);
		Transport.send(message);


	}

	public static  class Data{
		private long id;
		private  String name;
		private  String descrption;

		public String getDescrption() {
			return descrption;
		}

		public void setDescrption(String descrption) {
			this.descrption = descrption;
		}


		public Data(long id, String name,String descrption){
			this.id=id;
			this.name=name;
			this.descrption=descrption;
		}
		public long getId() {
			return id;
		}
		public void setId(long id) {
			this.id = id;
		}
		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}
	}



}
