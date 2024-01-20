package com.maskun.projectdiary.domain.memo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Repository
public interface MemoRepository extends CrudRepository<Memo,Long> {
    List<Memo> findByMemoDate(LocalDate date);

    List<Memo> findByMemoDateBetweenAndUserId(LocalDate memoDate, LocalDate memoDate2, String userId);

    Optional<Memo> findMemoByMemoNo(Long memoNo);

    List<Memo> findMemosByUserIdAndMemoDateOrderByCreatedate(String userId, LocalDate memoDate);

    void deleteMemoByMemoNoIn(Collection<Long> memoNo);


}
