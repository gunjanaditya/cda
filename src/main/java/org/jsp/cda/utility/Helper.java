package org.jsp.cda.utility;

import org.jsp.cda.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

@Service
public class Helper {

	@Autowired
	private JavaMailSender javaMailSender;

	public int generateOtp() {
		double otp = 0;
		while (otp < 1000) {
			otp = Math.random() * 10000;
		}
		return (int) otp;
	}

	public void sendAccountCreationEmail(User user) {

		MimeMessage mimeMessage = javaMailSender.createMimeMessage();

		MimeMessageHelper helper = new MimeMessageHelper(mimeMessage);
		try {
			helper.setTo(user.getEmail());
			helper.setSubject("Account Created");
			helper.setText("<html><body><h2>Dear " + user.getName()
					+ ", your Account has been created successfully, your OTP is : " + user.getOtp()
					+ "</h2></body></html>", true);
			javaMailSender.send(mimeMessage);

		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
