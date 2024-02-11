package com.maskun.projectdiary.domain.memo;

import org.springframework.util.StringUtils;

public record MemoPutReqDto(Long memoNo, String memoContent, CUDI requestType){

    public MemoPutReqDto(Long memoNo, String memoContent) {
        this(memoNo, memoContent, classifyRequestType(memoNo, memoContent));
    }

    boolean IsReqTypeCreate(){
        return this.requestType == CUDI.CREATE;
    }
    boolean IsReqTypeUpdate(){
        return this.requestType == CUDI.UPDATE;
    }
    boolean IsReqTypeDelete(){
        return this.requestType == CUDI.DELETE;
    }
    boolean IsReqTypeIgnore(){
        return this.requestType == CUDI.IGNORE;
    }

    private enum CUDI {
        CREATE,
        UPDATE,
        DELETE,
        IGNORE
    }

    private static CUDI classifyRequestType(Long memoNo, String memoContent){
        boolean memoNoIsNull = memoNo == null;
        boolean memoContentHasText = StringUtils.hasText(memoContent);

        if(memoNoIsNull){
            return memoContentHasText? CUDI.CREATE : CUDI.IGNORE ;
        }else {
            return memoContentHasText? CUDI.UPDATE : CUDI.DELETE ;
        }
    }
}
