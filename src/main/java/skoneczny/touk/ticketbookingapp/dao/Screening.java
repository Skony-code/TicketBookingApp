package skoneczny.touk.ticketbookingapp.dao;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Screening {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private LocalDateTime startTime;
    @ManyToOne
    private Room room;
    @ManyToOne
    private Movie movie;
    @OneToMany(mappedBy = "screening",fetch = FetchType.EAGER,cascade = CascadeType.MERGE)
    private List<Ticket> tickets;
}
