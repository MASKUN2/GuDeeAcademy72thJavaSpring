package com.maskun.projectdiary.domain.memo;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Repository
public interface MemoRepository extends JpaRepository<Memo,Long> {
    List<Memo> findByUserIdAndMemoDate(String userId, LocalDate memoDate);

    List<Memo> findByMemoDateBetweenAndUserId(LocalDate memoDate, LocalDate memoDate2, String userId);

    Optional<Memo> findMemoByMemoNo(Long memoNo);

    List<Memo> findMemosByUserIdAndMemoDateOrderByCreatedate(String userId, LocalDate memoDate);

    void deleteMemoByMemoNoIn(Collection<Long> memoNo);

    Page<Memo> findMemoByUserIdAndMemoContentContainsOrderByCreatedateDesc(String userId, String keyword, Pageable pageable);

    void deleteAllByUserIdAndMemoDate(String userId, LocalDate memoDate);

    void deleteMemosByMemoNoNotIn(List<Long> MemoNo);

}
