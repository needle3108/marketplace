package com.gawron.market.model.EUNE;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name="itemOnServer")
public class ItemOnServer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private int itemOwner;

    @Column(nullable = false, columnDefinition = "uniqueidentifier")
    private String uuid;

    public int getId(){
        return id;
    }

    public int getItemOwner(){
        return itemOwner;
    }

    public String getName(){
        return name;
    }

    public String getUuid(){
        return uuid;
    }

    public ItemOnServer(){}

    public ItemOnServer (String name, int itemOwner, String uuid){
        this.name = name;
        this.itemOwner = itemOwner;
        this.uuid = uuid;
    }
}
