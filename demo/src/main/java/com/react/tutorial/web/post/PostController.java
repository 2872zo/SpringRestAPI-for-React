package com.react.tutorial.web.post;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.react.tutorial.service.domain.Post;
import com.react.tutorial.service.domain.Search;
import com.react.tutorial.service.post.PostService;

@RestController
@RequestMapping("/api/posts")
public class PostController {
	@Autowired
	@Qualifier("postServiceImpl")
	private PostService postService;

	public PostController() {
		System.out.println(this.getClass());
	}

	@GetMapping("/")
	public List<Post> getPosts(@RequestBody Search search) {
		return postService.getPosts(search);
	}

	@GetMapping("/{postNo}")
	public Post getPost(@PathVariable int postNo) {
		return postService.getPost(postNo);
	}

	@PostMapping("/")
	public Post addPost(@RequestBody Post post) {
		postService.addPost(post);
		return post;
	}

	@RequestMapping(path = "/", method = RequestMethod.PATCH)
	public Post updatePost(@RequestBody Post post) {
		postService.updatePost(post);
		return post;
	}

	@RequestMapping(path = "/{postNo}", method = RequestMethod.DELETE)
	public boolean deletePost(@PathVariable int postNo) {
		return (postService.deletePost(postNo) == 1 ? true : false);
	}
}
