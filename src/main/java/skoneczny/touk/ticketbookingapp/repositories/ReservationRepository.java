package skoneczny.touk.ticketbookingapp.repositories;

import org.springframework.data.repository.CrudRepository;
import skoneczny.touk.ticketbookingapp.dao.Reservation;

public interface ReservationRepository extends CrudRepository<Reservation,Integer> {

}
