package pers.kkddyz.learnmybatis.manyToMany.dao;

import pers.kkddyz.learnmybatis.manyToMany.domain.Role;

import java.util.List;

/**
 * @author kkddyz
 */
public interface IRoleDao {

    /**
     * 查询所有角色
     * @return
     */
    List<Role> findAll();
}
