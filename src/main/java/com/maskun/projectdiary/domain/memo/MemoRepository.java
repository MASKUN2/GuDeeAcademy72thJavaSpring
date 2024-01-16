package com.maskun.projectdiary.domain.memo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
@Repository
public interface MemoRepository extends CrudRepository<Memo,Long> {
    List<Memo> findByMemoDate(LocalDate date);

    List<Memo> findByMemoDateBetween(LocalDate startDate, LocalDate endDate);

    Memo findMemoByMemoNo(Long memoNo);
}
