package com.maskun.projectdiary.web;

import com.maskun.projectdiary.domain.memo.Memo;
import com.maskun.projectdiary.domain.user.User;
import com.maskun.projectdiary.domain.memo.MemoService;
import com.maskun.projectdiary.web.dto.MemoSaveDto;
import com.maskun.projectdiary.web.dto.PageUrl;
import com.maskun.projectdiary.web.dto.Pagination;
import jakarta.servlet.http.HttpServletRequest;
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
        boolean isSuccess = memoService.updateUserDateMemo(user.getUserId(),date, dtoList);

        return isSuccess? ok().build() : internalServerError().body("error");

    }

    @GetMapping("/memo")
    public String getMemoList(@SessionAttribute(name = "loginUser")User user,
                              @RequestParam(name = "keyword")String keyword,
                              @PageableDefault(size = 10) Pageable pageable,
                              HttpServletRequest request,
                              Model model){
        //데이터 가져오기
        Pagination<Memo> memoPagination = memoService.retrieveUserMemoByKeywordPagination(user.getUserId(),keyword, pageable);
        List<PageUrl> pageUrlList = memoPagination.getPageUrlList(5, request.getRequestURL().toString(), request.getParameterMap());
        log.debug("생성된 페이지네이션 {}", pageUrlList.toString());
        model.addAttribute("retrievedMemoList", memoPagination.getContent());
        model.addAttribute("pageUrlList", pageUrlList);
        model.addAttribute("keyword", keyword);
        return "memo";
    }


}
