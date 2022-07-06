package skoneczny.touk.ticketbookingapp.dao;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @ManyToOne
    private Screening screening;
    @ManyToOne(fetch = FetchType.EAGER,cascade = CascadeType.MERGE)
    private Seat seat;
    private Float price;
}
