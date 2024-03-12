package me.birdsilver.guestbook.domain.interns.service;

import lombok.RequiredArgsConstructor;
import me.birdsilver.guestbook.domain.interns.dao.MemberRepository;
import me.birdsilver.guestbook.domain.interns.entity.Intern;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@RequiredArgsConstructor
@Service
public class FileService {

    private final MemberRepository memberRepository;

    public String uploadImg(Long userId, MultipartFile[] files, String deviceType) {
        MultipartFile multi = files[0];
        if (multi.isEmpty()) {
            return ""; // Early return if the file is empty
        }

        String directoryName = deviceType.equals("keyboard") ? "keyboard" : "mouse";

        try {
            // 파일 경로 설정
            String originFilename = multi.getOriginalFilename();
            String extName = originFilename.substring(originFilename.lastIndexOf("."));
            String saveFileName = directoryName + "_" +  userId + extName;

            String path = System.getProperty("user.dir") + "\\src\\main\\resources\\image\\" + directoryName;

            System.out.println("uploadpath : " + path);
            System.out.println("originFilename : " + originFilename);
            System.out.println("extensionName : " + extName);
            System.out.println("saveFileName : " + saveFileName);

//            // 파일 경로 최종
//            String path2 = System.getProperty("user.dir");
//            // # 윈도우
//            String path3 = "\\src\\main\\resources\\image\\keyboard";
//            // # 리눅스
//            // String path3 = "/src/main/resources/image/keyboard_" + id;
//            System.out.println("Working Directory = " + path2 + path3);

            // Ensure the directory exists
            File directory = new File(path);
            if (!directory.exists()) {
                directory.mkdirs(); // Create the directory if it doesn't exist
                System.out.println("Directory created: " + directory.getPath());
            } else {
                System.out.println("Directory already exists.");
            }

            // File save path
            File file = new File(directory, saveFileName);
            // Save the file
            multi.transferTo(file);

            // Set the database path
            String setpath = "/image/" + directoryName + "/" + saveFileName;
            System.out.println("Database path: " + setpath);

            return setpath;

        } catch (Exception e) {
                System.out.println(e);
        }

        return directoryName;
    }




}
