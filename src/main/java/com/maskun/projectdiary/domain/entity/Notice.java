package com.maskun.projectdiary.domain.entity;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class Notice {
    private int noticeNo;
    private String author;
    private String noticeTitle;
    private String noticeContent;
    private LocalDateTime createdate;
    private List<NoticeComment> noticeCommentList;

}
