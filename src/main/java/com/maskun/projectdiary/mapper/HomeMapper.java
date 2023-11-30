package com.maskun.projectdiary.mapper;

import com.maskun.projectdiary.vo.Memo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface HomeMapper {
    List<Memo> selectMonthMemoList();
}
