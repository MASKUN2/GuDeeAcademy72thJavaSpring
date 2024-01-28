package com.maskun.projectdiary.domain.user;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;

import java.time.LocalDateTime;
@ToString
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class User {
    @Id
    private String userId;
    private String userPw;
    private Integer userLevel;
    private LocalDateTime createdate;


}
