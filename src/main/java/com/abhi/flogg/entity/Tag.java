package com.abhi.flogg.entity;

import jakarta.persistence.*;
import lombok.*;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tags")
public class Tag {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String name;

    @ManyToMany(mappedBy = "tags")
    @Builder.Default
    private Set<Blog> blogs = new HashSet<>();

    public Tag(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Tag(" + name + ")";
    }
}