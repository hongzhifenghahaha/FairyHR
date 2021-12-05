package org.fairysoftw.fairyhr.mapper;

import org.apache.ibatis.annotations.*;
import org.fairysoftw.fairyhr.model.LeaveRequest;
import org.fairysoftw.fairyhr.model.LeaveRequestType;

import java.util.List;

/**
 * 映射类,实现{@link LeaveRequestType}类与数据库中的leave_request_type表的映射。
 *
 * @version 1.0
 */
@Mapper
public interface LeaveRequestTypeMapper {
    /**
     * 选出leave_request_type表中的所有行，并映射成{@link LeaveRequestType}对象列表。
     *
     * @return leave_request_type表中所有请假申请。
     */
    @Select("SELECT name FROM leave_request_type")
    List<String> selectAll();

    /**
     * 把{@link LeaveRequestType}对象插入到leave_request_type表中。
     *
     * @param name 要插入的{@link LeaveRequestType}的name。
     * @return 成功插入到表中的行的数目，若插入失败或插入被忽略，则返回0。
     */
    @Insert("INSERT IGNORE INTO leave_request_type(name) VALUES(#{name})")
    int insert(@Param("name")String name);

    /**
     * 删除leave_request_type表中name=#{name}的行。
     *
     * @param name 要删除的请假类型的name。
     * @return 成功从表中的删除的行的数目，若没有删除任何行，则返回0。
     */
    @Delete("DELETE FROM leave_request_type WHERE name = #{name}")
    int delete(@Param("name") String name);
}
