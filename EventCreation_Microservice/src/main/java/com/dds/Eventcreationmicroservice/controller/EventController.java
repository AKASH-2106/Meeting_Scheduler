package com.dds.Eventcreationmicroservice.controller;

import org.springframework.web.bind.annotation.RestController;

import com.dds.Eventcreationmicroservice.dto.EventRequest;
import com.dds.Eventcreationmicroservice.dto.EventResponse;
import com.dds.Eventcreationmicroservice.service.EventService;

import lombok.RequiredArgsConstructor;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping("/api/event")
@RequiredArgsConstructor

public class EventController {
    
    private final EventService eventService;
    //let us create the method

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    //we are going to send a post request for this method so add post annotation

    //i am going to receive the event request
    public void createEvent(@RequestBody EventRequest eventRequest){
        //we are getting eventRequest as the requestbody
        eventService.createEvent(eventRequest);

        //now send that request body to the invite function that creates invite and sends
        eventService.createInvite(eventRequest);

    }

    //create nother end point to retreive all the events created
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<EventResponse> getAllEvents(){
        return eventService.getAllEvents();

    }
    
}
