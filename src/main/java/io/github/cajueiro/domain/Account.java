package io.github.cajueiro.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data

@Entity
@Table(name = "accounts", schema = "cajueiro")
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "food_balance", nullable = false)
    private Double foodBalance;

    @Column(name = "meal_balance", nullable = false)
    private Double mealBalance;

    @Column(name = "cash_balance", nullable = false)
    private Double cashBalance;

}
