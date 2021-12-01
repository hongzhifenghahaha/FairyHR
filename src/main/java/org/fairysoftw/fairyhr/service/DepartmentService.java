package org.fairysoftw.fairyhr.service;

import org.fairysoftw.fairyhr.model.Department;
import org.fairysoftw.fairyhr.model.User;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DepartmentService {
    /**
     * 查询所有的部门
     *
     * @return 包含所有部门实例的列表
     */
    List<Department> selectAll();

    /**
     * 查询id符合的部门
     *
     * @param id 部门id
     * @return id符合的部门，若没有符合的部门，则返回null
     */
    @Nullable
    Department selectById(String id);

    /**
     * 插入新的部门, 若已存在相同{@link org.fairysoftw.fairyhr.model.Department#id}的部门则不执行任何操作。<br>
     * 若参数{@link org.fairysoftw.fairyhr.model.Department#users}中存在新的用户,
     * 则使用{@link org.fairysoftw.fairyhr.service.UserService#insert(User)}插入新的user, 并且在数据库中关联新的用户与部门;
     * 若参数{@link org.fairysoftw.fairyhr.model.Department#users}中存在已存在的用户,
     * 则使用{@link org.fairysoftw.fairyhr.service.UserService#update(User)}更新该用户的数据。<br>
     * 对于参数中的{@link org.fairysoftw.fairyhr.model.Department#managers}与
     * {@link org.fairysoftw.fairyhr.model.Department#leaveRequests}也采取与
     * {@link org.fairysoftw.fairyhr.model.Department#users}相同的策略。
     *
     * @param department 新的部门的实例
     * @return 插入的记录条数
     */
    int insert(Department department);

    /**
     * 根据{@link org.fairysoftw.fairyhr.model.Department#id}更新已经存在部门，若没有相同id的部门存在则不执行任何操作。
     * <br><br>
     * 若参数{@link org.fairysoftw.fairyhr.model.Department#users}中存在新的用户，
     * 则使用{@link org.fairysoftw.fairyhr.service.UserService#insert(User)}插入新的user，并且在数据库中关联新的用户与部门；
     * 若参数{@link org.fairysoftw.fairyhr.model.Department#users}中存在已存在的用户，
     * 则使用{@link org.fairysoftw.fairyhr.service.UserService#update(User)}更新该用户的数据；
     * 参数{@link org.fairysoftw.fairyhr.model.Department#users}中不存在的用户，在数据库中解除该用户与部门的关联，
     * 即等同于在该部门中移除某个用户，不等于在系统中移除了某个用户。
     * <br><br>
     * 对于参数中的{@link org.fairysoftw.fairyhr.model.Department#managers}与
     * {@link org.fairysoftw.fairyhr.model.Department#leaveRequests}也采取与
     * {@link org.fairysoftw.fairyhr.model.Department#users}相同的策略。
     * <br><br>
     * 特别的，对于leaveRequest，解除部门与某个leaveRequest的关联的同时会删除该leaveRequest，而对于manager与user则不会真的删除。
     * <br><br>
     * 可以使用以下代码为{@link org.fairysoftw.fairyhr.model.Department}添加用户，并保存：
     * <pre>
     * <code>User newUser = new User();
     * Department department = departmentService.selectById("department's id");
     * department.getUsers().add(newUser);
     * departmentService.update(department);</code>
     * </pre>
     * 可以使用以下代码为{@link org.fairysoftw.fairyhr.model.Department}移除用户，并保存：
     * <pre>
     * <code>Department department = departmentService.selectById("department's id");
     * department.getUsers().removeIf((u)->u.getId().equals("one user's id"));
     * departmentService.update(department);</code>
     * </pre>
     *
     * @param department 部门实例
     * @return 更新的记录条数
     */
    int update(Department department);

    /**
     * 根据id删除部门，同时删除该部门与其下的{@link org.fairysoftw.fairyhr.model.Department#users}、
     * {@link org.fairysoftw.fairyhr.model.Department#managers}、
     * {@link org.fairysoftw.fairyhr.model.Department#managers}中的元素的关联。
     * <br><br>
     * 特别的，对于leaveRequest，删除部门的同时会删除该部门下的所有leaveRequest，而对于manager与user则不会真的删除。
     *
     * @param id 部门id
     * @return 删除的记录条数
     */
    int deleteById(String id);
}
