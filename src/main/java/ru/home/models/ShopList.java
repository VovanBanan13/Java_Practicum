package ru.home.models;

import io.swagger.annotations.ApiModelProperty;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@NoArgsConstructor
@ToString
@Table(name="shop_list")
public class ShopList {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    @Setter
    @ApiModelProperty(notes = "The database generated shop list ID")
    @Column(name = "id")
    private int id;

    @Getter
    @Setter
    @ManyToOne
    @ApiModelProperty(notes = "The order in that shop list")
    @JoinColumn(name = "order_id")
    private Order order;

    @Getter
    @Setter
    @ManyToOne
    @ApiModelProperty(notes = "The toy in that shop list")
    @JoinColumn(name = "toy_id")
    private Toy toy;

    @Getter
    @Setter
    @ApiModelProperty(notes = "The count of this toy in that shop list")
    @Column(name="count")
    private int count;

    public double getAmount() {
        return this.toy.getPrice() * this.count;
    }
}
