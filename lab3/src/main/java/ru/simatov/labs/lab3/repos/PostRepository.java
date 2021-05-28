package ru.simatov.labs.lab3.repos;

import java.util.Date;
import java.util.List;

import ru.simatov.labs.lab3.models.Post;

public interface PostRepository {

	Post findById(Long id);
	List<Post> getList();
	void addPost(Post post);
	void changeText(Long id, String text);
	void deletePost(Long id);
	List<Post> getPostsToDate(Date date);
	List<Post> getUserPosts(Long userId);
}
