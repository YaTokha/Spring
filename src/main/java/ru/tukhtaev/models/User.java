package ru.tukhtaev.models;

import lombok.*;

@Getter
@Setter
@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor

public class User {
    private Long id;
    private  String firstName;
    private  String lastName;
    private  String email;
    private  String hashPassword;
}
