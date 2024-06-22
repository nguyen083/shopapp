package com.example.shopapp.models;

import jakarta.persistence.*;
import lombok.*;



@Entity
@Table(name = "categories")
@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name", nullable =false, length = 100)
    private String name;
}
