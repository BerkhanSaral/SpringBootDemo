package com.tpe.domain;

import com.tpe.domain.enums.RoleType;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    private Integer id;

    @Enumerated(EnumType.STRING)//enum sabitlerimin db de string olarak kaydedilmesini saglar
    @Column(nullable = false)
    private RoleType type;//ROLE_STUDENT,ROLE_ADMIN
}
