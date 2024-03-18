package me.birdsilver.guestbook.domain.interns.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor(force = true)
@Getter
@Setter
public class UpdateInternRequestDto {

    private Long id;
    private String introduction;

}
