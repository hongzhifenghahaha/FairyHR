package org.fairysoftw.fairyhr.mapper;

import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;
import org.fairysoftw.fairyhr.mapper.typeHandler.DatetimeTypeHandler;
import org.fairysoftw.fairyhr.model.Department;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
public interface DepartmentMapper {
    @Select("SELECT * FROM department")
    @Results({
            @Result(id = true, property = "id", column = "id"),
            @Result(property = "managers", column = "id", many = @Many(select = "org.fairysoftw.fairyhr.mapper.DepartmentManagerMapper.selectByDepartmentId", fetchType = FetchType.LAZY)),
            @Result(property = "users", column = "id", many = @Many(select = "org.fairysoftw.fairyhr.mapper.DepartmentUserMapper.selectByDepartmentId", fetchType = FetchType.LAZY)),
            @Result(property = "leaveRequests", column = "id", many = @Many(select = "org.fairysoftw.fairyhr.mapper.DepartmentLeaveRequestMapper.selectByDepartmentId", fetchType = FetchType.LAZY)),
    })
    List<Department> selectAll();
}
