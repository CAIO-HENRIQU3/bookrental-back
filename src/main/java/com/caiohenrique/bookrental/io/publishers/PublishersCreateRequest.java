package com.caiohenrique.bookrental.io.publishers;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PublishersCreateRequest {

    private String name;

    private String city;
}