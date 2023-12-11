package com.maskun.projectdiary.vo.dto;

public interface Dto<T> {
    /**DTO를 서비스레이어에서 도메인값객체로 변경하는 인터페이스 메소드
     * @return 각 Dto가 상속하는 엔티티
     */
    T toDomainEntity();
}
