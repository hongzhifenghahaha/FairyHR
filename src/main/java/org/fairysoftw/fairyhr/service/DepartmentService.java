package org.fairysoftw.fairyhr.service;

import org.fairysoftw.fairyhr.model.Department;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DepartmentService {
    /**
     * 查询所有的部门
     * @return 包含所有部门实例的列表
     */
    List<Department> selectAll();

    /**
     * 查询id符合的部门
     * @param id 部门id
     * @return id符合的部门，若没有符合的部门，则返回null
     */
    @Nullable
    Department selectById(String id);

    /**
     * 插入新的部门, 同时也可能会插入新的用户
     * @param department 新的部门的实例
     * @return 插入的记录条数
     */
    int insert(Department department);

    /**
     * 更新部门信息, 也可用于向部门插入用户和管理员
     * <pre>
     * <code>User newUser = new User();
     * Department department = departmentService.selectById(id);
     * department.getUsers().add(newUser);
     * departmentService.update(department);</code>
     * </pre>
     * @param department 部门实例
     * @return 更新的记录条数
     */
    int update(Department department);

    /**
     * 根据id删除部门
     * @param id 部门id
     * @return 删除的记录条数
     */
    int deleteById(String id);
}
