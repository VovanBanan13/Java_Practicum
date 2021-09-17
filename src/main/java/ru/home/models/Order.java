package ru.home.models;

import java.sql.Time;
import java.util.Date;
import java.util.List;
import javax.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@NoArgsConstructor
@ToString
@Table(name="order")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    @Setter
    @Column(name = "id")
    private int id;

    @Getter
    @Setter
    @Column(name="date")
    private Date date;

    @Getter
    @Setter
    @Column(name="time")
    private Time time;

    @Getter
    @Setter
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user = new User();

    @OneToMany(mappedBy = "order", fetch = FetchType.LAZY)
    private List<ShopList> shopLists;
}
