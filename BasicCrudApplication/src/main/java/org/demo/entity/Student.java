package org.demo.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

@Entity
@Table(name = "student")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String name;

    @Min(1)
    @Max(35)
    private int age;

    @Email
    @NotBlank
    @Column(unique = true)
    private String email;

    @Min(1)
    @Column(unique = true)
    private int rollNo;

    @NotBlank
    private String subject;

}