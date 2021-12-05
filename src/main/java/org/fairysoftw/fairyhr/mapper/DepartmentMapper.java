package org.fairysoftw.fairyhr.mapper;

import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;
import org.fairysoftw.fairyhr.model.Department;
import org.fairysoftw.fairyhr.model.LeaveRequest;
import org.fairysoftw.fairyhr.model.User;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 映射类,实现{@link Department}类与数据库中的department表的映射。
 *
 * @version 1.0
 */
@Mapper
public interface DepartmentMapper {
    /**
     * 选出department表中的所有行，并映射成{@link Department}对象列表。
     *
     * @return department表中所有部门。
     */
    @Select("SELECT * FROM department")
    @Results({
            @Result(id = true, property = "id", column = "id"),
            @Result(property = "name", column = "d_name"),
            @Result(property = "department", column = "d_id", one = @One(select = "org.fairysoftw.fairyhr.mapper.DepartmentMapper.selectById", fetchType = FetchType.LAZY)),
            @Result(property = "managers", column = "id", many = @Many(select = "org.fairysoftw.fairyhr.mapper.DepartmentManagerMapper.selectByDepartmentId", fetchType = FetchType.LAZY)),
            @Result(property = "users", column = "id", many = @Many(select = "org.fairysoftw.fairyhr.mapper.DepartmentUserMapper.selectByDepartmentId", fetchType = FetchType.LAZY)),
            @Result(property = "leaveRequests", column = "id", many = @Many(select = "org.fairysoftw.fairyhr.mapper.DepartmentLeaveRequestMapper.selectByDepartmentId", fetchType = FetchType.LAZY)),
    })
    List<Department> selectAll();

    /**
     * 选出department表中id=#{id}的行，并映射成{@link Department}对象。
     *
     * @param id 要选出的部门的id。
     * @return department表中id=#{id}的部门。
     */
    @Select("SELECT * FROM department WHERE id = #{id}")
    @Results({
            @Result(id = true, property = "id", column = "id"),
            @Result(property = "name", column = "d_name"),
            @Result(property = "department", column = "d_id", one = @One(select = "org.fairysoftw.fairyhr.mapper.DepartmentMapper.selectById", fetchType = FetchType.LAZY)),
            @Result(property = "managers", column = "id", many = @Many(select = "org.fairysoftw.fairyhr.mapper.DepartmentManagerMapper.selectByDepartmentId", fetchType = FetchType.LAZY)),
            @Result(property = "users", column = "id", many = @Many(select = "org.fairysoftw.fairyhr.mapper.DepartmentUserMapper.selectByDepartmentId", fetchType = FetchType.LAZY)),
            @Result(property = "leaveRequests", column = "id", many = @Many(select = "org.fairysoftw.fairyhr.mapper.DepartmentLeaveRequestMapper.selectByDepartmentId", fetchType = FetchType.LAZY)),
    })
    Department selectById(@Param("id") String id);

    /**
     * 把{@link Department}对象插入到department表中。
     *
     * @param department 要插入的{@link Department}对象。
     * @return 成功插入到表中的行的数目，若插入失败或插入被忽略，则返回0。
     */
    @Insert("""
            <script>
            INSERT IGNORE INTO department(id, d_name, d_id, deleted)
            VALUES(#{id}, #{name},
            <if test='#{department}==null'> NULL </if> <if test='#{department}!=null'>#{department.id}</if>,
            #{deleted})
            </script>""")
    int insert(Department department);

    /**
     * 删除department表中id=#{id}的行。
     *
     * @param id 要删除的部门的id。
     * @return 成功从表中的删除的行的数目，若没有删除任何行，则返回0。
     */
    @Delete("DELETE FROM department WHERE id = #{id}")
    int deleteById(@Param("id") String id);

    /**
     * 根据{@link Department}对象的值更新对应的department表中的行。
     *
     * @param department 用于更新行的Department对象。
     * @return 成功在表中的更新的行数目，若没有更新任何行，则返回0。
     */
    @Update("""
            <script>
            UPDATE department
            SET d_name = #{name},
            d_id = <if test='#{department}==null'> NULL </if> <if test='#{department}!=null'>#{department.id}</if>,
            deleted = #{deleted}
            WHERE id = #{id}
            </script>""")
    int update(Department department);
}
