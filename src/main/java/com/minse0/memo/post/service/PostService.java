package com.minse0.memo.post.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.minse0.memo.post.domain.Post;
import com.minse0.memo.post.repository.PostRepository;

import jakarta.persistence.PersistenceException;


@Service
public class PostService {
	
	private PostRepository postRepository;
	
	public PostService(PostRepository postRepository) {
		this.postRepository = postRepository;
	}
	
	public boolean addPost(
			long userID
			, String title
			, String contents) {
		
		Post post = Post.builder()
		.userId(userID)
		.title(title)
		.contents(contents)
		.build();
		
		try {
			postRepository.save(post);
		} catch(PersistenceException e){
			return false;
		}
		
		return true;
		
	}
	
	public List<Post> getPostList() {
	    return postRepository.findAllByOrderByIdDesc();
	}

}
