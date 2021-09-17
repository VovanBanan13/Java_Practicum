package ru.home.models;

import java.util.List;
import javax.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@NoArgsConstructor
@ToString
@Table(name="storage")
public class Storage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    @Setter
    @Column(name = "id")
    private int id;

    @Getter
    @Setter
    @ManyToOne
    @JoinColumn(name = "toy_id")
    private Toy toy = new Toy();

    @Getter
    @Setter
    @Column(name="count")
    private int count;
}
