package com.example.shoppingproject.beans;

import com.example.shoppingproject.enums.ClientType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Size;
import java.util.UUID;


@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Users {


    @Id
    private int id;

    @Column(unique = true, nullable = false)
    @Size(min = 4, max = 16)
    private String userName;

    @Column(nullable = false)
    @Size(min = 4, max = 16)
    private String password;

    @Enumerated(EnumType.STRING)
    private ClientType clientType;


}
