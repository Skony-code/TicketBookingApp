package skoneczny.touk.ticketbookingapp.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class ScreeningDTO {
    private int id;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime starTime;
    private String title;
}
