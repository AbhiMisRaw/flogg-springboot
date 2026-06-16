package com.abhi.flogg.service;


import com.abhi.flogg.dto.BlogCreateRequest;
import com.abhi.flogg.dto.BlogDTO;
import com.abhi.flogg.dto.BlogUpdateRequest;
import com.abhi.flogg.entity.Blog;
import com.abhi.flogg.entity.Tag;
import com.abhi.flogg.entity.User;
import com.abhi.flogg.repository.BlogRepository;
import com.abhi.flogg.repository.TagRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;

@AllArgsConstructor
@Service
public class BlogService {
    BlogRepository blogRepository;
    TagRepository tagRepository;
    UserService userService;

    public Collection<BlogDTO> getAllBlogs() {
        var blogs = blogRepository.findAll();
        System.out.println(blogs);
        return blogs.stream()
                .map(
                blog -> new BlogDTO(
                        blog.getId(),
                        blog.getTitle(),
                        blog.getContent(),
                        blog.getTags().stream()
                                .map(Tag::getName) // Extract the string name from each Tag
                                .toList(),
                        blog.getStatus(),
                        blog.getCreatedAt(),
                        blog.getUpdatedAt()
                )).toList();
    }

    public Blog getBlogById(Long id) {
        var exp = new RuntimeException("Blog not found");
        return blogRepository.findById(id)
                .orElseThrow(()-> exp);
    }

    public BlogDTO updateBlogById(Long id, BlogUpdateRequest blogCreateRequest) {
        return null;
    }

    @Transactional
    public BlogDTO createBlog(BlogCreateRequest blogReq) {
        User user = userService.getDefaultHelperUser();
        Blog blog = Blog.builder().title(blogReq.title())
                .content(blogReq.content())
                .status(blogReq.status())
                .build();

        for(var tagName: blogReq.tags()){
            Tag tag = tagRepository.findByNameIgnoreCase(tagName.toLowerCase())
                    .orElseGet(() -> tagRepository.save(new Tag(tagName.toLowerCase())));
            blog.addTag(tag);
        }
        user.addBlog(blog);
        blogRepository.save(blog);

        return new BlogDTO(
                1L,
                blog.getTitle(),
                blog.getContent(),
                blog.getTags()
                        .stream()
                        .map(Tag::getName) // Extract the string name from each Tag
                        .toList(),
                blog.getStatus(),
                blog.getCreatedAt(),
                blog.getUpdatedAt()
        );
    }

    @Transactional
    public BlogDTO updateBlog(Long blogId, BlogUpdateRequest updateReq) {
        System.out.println("Fetching blog ID :"+blogId);
        Blog blog = blogRepository.findById(blogId)
                .orElseThrow(() -> new RuntimeException("Blog not found with ID: " + blogId));

        // Apply updates only if the optional fields are present
        System.out.println(updateReq);
        if(updateReq.getTitle() != null){
            System.out.println("Title :"+updateReq.getTitle());
            blog.setTitle(updateReq.getTitle());
        }
        if(updateReq.getContent() != null){
            System.out.println("Content :"+updateReq.getContent());
            blog.setContent(updateReq.getContent());
        }
        if(updateReq.getStatus() != null){
            System.out.println("Status :"+updateReq.getStatus()
            );
            blog.setStatus(updateReq.getStatus());
        }

        blogRepository.save(blog);
        return new BlogDTO(
                blog.getId(),
                blog.getTitle(),
                blog.getContent(),
                blog.getTags().stream()
                        .map(Tag::getName)
                        .toList(),
                blog.getStatus(),
                blog.getCreatedAt(),
                blog.getUpdatedAt()
        );
    }
}
