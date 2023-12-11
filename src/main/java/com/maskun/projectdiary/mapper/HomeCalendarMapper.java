package com.maskun.projectdiary.mapper;

import com.maskun.projectdiary.vo.domain.Memo;
import com.maskun.projectdiary.vo.domain.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.time.YearMonth;
import java.util.List;

@Mapper
public interface HomeCalendarMapper {
    List<Memo> selectMonthMemoList(@Param("user") User user, @Param("yearMonth") YearMonth yearMonth);
}
