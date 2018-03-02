package apis.mapper;

import apis.models.Blog;

import java.util.List;

public interface BlogMapper {
    void insertBlog(Blog blog);
    void updateBlog(Blog blog);
    void removeBlog(Integer id);
    List<Blog> allBlogs();
}
