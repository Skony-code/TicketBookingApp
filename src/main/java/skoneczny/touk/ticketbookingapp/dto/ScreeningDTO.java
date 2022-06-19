package skoneczny.touk.ticketbookingapp.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class ScreeningDTO {
    private LocalDate starTime;
    private String title;
}
