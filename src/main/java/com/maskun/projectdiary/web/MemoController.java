package com.maskun.projectdiary.web;

import com.maskun.projectdiary.domain.user.User;
import com.maskun.projectdiary.service.MemoService;
import com.maskun.projectdiary.web.dto.MemoSaveDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
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


}
