package com.maskun.projectdiary.mapper;

import com.maskun.projectdiary.domain.entity.Memo;
import com.maskun.projectdiary.domain.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.time.LocalDate;
import java.util.List;

@Mapper
public interface MemoMapper {
    List<Memo> selectDateMemoList(@Param("date") LocalDate date, @Param("userId")String userId);

    int updateMemo(@Param("memoNo") int memoNo, @Param("memoContent") String memoContent);

    int deleteMemo(@Param("memoNo") int memoNo);

    int insertMemo(@Param("user")User user, @Param("memo")Memo memo);
}
