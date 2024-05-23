package com.gawron.market.model.Market;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@Entity
@Table(name="history")
public class History {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private String sellerServer;

    @Column(nullable = false)
    private int sellerID;

    @Column(nullable = false)
    private String buyerServer;

    @Column(nullable = false)
    private int buyerID;

    @Column(nullable = false, columnDefinition = "uniqueidentifier")
    private String uuid;

    @Column(nullable = false)
    private String itemName;

    @Column(nullable = false, columnDefinition = "decimal", precision = 10, scale = 2)
    private BigDecimal prize;

    public String getItemName(){
        return itemName;
    }

    public BigDecimal getPrize(){
        return prize;
    }

    public History(){}

    public History(String sellerServer, int sellerID, String buyerServer, int buyerID, String uuid, String itemName, BigDecimal prize){
        this.sellerServer = sellerServer;
        this.sellerID = sellerID;
        this.buyerServer = buyerServer;
        this.buyerID = buyerID;
        this.uuid = uuid;
        this.itemName = itemName;
        this.prize = prize;
    }
}
