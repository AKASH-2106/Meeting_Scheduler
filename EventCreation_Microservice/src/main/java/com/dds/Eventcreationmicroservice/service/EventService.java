package com.dds.Eventcreationmicroservice.service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import org.springframework.stereotype.Service;

import com.dds.Eventcreationmicroservice.dto.EventRequest;
import com.dds.Eventcreationmicroservice.dto.EventResponse;
import com.dds.Eventcreationmicroservice.model.Event;
import com.dds.Eventcreationmicroservice.repository.EventRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class EventService {
    
    @Autowired
    private EmailService emailService;
    //inject the event repositoryclass
    private final EventRepository eventRepository;

    

    public void createEvent(EventRequest eventRequest){
        //map the eventmodel
        Event event= Event.builder()
            .name(eventRequest.getName())
            .description(eventRequest.getDescription())
            .meetid(eventRequest.getMeetid())
            .meet_start_time(eventRequest.getMeet_start_time())
            .meet_end_time(eventRequest.getMeet_end_time())
            .duration(eventRequest.getDuration())
            .participants_email(eventRequest.getParticipants_email())
            .build();

        //it will create object of type eventwith all the requested details
        //now we need to save this into the database

        eventRepository.save(event);

        //to add the logs we are using slf4j
        //this will get the meetid of the event which i have created 
        log.info("Event {} is saved", event.getMeetid());

    }

    //after the event is getting created , create a function that calls that sends the invite to the email ids mentioned
    //and it wont be returning any values

    public void createInvite(EventRequest eventRequest){
        //now do the following things
        Event event= Event.builder()
            .name(eventRequest.getName())
            .description(eventRequest.getDescription())
            .meetid(eventRequest.getMeetid())
            .meet_start_time(eventRequest.getMeet_start_time())
            .meet_end_time(eventRequest.getMeet_end_time())
            .duration(eventRequest.getDuration())
            .participants_email(eventRequest.getParticipants_email())
            .build();

            //it has created object of the event

            // log.info("Event invite sent to {}", event.getParticipants_email());

            
            //iterate over all the email ids
            for(String email: event.getParticipants_email()){
                String inviteMessage = generateInviteMessage(event);
                emailService.sendEmail(email, "Meeting Invitation", inviteMessage);

                // log.info("Email id of the participants {}", email);
            }
    }

    private String generateInviteMessage(Event event) {
        // Generate invitation message based on the event details
        String message = "You are invited to a meeting.\n";
        message += "Description: " + event.getDescription() + "\n";
        message += "Meetid: " + event.getMeetid() +"\n";
        message += "Start Time: " + event.getMeet_start_time() + "\n";
        message += "End Time: " + event.getMeet_end_time() + "\n";
        message+= "Duration: " + event.getDuration() + "\n";
        return message;
    }

    public List<EventResponse> getAllEvents(){
        List<Event> events= eventRepository.findAll();
        //now map these events class to event response class

        return events.stream().map(this::mapToEventResponse).toList();
    }

    private EventResponse mapToEventResponse(Event event){
        return EventResponse.builder()
                .id(event.getId())
                .name(event.getName())
                .description(event.getDescription())
                .meetid(event.getMeetid())
                .meet_start_time(event.getMeet_start_time())
                .meet_end_time(event.getMeet_end_time())
                .duration(event.getDuration())
                .participants_email(event.getParticipants_email())
                .build();
    }
}

// private LocalTime meet_start_time;
//     private LocalTime meet_end_time;
