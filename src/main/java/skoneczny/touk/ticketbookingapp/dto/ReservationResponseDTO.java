package skoneczny.touk.ticketbookingapp.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class ReservationResponseDTO {
    private Float totalPrice;
    @JsonFormat(pattern = "yyyy-mm-dd hh:MM")
    private LocalDate expirationDate;
}
