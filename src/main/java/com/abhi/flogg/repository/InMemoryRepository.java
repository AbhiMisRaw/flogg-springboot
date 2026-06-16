package com.abhi.flogg.repository;

import com.abhi.flogg.entity.Blog;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class InMemoryRepository{
    private Map<String, Blog> blogs = new HashMap<>();

    public Collection<Blog> getBlogs() {
        return blogs.values();
    }

    public Blog getBlog(String id) {
        return blogs.get(id);
    }

    public Blog save(Blog blog) {
        StringBuilder sb = new StringBuilder(blog.getTitle());
        sb.append(blog.getId());
        blogs.put(sb.toString(), blog);
        return blog;
    }
}
