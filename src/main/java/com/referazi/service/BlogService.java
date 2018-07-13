package com.referazi.service;

import com.referazi.dao.BlogDao;
import com.referazi.models.Blog;
import com.referazi.models.User;
import com.referazi.security.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.io.*;
import java.util.List;

public class BlogService {

    String fileUploadLocation;

    @Autowired
    @Qualifier("blogDao")
    BlogDao blogDao;

    public void postBlog(InputStream uploadedInputStream,
                             String imageName, String title, String description) throws Exception {

        try {

            //TODO: Generate random file name
            String imageUrl = fileUploadLocation + imageName;

            OutputStream out = new FileOutputStream(new File(imageUrl));
            int read = 0;
            byte[] bytes = new byte[1024];

            out = new FileOutputStream(new File(imageUrl));
            while ((read = uploadedInputStream.read(bytes)) != -1) {
                out.write(bytes, 0, read);
            }
            out.flush();
            out.close();

            User user = SecurityUtils.getUser();

            Blog blog = new Blog(user.getId(), title, description, imageUrl);

            blogDao.insertBlog(blog);


        } catch (IOException e) {

            e.printStackTrace();
        }

    }

    public List<Blog> getAllBlogs(){
        return blogDao.allBlogs();
    }

    public Blog getBlogById(Integer id){
        return blogDao.getBlogById(id);
    }

    public void deleteBlogById(Integer id){
        blogDao.removeBlog(id);
    }

    public String getFileUploadLocation() {
        return fileUploadLocation;
    }

    public void setFileUploadLocation(String fileUploadLocation) {
        this.fileUploadLocation = fileUploadLocation;
    }
}
