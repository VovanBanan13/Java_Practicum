package ru.home.models;

import io.swagger.annotations.ApiModelProperty;
import java.util.ArrayList;
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
    @ApiModelProperty(notes = "The database generated toy ID")
    @Column(name = "id")
    private int id;

    @Getter
    @Setter
    @ApiModelProperty(notes = "The toy name")
    @Column(name="name")
    private String name;

    @Getter
    @Setter
    @ApiModelProperty(notes = "The toy price")
    @Column(name="price")
    private double price;

    @Getter
    @Setter
    @ManyToOne
    @ApiModelProperty(notes = "The category in that toy")
    @JoinColumn(name = "category_id")
    private Category category;

    @ApiModelProperty(notes = "List of storages in this toy")
    @OneToMany(mappedBy = "toy", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Storage> storages = new ArrayList<>();

    @ApiModelProperty(notes = "List of shop lists in this toy")
    @OneToMany(mappedBy = "toy", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<ShopList> shopLists = new ArrayList<>();
}
