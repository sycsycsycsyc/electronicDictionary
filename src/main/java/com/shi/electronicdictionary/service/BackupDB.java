package com.shi.electronicdictionary.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;


@Service
public class BackupDB{
    @Value("${spring.datasource.url}")
    private String url;
    @Value("${spring.datasource.username}")
    private String userName;
    @Value("${spring.datasource.password}")
    private String password;
    private String sqlPath="/electronicDictionary/db_backup/";

    //获取数据库名
    public String getDataBaseName() {
        return url.substring(url.indexOf("3306"), url.indexOf("?")).replaceAll("/", "").replaceAll("3306", "");
    }

    // 获取主机地址
    private String getHost() {
        return url.substring(url.indexOf("mysql"), url.indexOf("3306")).replace(":", "").replace("//", "").replace("mysql", "");
    }

    // 导出 sql 并返回相关信息
    public void exportSql(String time) {        // 指定导出的 sql 存放的文件夹
        File saveFile = new File(sqlPath);
        if (!saveFile.exists()) {
            saveFile.mkdirs();
        }
        String host = getHost();
        String dataBaseName = getDataBaseName();
        String fileName = time + "_" + "backup.sql";
        StringBuilder sb = new StringBuilder();        // 拼接备份命令
        sb.append("mysqldump").append(" --opt").append(" -h ").append(host).append(" --user=").append(userName).append(" --password=").append(password);
        sb.append(" --result-file=").append(sqlPath + fileName).append(" --default-character-set=utf8 ").append(dataBaseName);
        try {
            Process exec = Runtime.getRuntime().exec(sb.toString());
            if (exec.waitFor() == 0) {
                System.out.println("数据库备份成功，保存路径：" + sqlPath);
            } else {
                System.out.println("process.waitFor()=" + exec.waitFor());
            }
        } catch (IOException e) {
            System.out.println("备份 数据库 出现 IO异常 "+e);
        } catch (InterruptedException e) {
            System.out.println("备份 数据库 出现 线程中断异常 "+e);
        } catch (Exception e) {
            System.out.println("备份 数据库 出现 其他异常 "+e);
        }
    }
}
