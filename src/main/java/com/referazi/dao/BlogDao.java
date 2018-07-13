package com.referazi.dao;

import com.referazi.models.Blog;

import java.util.List;

public interface BlogDao {
    void insertBlog(Blog blog);
    void updateBlog(Blog blog);
    Blog getBlogById(Integer id);
    void removeBlog(Integer id);
    List<Blog> allBlogs();
}
