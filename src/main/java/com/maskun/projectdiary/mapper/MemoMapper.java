package com.maskun.projectdiary.mapper;

import com.maskun.projectdiary.vo.domain.Memo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.time.LocalDate;
import java.util.List;

@Mapper
public interface MemoMapper {
    List<Memo> selectDateMemoList(@Param("memberId")String memberId, @Param("date") LocalDate date);

    int updateMemo(@Param("memoNo") int memoNo, @Param("memoContent") String memoContent);

    int deleteMemo(@Param("memoNo") int memoNo);

    int insertMemo(@Param("memberId") String memberId, @Param("date") LocalDate date, @Param("memoContent")String memoContent);
}
