package org.spring.securityregisterlogin.service;

import org.spring.securityregisterlogin.entity.Post;
import org.spring.securityregisterlogin.repository.PostRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostService implements IPostService{

    @Autowired
    private PostRepo postRepo;

    @Override
    public Post savePost(Post post) {
        return postRepo.save(post);
    }

    @Override
    public List<Post> getAllPosts() {
        return postRepo.findAll();
    }

    @Override
    public Boolean deletePost(int id) {
        Post post = postRepo.findById(id).orElse(null);
        if(post != null){
            postRepo.delete(post);
            return true;
        }
        return false;
    }
}
