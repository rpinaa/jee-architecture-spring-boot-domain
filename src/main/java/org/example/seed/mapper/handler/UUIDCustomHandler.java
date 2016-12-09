package org.example.seed.mapper.handler;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedTypes;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

/**
 * Created by Ricardo Pina Arellano on 08/12/2016.
 */
@MappedTypes(UUID.class)
public class UUIDCustomHandler extends BaseTypeHandler<UUID> {

    @Override
    public void setNonNullParameter(final PreparedStatement ps, final int i, final UUID parameter, final JdbcType jdbcType) throws SQLException {
        ps.setString(i, parameter.toString());
    }

    @Override
    public UUID getNullableResult(final ResultSet rs, final String columnName) throws SQLException {
        return UUID.fromString(rs.getString(columnName));
    }

    @Override
    public UUID getNullableResult(final ResultSet rs, final int columnIndex) throws SQLException {
        return UUID.fromString(rs.getString(columnIndex));
    }

    @Override
    public UUID getNullableResult(final CallableStatement cs, final int columnIndex) throws SQLException {
        return UUID.fromString(cs.getString(columnIndex));
    }
}