package com.maskun.projectdiary.core;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @author 응답엔티티를 생성하는 컴포넌트 클래스
 */
@Component
public class ResponseEntityGenerator {

    /**<p>GET요청 응답엔티티를 생성하는 메서드</p>
     * @param responseObject object
     * @return responseObject 가 널? 404 notFound : 200 ok
     */
    public ResponseEntity<Object> respOr404(Object responseObject){
        if(responseObject == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("serverMessage","조회결과가 없습니다."));
        }
        return  ResponseEntity.ok(responseObject);
    }

    public ResponseEntity<Object> respUnAuth(){
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }

    public ResponseEntity<Object> ok(){
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    /**
     * <p> 500 내부서버 에러 응답을 생성하는 메서드</p>
     * @return 500 with serverMessage
     */
    public ResponseEntity<Object> respInternalServerError(String serverMessage){
        return ResponseEntity.internalServerError().body(Map.of("serverMessage",serverMessage));
    }
}