package web.dao;

import web.model.Role;

import java.util.List;

public interface RoleDAO {
    public List<Role> getAllRoles();
    public Role findOne(long id);
}
