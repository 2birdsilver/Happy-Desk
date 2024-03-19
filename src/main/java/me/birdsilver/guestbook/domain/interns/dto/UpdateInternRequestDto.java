package me.birdsilver.guestbook.domain.interns.dto;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@AllArgsConstructor
@NoArgsConstructor(force = true)
@Getter
@Setter
public class UpdateInternRequestDto {

    private Long id;
    private String introduction;

}
