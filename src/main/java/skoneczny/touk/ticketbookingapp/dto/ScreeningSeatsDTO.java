package skoneczny.touk.ticketbookingapp.dto;

import lombok.Builder;
import lombok.Data;
import skoneczny.touk.ticketbookingapp.dao.Seat;

import java.util.List;

@Data
@Builder
public class ScreeningSeatsDTO {
    int screeningRoomNumber;
    List<Seat> availableSeats;
}
