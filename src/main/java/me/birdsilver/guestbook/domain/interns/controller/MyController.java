package me.birdsilver.guestbook.domain.interns.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import me.birdsilver.guestbook.domain.interns.dto.MemberLoginRequestDto;
import me.birdsilver.guestbook.domain.interns.dto.MemberLoginResponseDto;
import me.birdsilver.guestbook.domain.interns.dto.UpdateInternRequestDto;
import me.birdsilver.guestbook.domain.interns.entity.Intern;
import me.birdsilver.guestbook.domain.interns.service.MemberService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

@RequiredArgsConstructor
@RestController
@RequestMapping("/auth")
public class MyController {

    private final MemberService memberService;

    /** 간단 로그인 */
    @PostMapping("/login")
    public ResponseEntity<MemberLoginResponseDto> login(@RequestBody MemberLoginRequestDto requestDto) {
        MemberLoginResponseDto responseDto = memberService.login(requestDto.getEmail(), requestDto.getPassword());

        return  ResponseEntity.ok(responseDto);
    }

    /** 내 정보 수정 + 첨부파일 포함 */
    @PostMapping("/myinfo/{id}")
    public ResponseEntity<?> updateMyinfo(
        @RequestParam(value = "userId") Long userId,
        @RequestParam(value = "password") String password,
        @RequestParam(value = "introduction") String introduction,
        @RequestParam(value = "keyboard", required = false) MultipartFile keyboard[],
        HttpServletRequest request, HttpServletResponse response, Model model)
        {
        // 1. 비밀번호 확인
        if (!memberService.checkMember(userId, password)) {
          return ResponseEntity.badRequest().body("Invalid user or password");
        }
        // 2. 프로필 소개글 업데이트
        UpdateInternRequestDto internRequestDto = new UpdateInternRequestDto(userId, introduction);
        Intern intern = memberService.updateUserInfo(internRequestDto);

        // 3. 키보드 이미지 업로드
        // 이미지가 있는경우
        if (keyboard != null) {
            MultipartFile multi = keyboard[0];
            String path = "c:\\img";
            System.out.println(path);

            try {
                // 파일 경로 설정
                String uploadpath = path;
                String originFilename = multi.getOriginalFilename();
                String extName = originFilename.substring(originFilename.lastIndexOf("."), originFilename.length());
                // String saveFileName = genSaveFileName(extName);
                String saveFileName = "keyboard_" +  userId + extName;

                System.out.println("uploadpath : " + uploadpath);
                System.out.println("originFilename : " + originFilename);
                System.out.println("extensionName : " + extName);
                System.out.println("saveFileName : " + saveFileName);

                // 파일 경로 최종
                String path2 = System.getProperty("user.dir");
                // # 윈도우
                 String path3 = "\\src\\main\\resources\\image\\keyboard";
                // # 리눅스
                // String path3 = "/src/main/resources/image/keyboard_" + id;
                System.out.println("Working Directory = " + path2 + path3);

                // 디비 파일 경로
                String setpath = "/image/keyboard/" + saveFileName;
                System.out.println("setpath =" + setpath);

                if (!multi.isEmpty()) {
                    File forder = new File(path2 + path3);
                    forder.mkdir();
                    System.out.println("forder" + forder);

                    // 해당 디렉토리가 없을경우 디렉토리를 생성합니다.
                    if (!forder.exists()) {
                        try {
                            forder.mkdirs(); // 폴더 생성합니다.
                            System.out.println("폴더가 생성되었습니다.");
                        } catch (Exception e) {
                            e.getStackTrace();
                        }
                    } else {
                        System.out.println("이미 폴더가 생성되어 있습니다.");
                    }
                    // 파일 저장 경로/파일이름
                    File file = new File(path2 + path3, saveFileName);
                    // 파일 저장
                    multi.transferTo(file);
                    // files 등록
                    memberService.addImg(userId, setpath);
                }
            } catch (Exception e) {
                System.out.println(e);
                }
            }
            Intern interns = memberService.findById(userId);
            return ResponseEntity.status(HttpStatus.CREATED).body(interns);
        }

    /** 키보드 이미지 가져오기 */
    @GetMapping(value = "/keyboard/{id}")
    public ResponseEntity<?> returnImage(@PathVariable Long id) {
        String path1 = System.getProperty("user.dir");

        // # 윈도우
         String path2 = "\\src\\main\\resources\\";

        // # 리눅스
//        String path2 = "/src/main/resources/image/keyboard/"

        System.out.println("keyboard: " + memberService.getKeyboard(id));

        File file = new File("");

        File file1 = new File(path1 + path2 + memberService.getKeyboard(id));
        // # 윈도우
         File file2 = new File(path1 + "\\src\\main\\resources\\image\\keyboard\\keyboard_0.png");
        // # 리눅스
//        File file2 = new File(path1 + "/src/main/resources/keyboard/keyboard_0.png");

        if (file1.exists()) {
            file = file1;
        } else {
            file = file2;
        }

        // 저장된 이미지파일의 이진데이터 형식을 구함
        byte[] result = null;
        ResponseEntity<byte[]> entity = null;
        try {
            result = FileCopyUtils.copyToByteArray(file);

            // 2. header
            HttpHeaders header = new HttpHeaders();
            header.add("Content-type", Files.probeContentType(file.toPath())); // 파일의 컨텐츠타입을 직접 구해서 header에 저장

            // 3. 응답본문
            entity = new ResponseEntity<>(result, header, HttpStatus.OK);// 데이터, 헤더, 상태값
        } catch (IOException e) {
            e.printStackTrace();
        }

        return entity;
    }



}




