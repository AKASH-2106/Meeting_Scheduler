package com.notifs.notification.service;

import com.notifs.notification.dto.FreetimeRequest;
import com.notifs.notification.model.Free;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class NotifyService {
    @Autowired
    private EmailService emailService;

    public void emailnotify(FreetimeRequest freetimeRequest) {
        Free free = Free.builder()
                .emailid(freetimeRequest.getEmailid())
                .meetid(freetimeRequest.getMeetid())
                .free_start_time(freetimeRequest.getFree_start_time())
                .free_end_time(freetimeRequest.getFree_end_time())
                .duration(freetimeRequest.getDuration())
                .build();
        System.out.println(freetimeRequest);
        System.out.println(freetimeRequest.getClass());
        for (String email : free.getParticipants_email()) {
            String inviteMessage = generateInviteMessage(free);
            emailService.sendEmail(email, "Meeting Invitation", inviteMessage);
        }
    }

    private String generateInviteMessage(Free free) {
        // Generate invitation message based on the event details
        String message = "Meeting is scheduled.\n";
        message += "Meetid: " + free.getMeetid() +"\n";
        message += "Start Time: " + free.getFree_start_time() + "\n";
        message += "End Time: " + free.getFree_end_time() + "\n";
        message+= "Duration: " + free.getDuration() + "\n";
        System.out.println(message);
        return message;
    }
}
