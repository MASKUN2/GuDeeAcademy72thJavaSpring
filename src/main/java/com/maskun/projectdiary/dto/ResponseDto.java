package com.maskun.projectdiary.dto;

public interface ResponseDto<T> {
    /**도메인엔티티를 DTO로 변경하는 인터페이스메소드
     * @return 각 Dto가 상속하는 엔티티
     */
    ResponseDto fromEntity(T entity);
}
