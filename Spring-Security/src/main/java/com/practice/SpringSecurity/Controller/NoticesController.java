package com.practice.SpringSecurity.Controller;

import java.util.List;
import java.util.concurrent.TimeUnit;

import com.practice.SpringSecurity.Entity.Notice;
import com.practice.SpringSecurity.Repository.NoticeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.CacheControl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class NoticesController {

    @Autowired
    private NoticeRepository noticeRepository;

    @GetMapping("/test-notices")
    public String testAccount(){
        return "Welcome to notices controller.";
    }

    @GetMapping("/notices")
    public ResponseEntity<List<Notice>> getNotices() {
        List<Notice> notices = noticeRepository.findAllActiveNotices();
        if (notices != null ) {
            return ResponseEntity.ok()
                    .cacheControl(CacheControl.maxAge(60, TimeUnit.SECONDS))
                    .body(notices);
        }else {
            return null;
        }
    }

}