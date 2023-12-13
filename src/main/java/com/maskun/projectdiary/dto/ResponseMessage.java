package com.maskun.projectdiary.dto;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
@Builder
@Data
public class ResponseMessage {
    private String message;
}
