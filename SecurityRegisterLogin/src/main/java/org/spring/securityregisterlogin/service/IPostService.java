package org.spring.securityregisterlogin.service;

import org.spring.securityregisterlogin.entity.Post;

import java.util.List;

public interface IPostService {

    public Post savePost(Post post);

    public List<Post> getAllPosts();

    public Boolean deletePost(int id);

}
