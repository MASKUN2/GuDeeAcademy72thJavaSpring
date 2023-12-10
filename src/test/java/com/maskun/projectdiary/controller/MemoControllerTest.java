package com.maskun.projectdiary.controller;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

class MemoControllerTest {

    @Test
    public void 날짜변환에_실패하고_성공한다(){
        LocalDate date;
        try{
            date = LocalDate.parse("2023-01-1", DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        }catch (Exception e){
            e.printStackTrace();
            System.out.println("실패, 대신진행합니다.");
            date = LocalDate.parse("2023-01-01", DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            System.out.println(date.toString());
        }
    }
}