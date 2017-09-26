package com.jrbac.service;

import java.util.List;
import com.jrbac.entity.LoginUser;
import com.jrbac.entity.Role;

public interface RoleService {
    /**
     * 查询用户所能看到的角色列表
     * 
     * @param loginUser
     * @return
     */
    public List<Role> queryAll(LoginUser loginUser);

    /**
     * 添加菜单或修改菜单
     * 
     * @param menu
     * @return
     */
    public int addOrUpdateRole(Role role, String[] menuIds);

    /**
     * 删除角色
     * 
     * @param id
     * @return
     */
    public int delete(String id);
}
