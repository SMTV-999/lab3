package ru.simatov.labs.lab3.repos;

import java.util.List;

import ru.simatov.labs.lab3.models.User;

public interface UserRepository {

	public List<User> doList();
	public void save(User user);
	public void change();
	public void remove(Long id);
	public User findById(Long id);
}
