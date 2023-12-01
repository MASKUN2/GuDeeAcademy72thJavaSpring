package com.maskun.projectdiary.mapper;

import com.maskun.projectdiary.vo.Memo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ScheduleMapper {
    List<Memo> selectDateScheduleList(@Param("memberId")String memberId, @Param("yearMonthDate")String yearMonthDate);

    int updateScheduleMemo(@Param("memoNo") int memoNo, @Param("memo") String memo);

    int deleteScheduleMemo(@Param("memoNo") int memoNo);

    int insertScheduleMemo(@Param("memberId") String memberId, @Param("yearMonthDate") String yearMonthDate, @Param("memo")String memo);
}
