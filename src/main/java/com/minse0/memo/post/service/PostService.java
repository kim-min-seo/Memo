package com.minse0.memo.post.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.minse0.memo.common.Filemanager;
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
			long userId
			, String title
			, String contents
			, MultipartFile file) {
		
		String imagePath = Filemanager.saveFile(userId, file);
		
		Post post = Post.builder()
		.userId(userId)
		.title(title)
		.contents(contents)
		.imagePath(imagePath)
		.build();
		
		try {
			postRepository.save(post);
		} catch(PersistenceException e){
			return false;
		}
		
		return true;
		
	}
	
	public List<Post> getPostList(long userId) {
	    return postRepository.findByUserId(userId);
	}
	
	public Post getPost(long id) {
		Optional<Post> optionalPost =  postRepository.findById(id);
		
		if(optionalPost.isPresent()) {
			return optionalPost.get();
		} else {
			return null;
		}
	}

}
