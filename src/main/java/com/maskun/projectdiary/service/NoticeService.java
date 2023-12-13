package com.maskun.projectdiary.service;

import com.maskun.projectdiary.mapper.NoticeMapper;
import com.maskun.projectdiary.domain.entity.User;
import com.maskun.projectdiary.domain.entity.Notice;
import com.maskun.projectdiary.domain.entity.NoticeComment;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.List;

@Slf4j
@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
public class NoticeService {
    private final NoticeMapper noticeMapper;

    public List<Notice> getNoticeList(int page) {
        final int NUM_PER_PAGE = 6;
        int from = (page - 1) * NUM_PER_PAGE;
        return noticeMapper.selectNoticeList(from, NUM_PER_PAGE);
    }

    public Notice getNotice(int noticeNo) {

        Notice noticeFound = noticeMapper.selectNotice(noticeNo);
        List<NoticeComment> commentList = noticeMapper.selectNoticeCommentList(noticeNo);
        noticeFound.setNoticeCommentList(commentList);
        return noticeFound;
    }
    /*
    @Transactional
    public boolean addNotice(HttpSession session, Notice notice) {
        User user = (User) session.getAttribute("loginUser");
        log.debug("전달된 정보 {}", notice.toString());
        int result = noticeMapper.insertNotice(user.getMemberId(), notice);
        if (result == 1) {
            return true;
        } else {
            try {
                throw new SQLException("notice 등록이 정상적이지 않습니다 롤백합니다");
            } catch (Exception e) {
                e.printStackTrace();
                return false;
            }
        }
    }

    @Transactional
    public boolean addNoticeComment(HttpSession session, NoticeComment comment) {
        User user = (User) session.getAttribute("loginUser");
        log.debug("전달된 정보 {}", comment.toString());
        int result = noticeMapper.insertNoticeComment(user.getMemberId(), comment);
        if (result == 1) {
            return true;
        } else {
            try {
                throw new SQLException("comment 등록이 정상적이지 않습니다 롤백합니다");
            } catch (Exception e) {
                e.printStackTrace();
                return false;
            }
        }
    }

    @Transactional
    public boolean editNoticeComment(NoticeComment comment, HttpSession session) {
        User user = (User) session.getAttribute("loginUser");
        log.debug("전달된 정보 {}", comment.toString());
        int result = noticeMapper.updateNoticeComment(user.getMemberId(), comment);
        if (result == 1) {
            return true;
        } else {
            try {
                throw new SQLException("comment update 정상적이지 않습니다 롤백합니다");
            } catch (Exception e) {
                e.printStackTrace();
                return false;
            }
        }
    }

    @Transactional
    public boolean deleteNoticeComment(NoticeComment comment, HttpSession session) {
        User user = (User) session.getAttribute("loginUser");
        log.debug("전달된 정보 {}",comment.toString());
        int result = noticeMapper.deleteNoticeComment(user.getMemberId(), comment);
            if(result ==1){
            return true;
        }else{
            try {
                throw new SQLException("comment delete 정상적이지 않습니다 롤백합니다");
            } catch (Exception e) {
                e.printStackTrace();
                return false;
            }
        }
    */
}

