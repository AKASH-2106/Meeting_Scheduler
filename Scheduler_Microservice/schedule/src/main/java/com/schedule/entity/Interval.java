package com.schedule.entity;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Dictionary;
import java.util.Hashtable;
import java.util.List;

@Setter
@Getter
@JsonDeserialize
public class Interval {
    private String email;
    private String mid;
    private LocalTime free_start_time;
    private LocalTime free_end_time;
    private int duration;

    public Interval(String email, String mid, LocalTime free_start_time, LocalTime free_end_time, int duration) {
        this.email = email;
        this.mid = mid;
        this.free_start_time = free_start_time;
        this.free_end_time = free_end_time;
        this.duration = duration;
    }

}
