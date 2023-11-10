package com.caiohenrique.bookrental.io.rents;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RentTotalByStatusResponseRequest {
    private Long total_pending;
    private Long total_late;
    private Long total_returned_on_time;
    private Long total_returned_delay;
}
