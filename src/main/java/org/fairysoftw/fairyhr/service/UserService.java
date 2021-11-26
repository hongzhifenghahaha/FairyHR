package org.fairysoftw.fairyhr.service;

import org.fairysoftw.fairyhr.model.User;
import org.springframework.lang.Nullable;

import java.util.List;

public interface UserService {
    /**
     * 查询所有的用户
     *
     * @return 包含所有用户实例的列表
     */
    List<User> selectAll();

    /**
     * 查询id符合的用户
     *
     * @param id 用户id
     * @return id符合的用户，若没有符合的用户，则返回null
     */
    @Nullable
    User selectById(String id);

    /**
     * 根据id删除用户
     *
     * @param id 用户id
     * @return 删除的记录条数
     */
    int deleteById(String id);

    /**
     * 插入新的用户，同时插入新的时间表、请假时间和打卡时间
     *
     * @param user 新的用户实例
     * @return 插入的记录条数
     */
    int insert(User user);

    /**
     * 插入新的用户，同时插入新的时间表、请假时间和打卡时间
     *
     * @param users 用户实例列表
     * @return 插入的记录条数
     */
    int insert(List<User> users);

    /**
     * 更新用户，也可用于向用户插入时间表、请假时间和打卡时间
     *
     * @param user 用户实例
     * @return 更新的记录条数
     */
    int update(User user);
}
