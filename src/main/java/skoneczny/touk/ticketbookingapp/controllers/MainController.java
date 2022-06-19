package skoneczny.touk.ticketbookingapp.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import skoneczny.touk.ticketbookingapp.dto.ReservationResponseDTO;
import skoneczny.touk.ticketbookingapp.dto.ScreeningDTO;
import skoneczny.touk.ticketbookingapp.dto.ScreeningSeatsDTO;
import skoneczny.touk.ticketbookingapp.dao.Reservation;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api")
public class MainController {
    @GetMapping("/screenings")
    public ResponseEntity<List<ScreeningDTO>> getScreenings(@RequestParam("from")LocalDate from, @RequestParam("to")LocalDate to) {
        return null;
    }
    @GetMapping("/screening")
    public ResponseEntity<ScreeningSeatsDTO> getScreening(@RequestParam("id")int id) {
        return null;
    }
    @PutMapping("/reserve")
    public ResponseEntity<ReservationResponseDTO> reserveTickets(@RequestParam("reservation") Reservation reservation) {
        return null;
    }
}
