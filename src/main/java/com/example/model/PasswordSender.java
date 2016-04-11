package com.example.model;



import java.util.List;
import java.util.Properties;
import java.util.Random;
import java.util.Vector;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import com.example.model.dao.DBUserDao;

public class PasswordSender {

		private static final long serialVersionUID = 1L;
		private static String username = "taglibro2016";
		private static String password = "12345678GK";
		private static String recipient;
		
		private static final String ALPHA_CAPS  = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
	    private static final String ALPHA   = "abcdefghijklmnopqrstuvwxyz";
	    private static final String NUM     = "0123456789";

		public static boolean sendPassword(String email){
			String messageBody;
			DBUserDao dao=DBUserDao.getInstance();
			String newPassword = String.valueOf(generatePswd());
			System.out.println(newPassword);
			if(dao.getPassword(email)==null){
				messageBody="There is no user registered with that email address!";
			}else{
				messageBody="Your new password is: "+ newPassword;
			}
			if(sendEmail(email,messageBody)){
				System.out.println("Email sended");
				dao.updatePassword(email, newPassword);
				return true;
			}
			System.out.println("Fail with sending the email");
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
		
		
		 public static char[] generatePswd() {
			 int len = 6;
			 int noOfCAPSAlpha = 1;
			 int noOfAlpha = 2;
		        char[] pswd = new char[len];
		        int index = 0;
		        Random rnd = new Random();
		        for (int i = 0; i < noOfCAPSAlpha; i++) {
		            index = getNextIndex(rnd, len, pswd);
		            pswd[index] = ALPHA_CAPS.charAt(rnd.nextInt(ALPHA_CAPS.length()));
		        }
		        for (int i = 0; i < noOfAlpha; i++) {
		            index = getNextIndex(rnd, len, pswd);
		            pswd[index] = ALPHA.charAt(rnd.nextInt(ALPHA.length()));
		        }
		       
		        for(int i = 0; i < len; i++) {
		            if(pswd[i] == 0) {
		                pswd[i] =NUM.charAt(rnd.nextInt(NUM.length())); 
		            }
		        }
		        return pswd;
		    }
		     
		    private static int getNextIndex(Random rnd, int len, char[] pswd) {
		        int index = rnd.nextInt(len);
		        while(pswd[index = rnd.nextInt(len)] != 0);
		        return index;
		    }
		
}
