package skoneczny.touk.ticketbookingapp.services;

import org.springframework.stereotype.Service;
import skoneczny.touk.ticketbookingapp.dto.ScreeningDTO;
import skoneczny.touk.ticketbookingapp.dto.ScreeningSeatsDTO;

import java.time.LocalDate;
import java.util.List;

@Service
public class ScreeningService {
    public List<ScreeningDTO> findScreenings(LocalDate from, LocalDate to) {
        return null;
    }

    public List<ScreeningSeatsDTO> findScreeningWithDetails(Long id) {
        return null;
    }
}
