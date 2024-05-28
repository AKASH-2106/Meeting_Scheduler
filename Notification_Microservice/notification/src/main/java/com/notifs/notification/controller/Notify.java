package com.notifs.notification.controller;

import com.notifs.notification.service.EmailService;
import org.springframework.web.bind.annotation.RestController;

import com.notifs.notification.dto.FreetimeRequest;
import com.notifs.notification.dto.FreetimeResponse;
import com.notifs.notification.service.NotifyService;

import lombok.RequiredArgsConstructor;

import java.util.HashMap;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
@RequiredArgsConstructor
public class Notify {
    private final EmailService emailService;

    @PostMapping("/api/notify")
    public void notify(@RequestBody List<HashMap<String, String>> freeTimes) {
        for(HashMap<String, String> freeTime: freeTimes){
            System.out.println(freeTime);
            String message = "Meeting is scheduled.\n";
            message += "Meetid: " + freeTime.get("meetid") +"\n";
            message += "Start Time: " + freeTime.get("free_start_time") + "\n";
            message += "End Time: " + freeTime.get("free_end_time") + "\n";
            message += "Duration: " + freeTime.get("duration") + "\n";
            emailService.sendEmail(freeTime.get("emailid"), "Meeting Scheduled", message);

        }
        //notifyService.emailnotify(freetimeRequest);
    }
}

