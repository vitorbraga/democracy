package br.com.democracy.dao;

import java.util.List;

import br.com.democracy.persistence.User;

public interface UserDAO extends GenericDAO<User> {

	User getUserByEmail(String email);

	List<User> getAwaitingNormalUsers();
}
