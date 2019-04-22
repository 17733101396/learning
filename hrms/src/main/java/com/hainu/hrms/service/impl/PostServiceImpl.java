package com.hainu.hrms.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hainu.hrms.mapper.PostMapper;
import com.hainu.hrms.model.Post;
import com.hainu.hrms.service.PostService;

@Service("postService")
public class PostServiceImpl implements PostService {
	@Autowired
	PostMapper postMapper;
	@Override
	public List<Post> selectPostsByUser(Post post) {
		// TODO Auto-generated method stub
		return postMapper.selectPostsByUser(post);
	}

	@Override
	public List<Post> selectPosts() {
		// TODO Auto-generated method stub
		return postMapper.selectPosts();
	}

	@Override
	public Integer deletePosts(List<Integer> list) {
		// TODO Auto-generated method stub
		return postMapper.deletePosts(list);
	}

	@Override
	public Integer addPost(Post post) {
		// TODO Auto-generated method stub
		return postMapper.addPost(post);
	}

	@Override
	public Integer updatePost(Post post) {
		// TODO Auto-generated method stub
		return postMapper.updatePost(post);
	}

}
