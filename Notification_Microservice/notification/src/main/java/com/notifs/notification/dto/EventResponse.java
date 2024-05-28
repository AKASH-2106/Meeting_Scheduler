package com.notifs.notification.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalTime;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor

public class EventResponse {
    
    private String id;
    private String name;
    private String description;
    private int meetid;
    // private BigDecimal price;
    private LocalTime meet_start_time;
    private LocalTime meet_end_time;
    //this duration is in minutes
    private int duration;
    private List<String> participants_email;
}
