package com.maskun.projectdiary.mapper;

import com.maskun.projectdiary.vo.Notice;
import com.maskun.projectdiary.vo.NoticeComment;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface NoticeMapper {

    List<Notice> selectNoticeList(@Param("from") int from, @Param("numPerPage") int numPerPage);

    Notice selectNotice(@Param("noticeNo") int noticeNo);

    int insertNotice(@Param("memberId") String memberId,@Param("notice") Notice notice);

    List<NoticeComment> selectNoticeCommentList(@Param("noticeNo") int noticeNo);

    int insertNoticeComment(@Param("memberId") String memberId, @Param("comment") NoticeComment comment);

    int updateNoticeComment(@Param("memberId") String memberId, @Param("comment") NoticeComment comment);

    int deleteNoticeComment(@Param("memberId") String memberId, @Param("comment") NoticeComment comment);
}
