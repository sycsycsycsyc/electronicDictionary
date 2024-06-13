package com.shi.electronicdictionary.service;

import com.shi.electronicdictionary.pojo.User;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.File;
import java.time.LocalDate;
import java.util.List;

@Service
public class OracleJob {
    @Resource
    UserService userService;
    @Resource
    BackupDB backupDB;
    @Resource
    MailService mailService;


    @Scheduled(cron = "0 0 0 * * ?")
    public void clear() {
        List<User> all = userService.getAll();
        for (User user : all) {
            userService.updateFlag(0, user.getMail());
            if (user.getFlag2().equals("否")) {
                userService.updateFlag2("开", user.getMail());
            }
        }

    }
    @Scheduled(cron = "0 0 0 * * ?")
    //@Scheduled(cron = "0 */2 * * * ?")
    public void Backup() {
        LocalDate localDate = LocalDate.now();
        //保存七天
        LocalDate before = localDate.minusDays(7);
        String fileBeforeName = before +"_backup.sql";
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

