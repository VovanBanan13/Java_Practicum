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
@Table(name="toy")
public class Toy {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    @Setter
    @Column(name = "id")
    private int id;

    @Getter
    @Setter
    @Column(name="name")
    private String name;

    @Getter
    @Setter
    @Column(name="price")
    private double price;

    @Getter
    @Setter
    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category = new Category();

    @OneToMany(mappedBy = "toy", fetch = FetchType.LAZY)
    private List<Storage> storages;

    @OneToMany(mappedBy = "toy", fetch = FetchType.LAZY)
    private List<ShopList> shopLists;
}