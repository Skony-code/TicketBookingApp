package skoneczny.touk.ticketbookingapp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import skoneczny.touk.ticketbookingapp.dto.ReservationRequestDTO;
import skoneczny.touk.ticketbookingapp.dto.ScreeningDTO;
import skoneczny.touk.ticketbookingapp.exceptions.ReservationException;
import skoneczny.touk.ticketbookingapp.services.ReservationService;
import skoneczny.touk.ticketbookingapp.services.ScreeningService;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api")
public class MainController {
    @Autowired
    ScreeningService screeningService;
    @Autowired
    ReservationService reservationService;
    @GetMapping("/screenings")
    public ResponseEntity<List<ScreeningDTO>> getScreenings(
            @RequestParam("from") @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm") LocalDateTime from,
            @RequestParam("to") @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm") LocalDateTime to) {
        return new ResponseEntity<>(screeningService.findScreenings(from,to),HttpStatus.OK);
    }
    @GetMapping("/screening")
    public ResponseEntity<?> getScreening(@RequestParam("id")int id) {
        var screening =  screeningService.findScreeningWithDetails(id);
        if(screening == null)
            return new ResponseEntity<>("Screening with id: "+id+" does not exist",HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(screening, HttpStatus.OK);
    }
    @PutMapping("/reserve")
    public ResponseEntity<?> reserveTickets(@RequestBody @Valid ReservationRequestDTO reservation) {
        try {
            return new ResponseEntity<>(reservationService.makeReservation(reservation),HttpStatus.OK);
        } catch (ReservationException e) {
            return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }
}
