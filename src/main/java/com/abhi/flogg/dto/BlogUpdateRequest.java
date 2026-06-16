package com.abhi.flogg.dto;

import com.abhi.flogg.entity.BlogStatus;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Optional;

@Getter
@Setter
@ToString
public class BlogUpdateRequest {
    private String title;
    private String content;
    private BlogStatus status;
}
