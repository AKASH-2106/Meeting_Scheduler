package com.dds.Eventcreationmicroservice.service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.dds.Eventcreationmicroservice.dto.FreetimeRequest;
import com.dds.Eventcreationmicroservice.dto.FreetimeResponse;
import com.dds.Eventcreationmicroservice.model.Event;
import com.dds.Eventcreationmicroservice.model.Free;
import com.dds.Eventcreationmicroservice.repository.FreeRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class FreeTime {
    
    //inject the event repositoryclass
    private final FreeRepository freeRepository;

    public void createFreeTime(FreetimeRequest freetimeRequest){
        Free free= Free.builder()
            .emailid(freetimeRequest.getEmailid())
            .meetid(freetimeRequest.getMeetid())
            .free_start_time(freetimeRequest.getFree_start_time())
            .free_end_time(freetimeRequest.getFree_end_time())
            .duration(freetimeRequest.getDuration())
            .build();

        freeRepository.save(free);

        log.info("FreeTime of  {} is added", free.getEmailid());
    }

    public List<FreetimeResponse> getAllTimes(String meetid){
        //retreive the information from database 

        log.info("Hellolllllll guys {}",meetid);
        List<Free> frees = freeRepository.findByMeetid(meetid);

        if(!frees.isEmpty()){
            //retreive the entity and return it
            log.info("Yes present");
            List<FreetimeResponse> freetimeResponses=frees.stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());

            return freetimeResponses;
        }
        else{
            //rturn an empty list
            log.info("Not present");
            return List.of();
        }
    }

    //utility method to map Free entity to FreetimeResponse
    private FreetimeResponse mapToResponse(Free free){
        return FreetimeResponse.builder()
            .emailid(free.getEmailid())
            .meetid(free.getMeetid())
            .free_start_time(free.getFree_start_time())
            .free_end_time(free.getFree_end_time())
            .duration(free.getDuration())
            .build();
    }
}
