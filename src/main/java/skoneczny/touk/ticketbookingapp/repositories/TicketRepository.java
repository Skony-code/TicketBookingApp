package skoneczny.touk.ticketbookingapp.repositories;

import org.springframework.data.repository.CrudRepository;
import skoneczny.touk.ticketbookingapp.dao.Screening;
import skoneczny.touk.ticketbookingapp.dao.Ticket;

import java.util.Set;

public interface TicketRepository extends CrudRepository<Ticket,Integer> {
    Set<Ticket> findTicketsByScreeningId(int id);
}
