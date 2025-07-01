package com.minse0.memo.post;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.minse0.memo.post.domain.Post;
import com.minse0.memo.post.service.PostService;

@RequestMapping("/post")
@Controller
public class PostController {
	
	 private final PostService postService;

	    public PostController(PostService postService) {
	        this.postService = postService;
	    }

	    @GetMapping("/list-view")
	    public String postList(Model model) {
	        List<Post> postList = postService.getPostList();
	        model.addAttribute("postList", postList);
	        return "post/list";
	    }

	    @GetMapping("/create-view")
	    public String inputPost() {
	        return "post/input";
	    }

}
