package com.caiohenrique.bookrental.io.users;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UsersCreateRequest {

    private String name;

    private String address;

    private String city;

    private String email;

}
