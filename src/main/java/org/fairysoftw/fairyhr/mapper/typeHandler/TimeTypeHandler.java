package org.fairysoftw.fairyhr.mapper.typeHandler;

import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.TypeHandler;

import java.sql.*;

public class TimeTypeHandler implements TypeHandler<Long> {
    @Override
    public void setParameter(PreparedStatement ps, int i, Long parameter, JdbcType jdbcType) throws SQLException {
        if (parameter == null)
            ps.setNull(i, Types.TIME);
        else {
            var result = new Time(parameter);
            ps.setTime(i, result);
        }
    }

    @Override
    public Long getResult(ResultSet rs, String columnName) throws SQLException {
        var time = rs.getTime(columnName);
        return time.getTime();
    }

    @Override
    public Long getResult(ResultSet rs, int columnIndex) throws SQLException {
        var time = rs.getTime(columnIndex);
        return time.getTime();
    }

    @Override
    public Long getResult(CallableStatement cs, int columnIndex) throws SQLException {
        var time = cs.getTime(columnIndex);
        return time.getTime();
    }
}
