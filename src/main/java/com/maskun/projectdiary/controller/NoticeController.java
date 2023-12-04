package com.maskun.projectdiary.controller;

import com.maskun.projectdiary.service.NoticeService;
import com.maskun.projectdiary.vo.Notice;
import com.maskun.projectdiary.vo.NoticeComment;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.xml.stream.events.Comment;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Controller
@RequestMapping("/notice")
public class NoticeController {
    private final NoticeService service;

    @GetMapping
    public String getNoticeList(@RequestParam(defaultValue = "1") Integer page, Model model){
        if(page < 1){
            return "redirect:/notice?page=1";
        }
        List<Notice> noticeList = service.getNoticeList(page);
        model.addAttribute("noticeList", noticeList);
        return "notice/noticeList";
    }
    @GetMapping("/add")
    public String getNoticeAddForm(HttpSession session){
        return "notice/noticeAddForm";
    }

    @PostMapping
    public String addNotice(HttpSession session, Notice notice) {
        boolean result = service.addNotice(session, notice);
        if(result == true){
            return "redirect:/notice";
        }else{
            return "notice/noticeAddForm";
        }
    }

    @PostMapping("/comment")
    public String addComment(HttpSession session, NoticeComment comment){
        boolean result = service.addNoticeComment(session, comment);
            return "redirect:/notice/"+comment.getNoticeNo();
    }
    @PutMapping("/comment")
    public ResponseEntity editComment(@RequestBody NoticeComment comment){
        log.debug(comment.toString());
        return new ResponseEntity(HttpStatus.OK);
    }



    @GetMapping("/{noticeNo}")
    public String getNotice(@PathVariable Integer noticeNo, Model model){
        Notice notice = service.getNotice(noticeNo);
        if(notice == null){
            return "redirect:/notice?page=1";
        }
        model.addAttribute("notice", notice);
        return "notice/notice";
    }
}
