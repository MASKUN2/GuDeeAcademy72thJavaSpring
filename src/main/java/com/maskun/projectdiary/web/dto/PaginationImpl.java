package com.maskun.projectdiary.web.dto;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.lang.Nullable;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class PaginationImpl<T> extends PageImpl<T> implements Pagination<T> {

    private PaginationImpl(List<T> content, Pageable pageable, long total) {
        super(content, pageable, total);
    }


    public static <T>Pagination<T> fromPage(Page<T> page){
        return new PaginationImpl<>(page.getContent(), page.getPageable(), page.getTotalElements());
    }

    /** 페이징 URL을 생성하는 메소드
     * @param pageOffset half side 간격
     * @param urlPath 쿼리스트링을 제외한 요청 URL
     * @param requestParams 쿼리스트링 맵
     */
    @Override
    public List<PageUrl> getPageUrlList(@Nullable Integer pageOffset, String urlPath, @Nullable Map<String,String[]> requestParams) {
        //nullable 페이지 오프셋 초기화
        pageOffset = (pageOffset == null) ? 5 : pageOffset;
        //파라미터 준비
        MultiValueMap<String, String> params = getMultiValueMap(requestParams);
        //페이지숫자 정의
        final int currentPage = getNumber() + 1;
        final int startPage = currentPage - pageOffset;
        final int totalPage = getTotalPages();

        //페이지, url 생성
        List<PageUrl> pageUrlList = new ArrayList<>();
        for (int i = 0; i <= pageOffset * 2; i++) {
            int page = startPage + i;
            if (page >= 1 && page <= totalPage) {
                int pageIndex = page - 1;
                String PageUrl = UriComponentsBuilder
                        .fromHttpUrl(urlPath).queryParams(params)
                        .replaceQueryParam("page", pageIndex)
                        .replaceQueryParam("size", getSize())
                        .toUriString();
                pageUrlList.add(new PageUrl(page, PageUrl));
            }
        }
        return pageUrlList;
    }

    /** 리퀘스트 파라미터 맵을를 uri컴포넌트빌더 사용에 적합한 멀티밸류맵으로 전환시킵니다.
     */
    private static MultiValueMap<String, String> getMultiValueMap(@Nullable Map<String, String[]> requestParams) {
        if(requestParams == null){
            return null;
        }
        return requestParams.entrySet().stream()
                .collect(Collectors.toMap(Map.Entry::getKey, e -> Arrays.asList(e.getValue()),
                        (existing, replacement) -> existing, LinkedMultiValueMap::new)
                );
    }
}