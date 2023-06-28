package domain;

import exception.IllegalPositionDescriptionException;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PositionHandler extends BaseTypeHandler<Position> {
    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, Position parameter, JdbcType jdbcType) throws SQLException {
        ps.setString(i, parameter.getDescription());
    }

    @Override
    public Position getNullableResult(ResultSet rs, String columnName) throws SQLException {
        String description = rs.getString(columnName);
        return Position.getByDescription(description);
    }

    @Override
    public Position getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        String description = rs.getString(columnIndex);
        return Position.getByDescription(description);
    }

    @Override
    public Position getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        String description = cs.getString(columnIndex);
        return Position.getByDescription(description);
    }
}
