package ru.home.models;

import io.swagger.annotations.ApiModelProperty;
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
    @ApiModelProperty(notes = "The database generated storage ID")
    @Column(name = "id")
    private int id;

    @Getter
    @Setter
    @ManyToOne
    @ApiModelProperty(notes = "The toy in that storage")
    @JoinColumn(name = "toy_id")
    private Toy toy;

    @Getter
    @Setter
    @ApiModelProperty(notes = "The count of this toy in that storage")
    @Column(name="count")
    private int count;
}
