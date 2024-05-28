package com.dds.Eventcreationmicroservice.dto;

import java.time.LocalTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor

public class FreetimeResponse {
    private String emailid;
    private String meetid;
    // private BigDecimal price;
    private LocalTime free_start_time;
    private LocalTime free_end_time;
    private int duration;
}
