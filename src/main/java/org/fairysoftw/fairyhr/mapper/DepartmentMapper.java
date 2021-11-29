package org.fairysoftw.fairyhr.mapper;

import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;
import org.fairysoftw.fairyhr.mapper.typeHandler.DatetimeTypeHandler;
import org.fairysoftw.fairyhr.model.Department;
import org.fairysoftw.fairyhr.model.User;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface DepartmentMapper {
    @Select("SELECT * FROM department")
    @Results({
            @Result(id = true, property = "id", column = "id"),
            @Result(property = "department", column = "d_id", one = @One(select = "org.fairysoftw.fairyhr.mapper.DepartmentMapper.selectById", fetchType = FetchType.LAZY)),
            @Result(property = "managers", column = "id", many = @Many(select = "org.fairysoftw.fairyhr.mapper.DepartmentManagerMapper.selectByDepartmentId", fetchType = FetchType.LAZY)),
            @Result(property = "users", column = "id", many = @Many(select = "org.fairysoftw.fairyhr.mapper.DepartmentUserMapper.selectByDepartmentId", fetchType = FetchType.LAZY)),
            @Result(property = "leaveRequests", column = "id", many = @Many(select = "org.fairysoftw.fairyhr.mapper.DepartmentLeaveRequestMapper.selectByDepartmentId", fetchType = FetchType.LAZY)),
    })
    List<Department> selectAll();

    @Select("SELECT * FROM department " +
            "WHERE id = #{id}")
    @Results({
            @Result(id = true, property = "id", column = "id"),
            @Result(property = "department", column = "d_id", one = @One(select = "org.fairysoftw.fairyhr.mapper.DepartmentMapper.selectById", fetchType = FetchType.LAZY)),
            @Result(property = "managers", column = "id", many = @Many(select = "org.fairysoftw.fairyhr.mapper.DepartmentManagerMapper.selectByDepartmentId", fetchType = FetchType.LAZY)),
            @Result(property = "users", column = "id", many = @Many(select = "org.fairysoftw.fairyhr.mapper.DepartmentUserMapper.selectByDepartmentId", fetchType = FetchType.LAZY)),
            @Result(property = "leaveRequests", column = "id", many = @Many(select = "org.fairysoftw.fairyhr.mapper.DepartmentLeaveRequestMapper.selectByDepartmentId", fetchType = FetchType.LAZY)),
    })
    Department selectById(@Param("id") String id);

    @Insert("<script>" +
            "INSERT INTO department(id, d_name, d_id, deleted) " +
            "VALUES(#{id}, #{name}, " +
            "<if test='department=null'> NULL </if> <if test='department!=null'>#{department.id}</if>, " +
            "#{deleted})" +
            "</script>")
    int insert(@Param("department") Department department);

    @Delete("DELETE FROM department WHERE id = #{id}")
    int deleteById(@Param("id") String id);

    @Update("<script>" +
            "UPDATE user " +
            "SET d_name = #{name}, " +
            "d_id = <if test='department=null'> NULL </if> <if test='department!=null'>#{department.id}</if>, " +
            "deleted = #{deleted}, " +
            "WHERE id = #{id}" +
            "</script>")
    int update(@Param("user") Department department);
}
