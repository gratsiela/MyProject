package com.example.model;



import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import com.example.model.dao.DBUserDao;

public class PasswordSender {

		private static final long serialVersionUID = 1L;
		private static String username = "...";
		private static String password = "...";
		private static String recipient;

		public static boolean sendPassword(String email){
			String messageBody;
			DBUserDao dao=DBUserDao.getInstance();
			if(dao.getPassword(email)==null){
				messageBody="There is no user registered with that email address!";
			}else{
				messageBody="Your password is: "+dao.getPassword(email);
			}
			if(sendEmail(email,messageBody)){
				return true;
			}
			return false;
		}
		
		private static boolean sendEmail(String email, String messageBody) {
			recipient=email;
			
			Properties properties = System.getProperties();

			String host = "smtp.gmail.com";
			properties.put("mail.smtp.starttls.enable", "true");
			properties.put("mail.smtp.host", host);
			properties.put("mail.smtp.user", username);
			properties.put("mail.smtp.password", password);
			properties.put("mail.smtp.port", "587");
			properties.put("mail.smtp.auth", "true");
			Session session = Session.getDefaultInstance(properties);
			MimeMessage message = new MimeMessage(session);

			try {
				message.setFrom(new InternetAddress(username));
				InternetAddress recipientAddress = new InternetAddress(recipient);
				message.addRecipient(Message.RecipientType.TO, recipientAddress);
				message.setSubject("Forgotten Password");
				message.setText(messageBody);
				Transport transport = session.getTransport("smtp");
				transport.connect(host, username, password);
				transport.sendMessage(message, message.getAllRecipients());
				transport.close();
			} catch (MessagingException e) {
				System.out.println("Problem with sending email");
				return false;
			}
			return true;
		}
}
