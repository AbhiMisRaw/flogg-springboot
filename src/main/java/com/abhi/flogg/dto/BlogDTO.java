package com.abhi.flogg.dto;


import com.abhi.flogg.entity.BlogStatus;
import com.abhi.flogg.entity.Tag;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

public record BlogDTO(
        long id,
        String title,
        String content,
        List<String> tags,
        BlogStatus status,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
){};
