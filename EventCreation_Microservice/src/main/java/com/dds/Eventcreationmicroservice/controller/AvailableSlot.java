package com.dds.Eventcreationmicroservice.controller;
import org.springframework.web.bind.annotation.RestController;
import com.dds.Eventcreationmicroservice.dto.FreetimeRequest;
import com.dds.Eventcreationmicroservice.dto.FreetimeResponse;
import com.dds.Eventcreationmicroservice.service.FreeTime;

import lombok.RequiredArgsConstructor;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping("/api/freetime")
@RequiredArgsConstructor
public class AvailableSlot {
    
    private final FreeTime freeTime;
    //let us create the method

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    //we are going to send a post request for this method so add post annotation

    //i am going to receive the event request
    public void freeTime(@RequestBody FreetimeRequest freetimeRequest){
        //we are getting eventRequest as the requestbody
        freeTime.createFreeTime(freetimeRequest);
    }

    //i am adding another mapping that does the following for me
    @GetMapping("/{meetid}")
    @ResponseStatus(HttpStatus.OK)
    public List<FreetimeResponse> getAllTimes(@PathVariable String meetid) {
        return freeTime.getAllTimes(meetid);
    }
    
}
