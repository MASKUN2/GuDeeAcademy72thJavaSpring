package com.maskun.projectdiary.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@Slf4j
@RequiredArgsConstructor
@Controller
public class NoticeController {
    private final noticeService service;

    @GetMapping("/notice")
    public String getNoticeList(@RequestParam(defaultValue = "1") Integer page){
        final int NOTICES_PER_PAGE = 6;
        int from = (page-1)*NOTICES_PER_PAGE;
        service.getNoticeList(from, NOTICES_PER_PAGE);
        return "noticeList";
    }

    @GetMapping("/notice/{noticeNo}")
    public String getNotice(@PathVariable Integer noticeNo){
        service.getNotice(noticeNo);
        return "notice";
    }
}
