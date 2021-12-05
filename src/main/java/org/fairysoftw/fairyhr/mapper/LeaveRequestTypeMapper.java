package org.fairysoftw.fairyhr.mapper;

import org.apache.ibatis.annotations.*;
import org.fairysoftw.fairyhr.model.LeaveRequest;
import org.fairysoftw.fairyhr.model.LeaveRequestType;

import java.util.List;

@Mapper
public interface LeaveRequestTypeMapper {
    @Select("SELECT name FROM leave_request_type")
    List<String> selectAll();

    @Insert("INSERT IGNORE INTO leave_request_type(name) VALUES(#{name})")
    int insert(@Param("name")String name);

    @Delete("DELETE FROM leave_request_type WHERE name = #{name}")
    int delete(@Param("name") String name);
}
