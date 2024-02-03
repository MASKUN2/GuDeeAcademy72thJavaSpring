package com.maskun.projectdiary.web;

import com.maskun.projectdiary.core.exception.MismatchRequestToDbRecordException;
import com.maskun.projectdiary.domain.memo.Memo;
import com.maskun.projectdiary.domain.user.User;
import com.maskun.projectdiary.domain.memo.MemoService;
import com.maskun.projectdiary.web.dto.MemoSaveDto;
import com.maskun.projectdiary.web.dto.PageUrl;
import com.maskun.projectdiary.web.dto.Pagination;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

import static org.springframework.http.ResponseEntity.*;

/**
 * <p> 유저 메모 컨트롤러</p>
 */
@Slf4j
@RequiredArgsConstructor
@Controller
public class MemoController {
    private final MemoService memoService;
    @ResponseBody
    @GetMapping("/api/v1/memo/{date}")
    public ResponseEntity<Object> getMemoList(@PathVariable(name = "date")LocalDate date,
                                              @SessionAttribute(name = "loginUser", required = false)User user){
        if(user == null){
            return status(HttpStatus.UNAUTHORIZED).build();
        }
        return ok().body(memoService.findUserMemoListByDate(user.getUserId(), date));
    }

    @ResponseBody
    @PostMapping("/api/v1/memo/{date}")
    public ResponseEntity<Object> saveDateMemo(@PathVariable(name = "date")LocalDate date,
                                               @RequestBody(required = false) List<MemoSaveDto> dtoList,
                                               @SessionAttribute(name = "loginUser", required = false)User user){
        log.debug(dtoList.toString());

        //로그인 확인
        if(user == null){
            return status(HttpStatus.UNAUTHORIZED).build();
        }
        //빈 요청데이터는 수행하지 않고 ok 리턴
        if(dtoList.isEmpty()){
            return ok().build();
        }
        //요청실행
        try {
            memoService.updateUserDateMemo(user.getUserId(), date, dtoList);
            return ok().build();
        }catch (MismatchRequestToDbRecordException e){
            return badRequest().body("사용자 정보가 일치하지 않습니다");
        }catch (Exception e){
            log.error("메모의 수정업데이트에 실패했습니다.", e);
            return internalServerError().body("error");
        }
    }

    /**
     * @param user
     * @param keyword
     * @param pageable 정렬조건 sort를 옵션으로 받을 수 있습니다. 현재버전에선 사용하지 않습니다.
     * @param request
     * @param model
     * @return
     */
    @GetMapping("/memo")//메모 검색페이지
    public String getMemoList(@SessionAttribute(name = "loginUser")User user,
                              @RequestParam(name = "keyword") @NotNull String keyword,
                              @PageableDefault(size = 10, sort = {}) Pageable pageable,
                              HttpServletRequest request,
                              Model model){
        //pageable의 sort 입력을 방지
        if(pageable.getSort().isSorted()){
            throw new IllegalArgumentException("쿼리스트링 sort는 forbidden 되었습니다.");
        }
        //pagination 가져오기
        Pagination<Memo> memoPagination = memoService.retrieveUserMemoByKeywordPagination(user.getUserId(),keyword, pageable);
        //페이지네이션 정보 생성
        List<PageUrl> pageUrlList = memoPagination.getPageUrlList(5, request.getRequestURL().toString(), request.getParameterMap());
        log.debug("생성된 페이지네이션 {}", pageUrlList.toString());

        model.addAttribute("retrievedMemoList", memoPagination.getContent());
        model.addAttribute("pageUrlList", pageUrlList);
        model.addAttribute("keyword", keyword);
        return "memo";
    }


}
