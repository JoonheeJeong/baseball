package domain;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ReasonHandler extends BaseTypeHandler<Reason>{
    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, Reason parameter, JdbcType jdbcType) throws SQLException {
        ps.setString(i, parameter.getDescription());
    }

    @Override
    public Reason getNullableResult(ResultSet rs, String columnName) throws SQLException {
        String description = rs.getString(columnName);
        return Reason.getByDescription(description);
    }

    @Override
    public Reason getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        String description = rs.getString(columnIndex);
        return Reason.getByDescription(description);
    }

    @Override
    public Reason getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        String description = cs.getString(columnIndex);
        return Reason.getByDescription(description);
    }
}

