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

public class FreetimeResponse {
    private String emailid;
    private String meetid;
    // private BigDecimal price;
    private LocalTime free_start_time;
    private LocalTime free_end_time;
    private int duration;
}
