package skoneczny.touk.ticketbookingapp.repositories;

import org.springframework.data.repository.CrudRepository;
import skoneczny.touk.ticketbookingapp.dao.Screening;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

public interface ScreeningRepository extends CrudRepository<Screening,Integer> {
    List<Screening> findAllByStartTimeAfterAndStartTimeBeforeOrderByStartTimeAscMovieNameAsc(LocalDateTime from, LocalDateTime to);
}
