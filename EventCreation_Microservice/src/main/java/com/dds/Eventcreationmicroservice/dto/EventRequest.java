package com.dds.Eventcreationmicroservice.dto;

import java.math.BigDecimal;
import java.time.LocalTime;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor

public class EventRequest {
    //we are going to have similar fields that we had for event

    private String name;
    private String description;
    private int meetid;
    // private BigDecimal price;
    private LocalTime meet_start_time;
    private LocalTime meet_end_time;
    private int duration;
    private List<String> participants_email;
}
