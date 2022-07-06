package skoneczny.touk.ticketbookingapp.repositories;

import org.springframework.data.repository.CrudRepository;
import skoneczny.touk.ticketbookingapp.dao.Seat;

public interface SeatRepository extends CrudRepository<Seat,Integer> {
    Seat findBySeatRowAndSeatColumn(char row,int column);
}
