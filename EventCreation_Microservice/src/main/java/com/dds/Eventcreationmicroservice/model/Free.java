package com.dds.Eventcreationmicroservice.model;

import java.time.LocalTime;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Document(value = "invite")
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
}
