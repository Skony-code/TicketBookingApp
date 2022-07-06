package skoneczny.touk.ticketbookingapp.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import skoneczny.touk.ticketbookingapp.dao.Reservation;
import skoneczny.touk.ticketbookingapp.dao.Screening;
import skoneczny.touk.ticketbookingapp.dao.Ticket;
import skoneczny.touk.ticketbookingapp.dto.ReservationRequestDTO;
import skoneczny.touk.ticketbookingapp.dto.ReservationResponseDTO;
import skoneczny.touk.ticketbookingapp.exceptions.ReservationException;
import skoneczny.touk.ticketbookingapp.repositories.ReservationRepository;
import skoneczny.touk.ticketbookingapp.repositories.ScreeningRepository;
import skoneczny.touk.ticketbookingapp.repositories.SeatRepository;
import skoneczny.touk.ticketbookingapp.repositories.TicketRepository;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class ReservationService {
    private final Map<String, Float> ticketPrice = Map.of("adult",25f,"student",18f,"child",12.50f);
    @Autowired
    private ScreeningRepository screeningRepository;
    @Autowired
    private ReservationRepository reservationRepository;
    @Autowired
    private TicketRepository ticketRepository;
    @Autowired
    private SeatRepository seatRepository;

    public ReservationResponseDTO makeReservation(ReservationRequestDTO request) throws ReservationException {
        var screening = screeningRepository.findById(request.getScreeningId());

        if(screening.isEmpty())
            throw new ReservationException("Screening with id: "+request.getScreeningId()+" doesn't exist");

        if(screening.get().getStartTime().isAfter(LocalDateTime.now().minusMinutes(15)))
            throw new ReservationException("Reservation can be made at least 15 minutes before screening start");

        var tickets = request.getTickets().stream()
                .map(ticketDTO -> convertTicketDtoToTicketDao(screening.get(),ticketDTO))
                .toList();

        if (tickets.size()==0)
            throw new ReservationException("You must choose at least one seat in order to make a reservation");

       if(!areTicketsValid(screening.get(),tickets))
            throw new ReservationException("Chosen seat is already occupied or leaves one seat in between");
        ticketRepository.saveAll(tickets);

        reservationRepository.save(Reservation.builder()
                .tickets(tickets)
                .name(request.getName())
                .surname(request.getSureName())
                .build());

        return createSummary(screening.get(),tickets);
    }
    private ReservationResponseDTO createSummary(Screening screening,List<Ticket> tickets) {
        float sum = 0;
        for (var ticket: tickets)
            sum = sum + ticket.getPrice();

        return ReservationResponseDTO.builder()
                .totalPrice(sum)
                .expirationDate(screening.getStartTime().minusMinutes(15))
                .build();
    }

    private Ticket convertTicketDtoToTicketDao(Screening screening, ReservationRequestDTO.TicketDTO ticketDTO) {
        return Ticket.builder()
                .screening(screening)
                .price(ticketPrice.get(ticketDTO.getTicketType()))
                .seat(seatRepository.findBySeatRowAndSeatColumn(ticketDTO.getSeatRow(), ticketDTO.getSeatCol()))
                .build();
    }

    private boolean areTicketsValid(Screening screening,List<Ticket> proposedTickets) {
        var availableSeats = new ArrayList<>(screening.getRoom().getSeats());
        var occupiedSeats = screening.getTickets().stream().map(Ticket::getSeat).collect(Collectors.toList());
        availableSeats.removeAll(occupiedSeats);
        //check for collisions
        for(Ticket ticket: proposedTickets) {
             if(!availableSeats.contains(ticket.getSeat()))
                 return false;
             //checking for collisions inside proposed tickets
            if(proposedTickets.stream().filter(ticket2 -> ticket.getSeat().equals(ticket2.getSeat())).toList().size()>1)
                return false;
        }
        occupiedSeats.addAll(proposedTickets.stream().map(Ticket::getSeat).toList());

        Map<Character,List<Integer>> seats = new HashMap<>();
        occupiedSeats.forEach(seat -> {
            if(seats.containsKey(seat.getSeatRow()))
                seats.get(seat.getSeatRow()).add(seat.getSeatColumn());
            else
                seats.put(seat.getSeatRow(),new ArrayList<>());
        });
        for(var entry :seats.entrySet()) {
            if(!validateRow(entry.getValue()))
                return false;
        }
        return true;
    }
    private boolean validateRow(List<Integer> seats) {
        Collections.sort(seats);
        for(int i=0;i<seats.size()-1;i++) {
            if(seats.get(i+1)-seats.get(i)==1)
                return false;
        }
        return true;
    }
}
