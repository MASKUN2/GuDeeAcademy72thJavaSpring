package com.maskun.projectdiary.dto;

public interface RequestDto<T> {
    /**requestDTO를 serviceDto로 변경하는 인터페이스 메소드
     * @return 각 Dto가 상속하는 엔티티
     */
    T toServiceDto();
}
