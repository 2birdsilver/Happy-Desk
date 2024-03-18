package me.birdsilver.guestbook.domain.interns.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import me.birdsilver.guestbook.domain.interns.dao.MemberRepository;
import me.birdsilver.guestbook.domain.interns.entity.Intern;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@RequiredArgsConstructor
@Service
public class FileService {

    private final MemberRepository memberRepository;

    /** id 로 인턴 찾기 */
    public Intern findById(long id) {
        return memberRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("not found : " + id));
    }

    /** DB 에 이미지 저장 */
    @Transactional
    public void updateUserImage(Long id, String type, byte[] file, String mediaType) {
        Intern intern = findById(id);

        intern.uploadImg(type, file, mediaType);
    }

    /** DB에 파일 확장자 저장 */
    public String extractFileExt(MultipartFile file) {
        MultipartFile multi = file;
        String originFilename = multi.getOriginalFilename();
        String extName = originFilename.substring(originFilename.lastIndexOf(".") + 1);
//        System.out.println("extensionName : " + extName);
        return extName;
    }

    /** 이미지 파일 가져오기 */
    public byte[] getImage(String type, Long id) {
        Intern intern = findById(id);

        if ("keyboard".equals(type)) {
            return intern.getKeyboardImg();
        }
        if ("mouse".equals(type)) {
            return intern.getMouseImg();
        }
        return new byte[0];
    }

    /** 이미지 확장자 가져오기 */
    public MediaType getImageExt(String type, Long id) {
        Intern intern = findById(id);

        String imgMediaType = "";
        if ("keyboard".equals(type)) {
            imgMediaType = intern.getKeyboardType();
        }
        if ("mouse".equals(type)) {
            imgMediaType = intern.getMouseType();
        }

        switch (imgMediaType.toLowerCase()) {
            case "png":
                return MediaType.IMAGE_PNG;
            case "jpg":
            case "jpeg":
                return MediaType.IMAGE_JPEG;
            case "gif":
                return MediaType.IMAGE_GIF;
            // 기타 지원하는 이미지 형식에 대한 케이스 추가
            default:
                return MediaType.APPLICATION_OCTET_STREAM; // 알려지지 않은 파일 타입
        }
    }







}
