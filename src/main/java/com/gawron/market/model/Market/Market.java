package com.gawron.market.model.Market;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@Entity
@Table(name="market")
public class Market {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private int itemOwner;

    @Column(nullable = false)
    private String itemName;

    @Column(nullable = false)
    private String itemServer;

    @Column(nullable = false, columnDefinition = "uniqueidentifier")
    private String uuid;

    @Column(nullable = false, columnDefinition = "decimal", precision = 10, scale = 2)
    private BigDecimal prize;

    public int getItemOwner(){
        return itemOwner;
    }

    public String getItemName(){
        return itemName;
    }

    public String getItemServer(){
        return itemServer;
    }

    public String getUuid(){
        return uuid;
    }

    public BigDecimal getPrize(){
        return prize;
    }

    public Market(){}

    public Market(int itemOwner, String itemName, String itemServer, BigDecimal prize, String uuid){
        this.itemOwner = itemOwner;
        this.itemName = itemName;
        this.itemServer = itemServer;
        this.prize = prize;
        this.uuid = uuid;
    }

}
