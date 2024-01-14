package com.maskun.projectdiary.domain.memo;

import com.maskun.projectdiary.domain.memo.Memo;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
@Repository
public interface MemoRepository extends CrudRepository<Memo,Long> {
    List<Memo> findByMemoDate(LocalDate date);

    Memo findMemoByMemoNo(Long memoNo);
}
