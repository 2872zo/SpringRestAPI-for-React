package com.react.tutorial.web.post;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.xml.ws.spi.http.HttpContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.react.tutorial.service.domain.Post;
import com.react.tutorial.service.domain.Search;
import com.react.tutorial.service.domain.User;
import com.react.tutorial.service.post.PostService;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;

@RestController
@RequestMapping("/api/posts")
public class PostController {
	@Autowired
	@Qualifier("postServiceImpl")
	private PostService postService;

	@Value("${JWT_SECRET}")
	private String jwtSecret;
	
	@Value("${TOKEN_NAME}")
	private String tokenName;
	
	public PostController() {
		System.out.println(this.getClass());
	}

	@GetMapping("")
	public List<Post> getPosts(@RequestBody(required = false) Search search) {
		return postService.getPosts(search);
	}

	@GetMapping("/{postNo}")
	public Post getPost(@PathVariable int postNo) {
		return postService.getPost(postNo);
	}

	@PostMapping("")
	public Post addPost(@RequestBody Post post, @CookieValue(name = "${TOKEN_NAME}",required = false) Cookie accessToken) throws ExpiredJwtException, UnsupportedJwtException, MalformedJwtException, SignatureException, IllegalArgumentException, UnsupportedEncodingException {
		System.out.println("accessToken : " + accessToken.getValue());

		//쿠키의 로그인값을 이용해 작성자를 지정
		Claims claims = Jwts.parser()
		  .setSigningKey(jwtSecret.getBytes("UTF-8"))
		  .parseClaimsJws(accessToken.getValue()).getBody();	
		User user = new User();
		user.setUserNo(Integer.parseInt(claims.get("userNo").toString()));
		user.setUserId(claims.get("userId").toString());
		user.setUsername(claims.get("username").toString());
		post.setUser(user);
		
		System.out.println(post);
		
		postService.addPost(post);
		post.setUpdatedDate(new Date());
		post.setPublishedDate(new Date());
		return post;
	}

	@RequestMapping(path = "/{postNo}", method = RequestMethod.PATCH)
	public @ResponseBody Post updatePost(@RequestBody Post post, @PathVariable("postNo") String postNo) {
		post.setPostNo(Integer.parseInt(postNo));
		postService.updatePost(post);
		return post;
	}

	@RequestMapping(path = "/{postNo}", method = RequestMethod.DELETE)
	public boolean deletePost(@PathVariable int postNo) {
		return (postService.deletePost(postNo) == 1 ? true : false);
	}
}
