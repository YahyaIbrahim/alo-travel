package com.wellware.DTO;


import com.wellware.data.entities.Profile;
import com.wellware.services.framework.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
//import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class RegistrationListener implements
        ApplicationListener<OnRegistrationCompleteEvent> {

    @Autowired
    private ProfileService profileService;

//    @Autowired
//    private JavaMailSender mailSender;

    @Override
    public void onApplicationEvent(OnRegistrationCompleteEvent event) {
        this.confirmRegistration(event);
    }

    private void confirmRegistration(OnRegistrationCompleteEvent event) {
        Profile user = event.getUser();
        String token = UUID.randomUUID().toString();
        profileService.createVerificationToken(user, token);

        String recipientAddress = user.getEmail();
        String subject = "Voyager - Tour Guide Registration Confirmation";
        String confirmationUrl
                = event.getAppUrl() + "/registrationConfirm?token=" + token;
        String message = "Thank you for creating account with Voyager Ventures, " + user.getDisplayName() +
                "\n\nWelcome to Voyager!\n" +
                "Please activate your account!\n\n" +
                "This link will be available for 24 hours only ";

//        SimpleMailMessage email = new SimpleMailMessage();
//        email.setTo(recipientAddress);
//        email.setSubject(subject);
//        email.setText(message + " http://localhost:8080" + confirmationUrl);
        //mailSender.send(email);
    }
}
