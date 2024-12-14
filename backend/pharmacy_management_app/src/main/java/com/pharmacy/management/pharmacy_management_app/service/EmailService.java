//package com.pharmacy.management.pharmacy_management_app.service;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.mail.SimpleMailMessage;
//import org.springframework.mail.javamail.JavaMailSender;
//import org.springframework.stereotype.Service;
//
//@Service
//public class EmailService {
//    @Autowired
//    private JavaMailSender mailSender;
//
//    public void sendOtpEmail(String toEmail, String otp) {
//        SimpleMailMessage message = new SimpleMailMessage();
//        message.setFrom("chikukaspierre@gmail.com","Pharmacy Web App");
//        message.setTo(toEmail);
//        message.setSubject("Your Login OTP for Pharmacy Management App");
//        message.setText("Your One-Time Password (OTP) is: " + otp +
//                "\n\nThis OTP is valid for one-time use. " +
//                "Do not share it with anyone.");
//
//        mailSender.send(message);
//    }
//    public void sendOtpForResetingPassword(String toEmail, String otp) {
//        SimpleMailMessage message = new SimpleMailMessage();
//        message.setFrom("chikukaspierre@gmail.com");
//        message.setTo(toEmail);
//        message.setSubject("Your Reset password OTP for Pharmacy Management App");
//        message.setText("Your One-Time Password (OTP) is: " + otp +
//                "\n\nThis OTP is valid for one-time use. " +
//                "Do not share it with anyone.");
//
//        mailSender.send(message);
//    }
//}
package com.pharmacy.management.pharmacy_management_app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import jakarta.mail.internet.MimeMessage;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender mailSender;

    public void sendOtpEmail(String toEmail, String otp) {
        try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);

            // Set the custom sender name
            helper.setFrom("chikukaspierre@gmail.com", "Pharmacy Web App");
            helper.setTo(toEmail);
            helper.setSubject("Your Login OTP for Pharmacy Management App");
            helper.setText("Your One-Time Password (OTP) is: " + otp +
                    "\n\nThis OTP is valid for one-time use. " +
                    "Do not share it with anyone.", true);

            mailSender.send(message);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void sendOtpForResetingPassword(String toEmail, String otp) {
        try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);

            // Set the custom sender name
            helper.setFrom("chikukaspierre@gmail.com", "Pharmacy Web App");
            helper.setTo(toEmail);
            helper.setSubject("Your Reset Password OTP for Pharmacy Management App");
            helper.setText("Your One-Time Password (OTP) is: " + otp +
                    "\n\nThis OTP is valid for one-time use. " +
                    "Do not share it with anyone.", true);

            mailSender.send(message);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
