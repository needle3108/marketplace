package com.gawron.market.model.NA;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@Entity
@Table(name="player")
public class Player {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false, unique = true)
    private String nickname;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false, columnDefinition = "decimal", precision = 10, scale = 2)
    private BigDecimal saldo;

    public Player(){}

    public Player(String nickname, String password){
        this.nickname = nickname;
        this.password = password;
        this.saldo = BigDecimal.valueOf(500.00);
    }
}
