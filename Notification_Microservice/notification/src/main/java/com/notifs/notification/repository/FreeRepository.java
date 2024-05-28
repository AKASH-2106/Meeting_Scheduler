package com.notifs.notification.repository;

import com.notifs.notification.model.Free;

import java.util.List;


public interface FreeRepository<Free, String> {
    List<Free> findByMeetid(String meetid);
}