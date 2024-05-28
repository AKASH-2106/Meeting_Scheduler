package com.dds.Eventcreationmicroservice.model;

import java.math.BigDecimal;
import java.time.LocalTime;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document(value = "event")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class Event {
    @Id
    private String id;
    private String name;
    private String description;
    private int meetid;
    private LocalTime meet_start_time;
    private LocalTime meet_end_time;
    //this duration is in minutes
    private int duration;
    private List<String> participants_email;
}
