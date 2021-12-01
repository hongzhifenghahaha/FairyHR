package org.fairysoftw.fairyhr.service;

import org.fairysoftw.fairyhr.model.Schedule;
import org.fairysoftw.fairyhr.model.User;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
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
     * 根据id删除用户，若没有id相同的用户，则不执行任何操作。
     * 删除用户的时候会同时删除该用户的请假记录与考勤纪律，而不会删除与该用户关联的时间表。
     *
     * @param id 用户id
     * @return 删除的记录条数
     */
    int deleteById(String id);

    /**
     * 插入新的用户, 若已存在相同{@link org.fairysoftw.fairyhr.model.User#id}的用户则不执行任何操作。
     * <br><br>
     * 若参数{@link org.fairysoftw.fairyhr.model.User#schedules}中存在新的时间表,
     * 则使用{@link org.fairysoftw.fairyhr.service.ScheduleService#insert(Schedule)}插入
     * 新的{@link org.fairysoftw.fairyhr.model.Schedule}, 并且在数据库中关联新的用户与时间表;
     * <br>
     * 若参数{@link org.fairysoftw.fairyhr.model.User#schedules}中存在已存在的时间表,
     * 则直接关联该用户和时间表，但并不会更新时间表的内容。
     * <br><br>
     * 对于参数中的{@link org.fairysoftw.fairyhr.model.User#attendanceTimes}，将全部新建并与用户关联。
     * <br><br>
     * 对于{@link org.fairysoftw.fairyhr.model.User#leaves}采取与
     * {@link org.fairysoftw.fairyhr.model.User#schedules}相同的策略。
     *
     * @param user 新的用户实例
     * @return 插入的记录条数
     */
    int insert(User user);

    /**
     * 插入多个新的用户。
     * <br><br>
     * 与{@link org.fairysoftw.fairyhr.service.UserService#insert(User)}采用相同的修改规则。
     *
     * @param users 用户实例列表
     * @return 插入的记录条数
     */
    int insert(List<User> users);

    /**
     * 更新已有用户, 若不存在相同{@link org.fairysoftw.fairyhr.model.User#id}的用户则不执行任何操作。
     * <br><br>
     * 若参数{@link org.fairysoftw.fairyhr.model.User#schedules}中存在新的时间表,
     * 则使用{@link org.fairysoftw.fairyhr.service.ScheduleService#insert(Schedule)}插入
     * 新的{@link org.fairysoftw.fairyhr.model.Schedule}, 并且在数据库中关联新的用户与时间表;
     * <br>
     * 若参数{@link org.fairysoftw.fairyhr.model.User#schedules}中存在已存在的时间表,
     * 则直接关联该用户和时间表，但并不会更新时间表的内容。
     * <br><br>
     * 对于参数中的{@link org.fairysoftw.fairyhr.model.User#attendanceTimes}中数据库中不存在的，将全部新建并与用户关联;
     * <br>
     * 对于缺失的，将从数据库中删除。
     * <br><br>
     * 对于{@link org.fairysoftw.fairyhr.model.User#leaves}采取与
     * {@link org.fairysoftw.fairyhr.model.User#schedules}相同的策略。
     *
     * @param user 用户实例
     * @return 更新的记录条数
     */
    int update(User user);
}
