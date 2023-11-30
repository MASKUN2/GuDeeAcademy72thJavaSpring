package com.maskun.projectdiary.mapper;

import com.maskun.projectdiary.vo.Memo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface HomeMapper {
    List<Memo> selectMonthMemoList(@Param("memberId")String memberId, @Param("yearMonth")String yearMonth);
}
