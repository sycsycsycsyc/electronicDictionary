package com.shi.electronicdictionary.service;

import com.shi.electronicdictionary.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.io.File;
import java.time.LocalDate;
import java.util.List;

@Service
public class OracleJob {
    @Autowired
    JavaMailSenderImpl mailSender;
    @Autowired
    UserService userService;
    @Autowired
    BackupDB backupDB;
    @Autowired
    MailService mailService;


    @Scheduled(cron = "0 0 0 * * ?")
    public void clear() {
        List<User> all = userService.getAll();
        for (int i = 0; i < all.size(); i++) {
            userService.updateFlag(0, all.get(i).getMail());
            if (all.get(i).getFlag2().equals("否")) {
                userService.updateFlag2("开", all.get(i).getMail());
            }
        }

    }
    @Scheduled(cron = "0 0 0 * * ?")
    //@Scheduled(cron = "0 */2 * * * ?")
    public void Backup() {
        LocalDate localDate = LocalDate.now();
        //保存七天
        LocalDate before = localDate.minusDays(7);
        String fileBeforeName = before.toString()+"_backup.sql";
        File fileBefore = new File("/electronicDictionary/db_backup/", fileBeforeName);
        if (fileBefore.exists()) {
            fileBefore.delete();
        }
        backupDB.exportSql(localDate.toString());
    }

    @Scheduled(cron = "0 */30 * * * ?")
    public void sendMail() {
        mailService.send();
    }

}

