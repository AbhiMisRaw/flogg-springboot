package com.abhi.flogg.controller;


import com.abhi.flogg.dto.BlogCreateRequest;
import com.abhi.flogg.dto.BlogDTO;
import com.abhi.flogg.dto.BlogUpdateRequest;
import com.abhi.flogg.service.BlogService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.*;


@RestController
@RequestMapping("/api/v1")
public class BlogController {
    BlogService blogService;

    public BlogController(BlogService blogService) {
        this.blogService = blogService;
    }

    @GetMapping("/blog")
    public Collection<BlogDTO> getBlog() {
        return blogService.getAllBlogs();
    }

    @PutMapping("/blog/{id}/")
    public BlogDTO updateBlog(@PathVariable Long id,@Valid @RequestBody BlogUpdateRequest blogUpdateRequest) {
        return blogService.updateBlog(id, blogUpdateRequest);
    }

    @PostMapping("/blog")
    public BlogDTO createBlog(@RequestBody BlogCreateRequest blog) {
        return  blogService.createBlog(blog);
    }
}
