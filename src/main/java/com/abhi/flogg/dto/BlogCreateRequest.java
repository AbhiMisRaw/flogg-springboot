package com.abhi.flogg.dto;

import com.abhi.flogg.entity.BlogStatus;

import java.util.List;

public record BlogCreateRequest(
        String title,
        String content,
        BlogStatus status,
        List<String> tags
    ){}