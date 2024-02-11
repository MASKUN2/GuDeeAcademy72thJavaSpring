package com.maskun.projectdiary.domain.memo;

import com.maskun.projectdiary.core.exception.MismatchRequestToDbRecordException;
import com.maskun.projectdiary.web.dto.Pagination;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;
import java.util.List;

public interface MemoService {

    List<Memo> findUserMemoListByDate(String userId, LocalDate date);

    void updateUserDateMemoList(String userId, LocalDate date, List<MemoPutReqDto> reqMemoList)throws MismatchRequestToDbRecordException;

    Pagination<Memo> retrieveUserMemoByKeywordPagination(String userId, String keyword, Pageable pageable);
}
