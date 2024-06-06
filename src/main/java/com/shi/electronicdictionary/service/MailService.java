package com.shi.electronicdictionary.service;

import com.shi.electronicdictionary.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.util.List;

@Service
public class MailService {
    @Autowired
    JavaMailSenderImpl mailSender;
    @Autowired
    UserService userService;

    public void send() {
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setSubject("小型电子词典系统系统提醒");
        mailMessage.setFrom("1010707431@qq.com");
        List<User> all = userService.getAll();
        LocalTime time = LocalTime.now().withNano(0);
        for (int i = 0; i < all.size(); i++) {
            if (all.get(i).getFlag2().equals("开")) {
                if (all.get(i).getFlag() < all.get(i).getCount()) {
                    if (all.get(i).getTime().compareTo(time) < 0) {
                        mailMessage.setText("背诵提醒：您今日未达到您期望的背诵目标。" +
                                "您的目标:" + all.get(i).getCount() + "个。今日背诵：" +
                                all.get(i).getFlag() + "个。");
                        userService.updateFlag2("否", all.get(i).getMail());
                        mailMessage.setTo(all.get(i).getMail());
                        mailSender.send(mailMessage);
                    }
                }
            }
        }
    }


    public void forget(User user) {
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setSubject("小型电子词典系统系统提醒");
        mailMessage.setFrom("1010707431@qq.com");
        mailMessage.setText("您的密码为：'" + user.getPassword() + "'.请不要透露给他人");
        mailMessage.setTo(user.getMail());
        mailSender.send(mailMessage);

    }
}

