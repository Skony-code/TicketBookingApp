package skoneczny.touk.ticketbookingapp.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import skoneczny.touk.ticketbookingapp.dao.Ticket;
import skoneczny.touk.ticketbookingapp.dto.ScreeningDTO;
import skoneczny.touk.ticketbookingapp.dto.ScreeningSeatsDTO;
import skoneczny.touk.ticketbookingapp.repositories.ScreeningRepository;
import skoneczny.touk.ticketbookingapp.repositories.TicketRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ScreeningService {
    @Autowired
    ScreeningRepository screeningRepository;
    @Autowired
    TicketRepository ticketRepository;
    public List<ScreeningDTO> findScreenings(LocalDateTime from, LocalDateTime to) {
        var screenings = screeningRepository.findAllByStartTimeAfterAndStartTimeBeforeOrderByStartTimeAscMovieNameAsc(from,to);
        var screeningsDto = screenings.stream()
                .map(screening -> ScreeningDTO.builder()
                        .id(screening.getId())
                        .starTime(screening.getStartTime())
                        .title(screening.getMovie().getName())
                        .build())
                .toList();
        return screeningsDto;
    }

    public ScreeningSeatsDTO findScreeningWithDetails(int id) {
        var optionalScreening = screeningRepository.findById(id);
        if(optionalScreening.isPresent()) {
            var screening = optionalScreening.get();
            var seats = screening.getRoom().getSeats();
            var reservedSeats = ticketRepository.findTicketsByScreeningId(screening.getId()).stream().map(Ticket::getSeat).collect(Collectors.toList());
            seats.removeAll(reservedSeats);
            return ScreeningSeatsDTO.builder()
                    .screeningRoomNumber(screening.getRoom().getId())
                    .availableSeats(seats)
                    .build();
        }
        return null;
    }
}
