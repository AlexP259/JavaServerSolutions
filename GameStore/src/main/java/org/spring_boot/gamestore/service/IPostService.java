package org.spring_boot.gamestore.service;

import org.spring_boot.gamestore.entity.Post;

import java.util.List;

public interface IPostService {
    public Post savePost(Post post);

    public List<Post> getAllPosts();

    public Boolean deletePost(int id);
}
