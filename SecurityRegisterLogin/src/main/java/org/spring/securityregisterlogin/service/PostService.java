package org.spring.securityregisterlogin.service;

import org.spring.securityregisterlogin.entity.Post;
import org.spring.securityregisterlogin.repository.PostRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

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

    @Override
    public Post getPostById(int id) {
        Post post = postRepo.findById(id).orElse(null);
        return post;
    }

    @Override
    public List<Post> getAllSelectPosts(String category) {
        List<Post> posts = null;
        if(ObjectUtils.isEmpty(category)){
            posts = postRepo.findAll();
        } else {
            posts = postRepo.findByCategory(category);
        }
        return posts;
    }

    @Override
    public List<Post> searchPost(String ch) {
        return postRepo.findByTitleContainingIgnoreCaseOrCategoryContainingIgnoreCase(ch, ch);
    }

    @Override
    public Page<Post> getAllPostPagination(Integer pageNo, Integer pageSize, String category) {
        Pageable pageable = PageRequest.of(pageNo, pageSize);
        Page<Post> pagePosts = null;
        if(ObjectUtils.isEmpty(category)){
            pagePosts = postRepo.findAllBy(pageable);
        } else {
            pagePosts = postRepo.findByCategory(pageable, category);
        }
        return pagePosts;
    }
}













