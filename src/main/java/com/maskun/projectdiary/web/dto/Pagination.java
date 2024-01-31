package com.maskun.projectdiary.web.dto;

import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Map;

/**
 * 커스텀 페이지네이션 정보를 담기 Page<T>상속 인터페이스
 */
public interface Pagination<T> extends Page<T> {

    List<PageUrl> getPageUrlList(Integer pageOffset, String urlPath, Map<String,String[]> params);
}

