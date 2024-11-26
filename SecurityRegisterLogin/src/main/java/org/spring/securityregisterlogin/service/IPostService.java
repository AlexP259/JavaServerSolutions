package org.spring.securityregisterlogin.service;

import org.spring.securityregisterlogin.entity.Post;
import org.springframework.data.domain.Page;

import java.util.List;

public interface IPostService {

    public Post savePost(Post post);

    public List<Post> getAllPosts();

    public Boolean deletePost(int id);

    public Post getPostById(int id);

    public List<Post> getAllSelectPosts(String category);

    public List<Post> searchPost(String ch);

    public Page<Post> getAllPostPagination(Integer pageNo, Integer pageSize, String category);

}
