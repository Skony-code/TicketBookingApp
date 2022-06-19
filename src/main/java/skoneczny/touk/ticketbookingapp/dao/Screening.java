package skoneczny.touk.ticketbookingapp.dao;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Screening {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private LocalDateTime startTime;
    @ManyToOne
    private Room room;
    @ManyToOne
    private Movie movie;
    @OneToMany(mappedBy = "screening")
    private Set<Ticket> tickets;
}
