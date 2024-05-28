package com.schedule.entity;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalTime;

@Setter
@Getter
public class scheduler {
    private String mid;
    private LocalTime start_time;
    private LocalTime end_time;
    private double convenience = 0.0;

    public scheduler(String mid, LocalTime free_start_time, LocalTime free_end_time, double convenience) {
        this.mid = mid;
        this.start_time = free_start_time;
        this.end_time = free_end_time;
        this.convenience = convenience;
    }

    public scheduler(String mid) {
        this.mid = mid;
    }

}
