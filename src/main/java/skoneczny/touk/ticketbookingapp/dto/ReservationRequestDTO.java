package skoneczny.touk.ticketbookingapp.dto;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.Pattern;
import java.util.List;

@Data
@Builder
public class ReservationRequestDTO {

    @Pattern(regexp = "[A-Z][a-z]{2,}")
    private String name;
    @Pattern(regexp = "[A-Z][a-z]{2,}(-[A-Z][a-z]{2,})?")
    private String sureName;
    private int screeningId;
    private List<TicketDTO> tickets;

    @Data
    @Builder
    public static class TicketDTO {
        private char seatRow;
        private int seatCol;
        private String ticketType;
    }
}
