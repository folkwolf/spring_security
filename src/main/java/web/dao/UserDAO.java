package web.dao;

import web.model.User;

import java.util.List;

public interface UserDAO {

    public User getUser(int id);

    public List<User> getAllUsers();

    public void saveUser(User user);

    public void updateUser(User user);

    public void deleteUser(int id);

    public User getUserByName(String var);

}
