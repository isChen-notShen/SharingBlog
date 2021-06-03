package group.blog.dao.typeHandler;

import group.blog.permission.Role;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author Mr.Chen
 **/
public class RoleTypeHandler extends BaseTypeHandler<Role> {

    public RoleTypeHandler(Class<Role> type){
        if (type == null){
            throw new IllegalArgumentException("Type argument cannot be null");
        }
    }

    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, Role parameter, JdbcType jdbcType) throws SQLException {
        ps.setString(i, parameter.value());
    }

    @Override
    public Role getNullableResult(ResultSet rs, String columnName) throws SQLException {
        String role = rs.getString(columnName);
        if (rs.wasNull()){
            return null;
        }else {
            return Role.getRole(role);
        }
    }

    @Override
    public Role getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        String role = rs.getString(columnIndex);
        if (rs.wasNull()){
            return null;
        }else {
            return Role.getRole(role);
        }
    }

    @Override
    public Role getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        String role = cs.getString(columnIndex);
        if (cs.wasNull()){
            return null;
        }else {
            return Role.getRole(role);
        }
    }

}
