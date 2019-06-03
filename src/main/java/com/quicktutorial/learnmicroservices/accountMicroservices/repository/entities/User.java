package com.quicktutorial.learnmicroservices.accountMicroservices.repository.entities;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name="users")
public class User {

    @Getter @Setter
    @Id
    @NotEmpty @NotBlank @NotNull
    @Column(name="ID")
    private String id;

    @Getter @Setter
    @NotEmpty @NotBlank @NotNull
    @Column(name="USERNAME")
    private String username;

    @Getter @Setter
    @NotEmpty @NotBlank @NotNull
    @Column(name="PASSWORD")
    private String password;

    @Getter @Setter
    @NotEmpty @NotBlank @NotNull
    @Column(name="PERMISSION")
    private String permission;

}
