package com.notifs.notification.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

import java.time.LocalTime;
import java.util.List;


@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data

public class Free {
    
    @Id
    private String emailid;
    private String meetid;
    // private BigDecimal price;
    private LocalTime free_start_time;
    private LocalTime free_end_time;
    private int duration;
    private List<String> participants_email;
}
