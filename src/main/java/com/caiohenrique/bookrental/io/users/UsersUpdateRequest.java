package com.caiohenrique.bookrental.io.users;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Min;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UsersUpdateRequest {

    private Integer id;

    private String name;

    private String address;

    private String city;

    private String email;

}
