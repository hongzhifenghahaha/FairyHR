package org.fairysoftw.fairyhr.mapper.typeHandler;

import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.TypeHandler;

import java.sql.*;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

public class DatetimeTypeHandler implements TypeHandler<LocalDateTime> {
    @Override
    public void setParameter(PreparedStatement ps, int i, LocalDateTime parameter, JdbcType jdbcType) throws SQLException {
        if (parameter == null)
            ps.setNull(i, Types.TIMESTAMP);
        else {
            var result = new Timestamp(parameter.toEpochSecond(ZoneOffset.ofHours(8)));
            ps.setTimestamp(i, result);
        }
    }

    @Override
    public LocalDateTime getResult(ResultSet rs, String columnName) throws SQLException {
        Timestamp result = rs.getTimestamp(columnName);
        if (result == null)
            return null;
        else
            return Instant.ofEpochMilli(result.getTime()).atZone(ZoneOffset.ofHours(8)).toLocalDateTime();
    }

    @Override
    public LocalDateTime getResult(ResultSet rs, int columnIndex) throws SQLException {
        Timestamp result = rs.getTimestamp(columnIndex);
        if (result == null)
            return null;
        else
            return Instant.ofEpochMilli(result.getTime()).atZone(ZoneOffset.ofHours(8)).toLocalDateTime();
    }

    @Override
    public LocalDateTime getResult(CallableStatement cs, int columnIndex) throws SQLException {
        Timestamp result = cs.getTimestamp(columnIndex);
        if (result == null)
            return null;
        else
            return Instant.ofEpochMilli(result.getTime()).atZone(ZoneOffset.ofHours(8)).toLocalDateTime();
    }
}
