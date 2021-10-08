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
@Table(name="category")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    @Setter
    @ApiModelProperty(notes = "The database generated category ID")
    @Column(name = "id")
    private int id;

    @Getter
    @Setter
    @ApiModelProperty(notes = "The category name")
    @Column(name="name")
    private String name;

    @ApiModelProperty(notes = "List of toys in this category")
    @OneToMany(mappedBy = "category", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Toy> toys = new ArrayList<>();
}
