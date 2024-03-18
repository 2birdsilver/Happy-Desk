package me.birdsilver.guestbook.domain.interns.controller;

import jakarta.annotation.Nullable;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import me.birdsilver.guestbook.domain.interns.dto.UpdateInternRequestDto;
import me.birdsilver.guestbook.domain.interns.entity.Intern;
import me.birdsilver.guestbook.domain.interns.service.FileService;
import me.birdsilver.guestbook.domain.interns.service.MemberService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.Principal;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/auth")
public class MyController {

    private final MemberService memberService;
    private final FileService fileService;


    /** 내 정보 수정 + 첨부파일 포함 */
    @PostMapping("/myinfo/update")
    public ResponseEntity<String> updateMyinfo(
            @RequestParam(value = "userId", required = false) long userId,
            @RequestParam(value = "introduction") String introduction,
            @RequestParam(value = "keyboard", required = false) MultipartFile keyboard[],
            @RequestParam(value = "mouse", required = false) MultipartFile mouse[],
            HttpServletRequest request, HttpServletResponse response, Model model) {
        {

            // 2. 프로필 소개글 업데이트
            UpdateInternRequestDto internRequestDto = new UpdateInternRequestDto(userId, introduction);
            Intern intern = memberService.updateUserInfo(internRequestDto);


            try {
                // 키보드 이미지가 있는 경우 이미지 변환 및 업로드
                if (keyboard != null && !mouse[0].isEmpty()) {
                    String extName = fileService.extractFileExt(keyboard[0]);
                    byte[] keyboardBytes = keyboard[0].getBytes();
                    fileService.updateUserImage(userId, "keyboard", keyboardBytes, extName);
                }

                // 마우스 이미지가 있는 경우 이미지 변환 및 업로드
                if (mouse != null && !mouse[0].isEmpty()) {
                    String extName = fileService.extractFileExt(mouse[0]);
                    byte[] mouseBytes = mouse[0].getBytes();
                    fileService.updateUserImage(userId, "mouse", mouseBytes, extName);
                }
            } catch (IOException e) {
                e.printStackTrace();
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Image upload failed");
            }
            return ResponseEntity.status(HttpStatus.CREATED).body("success");
        }
    }

        /** 이미지 가져오기 */
        @GetMapping("/img/{type}/{id}")
        public ResponseEntity<byte[]> getKeyboardImage(@PathVariable String type, @PathVariable Long id) {
            byte[] img = fileService.getImage(type, id);

            if (img == null) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            } else {
                MediaType mediaType = fileService.getImageExt(type,id);
                HttpHeaders headers = new HttpHeaders();
                headers.setCacheControl("no-cache, no-store, must-revalidate");
                headers.setPragma("no-cache");
                headers.setExpires(0);
                headers.setContentType(mediaType);
                return new ResponseEntity<>(img, headers, HttpStatus.OK);
            }
        }





}




