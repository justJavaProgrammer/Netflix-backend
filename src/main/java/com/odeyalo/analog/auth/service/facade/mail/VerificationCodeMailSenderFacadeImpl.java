package com.odeyalo.analog.auth.service.facade.mail;

import com.odeyalo.analog.auth.entity.User;
import com.odeyalo.analog.auth.entity.VerificationCode;
import com.odeyalo.analog.auth.service.register.mail.MailSender;
import com.odeyalo.analog.auth.service.support.verification.CodeVerificationManager;
import org.springframework.stereotype.Component;

@Component
public class VerificationCodeMailSenderFacadeImpl implements VerificationCodeMailSenderFacade {
    private final MailSender mailSender;
    private final CodeVerificationManager verificationManager;

    public VerificationCodeMailSenderFacadeImpl(MailSender mailSender, CodeVerificationManager verificationManager) {
        this.mailSender = mailSender;
        this.verificationManager = verificationManager;
    }

    @Override
    public void generateAndSend(User user, Integer codeLength) {
        VerificationCode verificationCode = this.verificationManager.generateAndSave(user, codeLength, CodeVerificationManager.DEFAULT_ACTIVE_MINUTES);
        String codeValue = verificationCode.getCodeValue();
        this.mailSender.send("Your code is: " + codeValue," Verification code", user.getEmail());
    }
}
