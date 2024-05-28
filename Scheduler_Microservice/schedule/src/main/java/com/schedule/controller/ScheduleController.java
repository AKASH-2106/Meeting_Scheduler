package com.schedule.controller;

import com.schedule.entity.scheduler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;


@RestController
@RequestMapping("/schedule")
public class ScheduleController {
    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/{userId}/{mid}")
    public scheduler getInterval(@PathVariable("userId") String userId, @PathVariable("mid") String mid) throws IOException {
        // Get from Event microservice
        List I =  this.restTemplate.getForObject("http://localhost:9001/interval/" + mid, List.class);

        scheduler s = new scheduler(mid);
        assert I != null;
        int n = I.size();
        List<Integer> z = new ArrayList<Integer>(((LinkedHashMap)I.get(0)).values());
        int d = z.get(4);

        for (Object o : I) {
            List<String> x = new ArrayList<String>(((LinkedHashMap) o).values());

            LocalTime start = LocalTime.parse(x.get(2));
            LocalTime end = LocalTime.parse(x.get(3));

            LocalTime a = start, b = a.plusMinutes(d);
            if (!end.isBefore(b)) {
                for (; !end.isBefore(b); a = a.plusMinutes(5), b = a.plusMinutes(d)) {
                    int count = 0;

                    for (Object p : I) {
                        List<String> y = new ArrayList<String>(((LinkedHashMap) p).values());

                        LocalTime a1 = LocalTime.parse(y.get(2));
                        LocalTime b1 = LocalTime.parse(y.get(3));

                        if ((!a.isBefore(a1)) && (!b1.isBefore(b)))
                            count++;
                    }

                    if (s.getConvenience() < ((double) count / n)) {
                        s.setStart_time(a);
                        s.setEnd_time(b);
                        s.setConvenience((double) count / n);
                    }
                }
            }
//
//            if(i == 0)
//            {
//                s.setFree_start_time(start);
//                s.setFree_end_time(end);
//            }
//            else {
//                LocalTime a = s.getFree_start_time();
//                LocalTime b = s.getFree_end_time();
//
//                if(start.compareTo(a) > 0)
//                    s.setFree_start_time(start);
//
//                if(b.compareTo(end) > 0)
//                    s.setFree_end_time(end);
//            }
        }

        HashMap<String, String> map = new HashMap<>();
        map.put("mid", s.getMid());
        map.put("free_start_time", String.valueOf(s.getStart_time()));
        map.put("free_end_time", String.valueOf(s.getEnd_time()));
        map.put("email", "");

        List<HashMap<String, String>> request = new ArrayList<>();
        for (Object o : I) {
            List<String> x = new ArrayList<String>(((LinkedHashMap) o).values());

            String email = x.get(0);
            map.put("email", email);
            request.add((HashMap<String, String>) map.clone());
        }

        // Post to Notification service
        this.restTemplate.postForObject("", request, List.class);
        return s;
    }
}
