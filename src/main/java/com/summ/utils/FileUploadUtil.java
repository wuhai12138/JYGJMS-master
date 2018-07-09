package com.summ.utils;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.net.FileNameMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author summ
 * @date 17/12/18
 *
 */
public class FileUploadUtil {

    public static String Upload(MultipartFile file){
        String filePath = null;
        String fileName = null;
        if (!file.isEmpty()) {
            try {
                Pattern pattern = Pattern.compile("\\.[a-z|A-Z]+$");
                Matcher matcher = pattern.matcher(file.getOriginalFilename());
                if (matcher.find()) {
                    // 文件保存路径
                    fileName = System.currentTimeMillis() + matcher.group(0);
                    filePath = "C:/Users/summ/Desktop/upload/" + fileName;
//                    filePath = "/opt/data/source/uploaded/" + System.currentTimeMillis() + matcher.group(0);
                } else {
                    return null;
                }

                // 转存文件
                file.transferTo(new File(filePath));
            } catch (Exception e) {
                e.printStackTrace();
                return "server err";
            }
        } else {
            return "file is empty.";
        }
        return fileName;
    }

}
