package ru.tokarev.shop.flow.register;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.binding.message.MessageBuilder;
import org.springframework.binding.message.MessageContext;
import org.springframework.transaction.annotation.Transactional;
import ru.tokarev.shop.service.gender.GenderService;
import ru.tokarev.shop.service.mailSender.MailSendService;
import ru.tokarev.shop.service.repr.SystemUser;
import ru.tokarev.shop.service.user.UserService;


public class UserRegisterHandler {

    private static final Logger logger = LoggerFactory.getLogger(UserRegisterHandler.class);

    private static final String FAILURE = "failure";
    private static final String SUCCESS = "success";

    private final UserService userService;

    private GenderService genderService;

    @Autowired
    public void setGenderService(GenderService genderService) {
        this.genderService = genderService;
    }

    private MailSendService mailSendService;

    @Autowired
    public void setMailSendService(MailSendService mailSendService) {
        this.mailSendService = mailSendService;
    }

    @Autowired
    public UserRegisterHandler(UserService userService) {
        this.userService = userService;
    }

    public UserRegisterModel init() {
        return new UserRegisterModel();
    }

    public void addBasicUserInfo(UserRegisterModel userRegisterModel, BasicUserInfo basicUserInfo) {
        userRegisterModel.setBasicUserInfo(basicUserInfo);
    }

    public void addPersonalUserInfo(UserRegisterModel userRegisterModel, PersonalUserInfo personalUserInfo) {
        userRegisterModel.setPersonalUserInfo(personalUserInfo);
    }

    public String validateBasicUserInfo(BasicUserInfo basicUserInfo, MessageContext error) {
        if (!basicUserInfo.getPassword().equals(basicUserInfo.getConfirmPassword())) {
            error.addMessage(new MessageBuilder()
                    .error()
                    .source("confirmPassword")
                    .defaultText("Password doesn't match up the confirm password!")
                    .build());

            return FAILURE;
        }
        else if(basicUserInfo.getPassword().length() < 8 || basicUserInfo.getPassword().length() > 20) {
            error.addMessage(new MessageBuilder()
                    .error()
                    .source("confirmPassword")
                    .defaultText("The password must be at least 8 and no more than 20 characters!")
                    .build());

            return FAILURE;
        }
        return SUCCESS;
    }

    public String validatePersonalUserInfo(PersonalUserInfo personalUserInfo, MessageContext error) {
        return SUCCESS;
    }

    public String saveAll(UserRegisterModel urm, MessageContext error) {
        try {
            SystemUser systemUser = new SystemUser(
                    urm.getPersonalUserInfo().getFirstName(),
                    urm.getPersonalUserInfo().getLastName(),
                    urm.getBasicUserInfo().getNumberPhone(),
                    urm.getBasicUserInfo().getPassword(),
                    urm.getBasicUserInfo().getEmail());
            if(genderService.isPresent(urm.getPersonalUserInfo().getGender())) {
                systemUser.setGender(genderService.findOneByNameGender(urm.getPersonalUserInfo().getGender()));
            }
            userService.save(systemUser);
            mailSendService.sendMail(
                    systemUser.getEmail(),
                    "Registration for Shop-Electro is successful!",
                    "Dear " + systemUser.getFirstUserName() +
                            "! You have successfully registered in our online store Shop-Electro!");
        } catch (Exception ex) {
            logger.error("", ex);
            error.addMessage(new MessageBuilder()
                    .error()
                    .source("email")
                    .defaultText("Internal error. Can't complete registration.")
                    .build());
            return FAILURE;
        }
        return SUCCESS;
    }
}
