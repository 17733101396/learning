package com.hainu.hrms.mapper;

import java.util.List;

import org.springframework.stereotype.Repository;
import com.hainu.hrms.model.Post;


@Repository
public interface PostMapper {
	
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
