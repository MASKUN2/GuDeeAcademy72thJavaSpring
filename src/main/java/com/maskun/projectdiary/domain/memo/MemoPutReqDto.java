package com.maskun.projectdiary.domain.memo;

import lombok.AccessLevel;
import lombok.Getter;
import org.springframework.util.StringUtils;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class MemoPutReqDto{
    @Getter
    private final Long memoNo;
    @Getter(AccessLevel.PACKAGE)
    private final String memoContent;
    @Getter(AccessLevel.NONE)
    private final RequestType requestType;

    public MemoPutReqDto(Long memoNo, String memoContent) {
        this.memoNo = memoNo;
        this.memoContent = memoContent;
        this.requestType = classifyRequestType(memoNo, memoContent);
    }
    Memo toEntityForInsert(String userId, LocalDate date){
        return new Memo(userId, date, memoContent, LocalDateTime.now());
    }
    boolean IsReqTypeInsert(){
        return this.requestType == RequestType.INSERT;
    }
    boolean IsReqTypeRemainAndUpdate(){
        return this.requestType == RequestType.REMAIN_AND_UPDATE;
    }
    boolean IsReqTypeIgnore(){
        return this.requestType == RequestType.IGNORE;
    }

    private enum RequestType {
        INSERT,
        REMAIN_AND_UPDATE,
        IGNORE
    }
    private static RequestType classifyRequestType(Long memoNo, String memoContent){
        boolean memoNoIsNull = memoNo == null;
        boolean memoContentHasText = StringUtils.hasText(memoContent);
        if(memoNoIsNull){
            return memoContentHasText? RequestType.INSERT : RequestType.IGNORE ;
        }
        return memoContentHasText? RequestType.REMAIN_AND_UPDATE : RequestType.IGNORE;
    }
}
