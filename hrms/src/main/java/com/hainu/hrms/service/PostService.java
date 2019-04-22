package com.hainu.hrms.service;

import java.util.List;
import com.hainu.hrms.model.Post;


public interface PostService {
	
	/** selectPostsByAdmin
	 * 
	 * @return
	 */
	public List<Post> selectPostsByUser(Post post);
	
	
	/** selectPosts
	 * 
	 * @return
	 */
	public List<Post> selectPosts();
	
	/** deletePosts
	 * 
	 * @param id
	 * @return
	 */
	public Integer deletePosts(List<Integer> list);
	
	
	/** addPost
	 * 
	 * @param post
	 * @return
	 */	
	public Integer addPost(Post post);
	
	/** post
	 * 
	 * @param post
	 * @return
	 */
	public Integer updatePost(Post post);
}
