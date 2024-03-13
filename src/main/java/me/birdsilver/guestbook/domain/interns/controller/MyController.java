package me.birdsilver.guestbook.domain.interns.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import me.birdsilver.guestbook.domain.interns.dto.MemberLoginRequestDto;
import me.birdsilver.guestbook.domain.interns.dto.MemberLoginResponseDto;
import me.birdsilver.guestbook.domain.interns.dto.UpdateInternRequestDto;
import me.birdsilver.guestbook.domain.interns.entity.Intern;
import me.birdsilver.guestbook.domain.interns.service.FileService;
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
    private final FileService fileService;

    /** 간단 로그인 */
    @PostMapping("/login")
    public ResponseEntity<MemberLoginResponseDto> login(@RequestBody MemberLoginRequestDto requestDto) {
        MemberLoginResponseDto responseDto = memberService.login(requestDto.getEmail(), requestDto.getPassword());

        return  ResponseEntity.ok(responseDto);
    }

    /** 내 정보 수정 + 첨부파일 포함 */
    @PostMapping("/myinfo/{id}")
    public ResponseEntity<String> updateMyinfo(
        @RequestParam(value = "userId") Long userId,
        @RequestParam(value = "password") String password,
        @RequestParam(value = "introduction") String introduction,
        @RequestParam(value = "keyboard", required = false) MultipartFile keyboard[],
        @RequestParam(value = "mouse", required = false) MultipartFile mouse[],
        HttpServletRequest request, HttpServletResponse response, Model model)
    {
        // 1. 비밀번호 확인
        if (!memberService.checkMember(userId, password)) {
          return ResponseEntity.badRequest().body("Invalid user or password");
        }

        // 2. 프로필 소개글 업데이트
        UpdateInternRequestDto internRequestDto = new UpdateInternRequestDto(userId, introduction);
        Intern intern = memberService.updateUserInfo(internRequestDto);

        String setKeyboardPath = "";
        String setMousePath = "";

        // 3. 이미지가 있는 경우 이미지 업로드
        if (keyboard != null && keyboard.length > 0 && !keyboard[0].isEmpty()) {
            setKeyboardPath = fileService.uploadImg(userId, keyboard, "keyboard");
            memberService.addImg(userId, "keyboard", setKeyboardPath);
        }
        if (mouse != null && mouse.length > 0 && !mouse[0].isEmpty()) {
            setMousePath = fileService.uploadImg(userId, mouse, "mouse");
            memberService.addImg(userId, "mouse", setMousePath);
        }

        return ResponseEntity.status(HttpStatus.CREATED).body("success");

    }

    /** 이미지 가져오기 (키보드 또는 마우스) */
    @GetMapping(value = "/image/{type}/{id}")
    public ResponseEntity<?> returnImage(@PathVariable String type, @PathVariable Long id) {
        String baseDir = System.getProperty("user.dir") + "\\src\\main\\resources\\";
        String defaultImageName = type + "_0.png"; // Default image for both types

        // Determine the directory based on type
        String directoryPath = baseDir + "image\\" + (type.equals("keyboard") ? "keyboard\\" : "mouse\\");
//        System.out.println("directoryPath:" + directoryPath);

        // Attempt to find the specific image for the given ID
        File file = new File(baseDir + "\\" + memberService.getImagePath(type, id));

//        System.out.println("file:" + memberService.getImagePath(type, id));
        // Fall back to default image if the specific one does not exist
        if (!file.exists()) {
            file = new File(directoryPath + defaultImageName);
        }

        // Try to return the image file as a byte array
        try {
            byte[] imageData = FileCopyUtils.copyToByteArray(file);
            HttpHeaders headers = new HttpHeaders();
            headers.add("Content-Type", Files.probeContentType(file.toPath()));

            return new ResponseEntity<>(imageData, headers, HttpStatus.OK);
        } catch (IOException e) {
            // Log the error or handle it appropriately
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error retrieving image");
        }
    }



}




