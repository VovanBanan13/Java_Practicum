package ru.home.models;


import io.swagger.annotations.ApiModelProperty;
import java.util.Date;
import java.sql.Time;
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
@Table(name="orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    @Setter
    @ApiModelProperty(notes = "The database generated order ID")
    @Column(name = "id")
    private int id;

    @Getter
    @Setter
    @ApiModelProperty(notes = "Date of created the order")
    @Column(name="date")
    private Date date;

    @Getter
    @Setter
    @ApiModelProperty(notes = "Time of created the order")
    @Column(name="time")
    private Date time;

    @Getter
    @Setter
    @ApiModelProperty(notes = "The order amount")
    @Column(name="amount")
    private double amount;

    @Getter
    @Setter
    @ManyToOne
    @ApiModelProperty(notes = "The user in that order")
    @JoinColumn(name = "user_id")
    private User user;

    @ApiModelProperty(notes = "List of shop lists in this order")
    @OneToMany(mappedBy = "order", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<ShopList> shopLists = new ArrayList<>();

    public int getCountTotal() {
        int count = 0;
        for (ShopList toyList : this.shopLists) {
            count += toyList.getCount();
        }
        return count;
    }

    public double getAmountTotal() {
        double total = 0;
        for (ShopList toyList : this.shopLists) {
            total += toyList.getAmount();
        }
        return total;
    }
}
