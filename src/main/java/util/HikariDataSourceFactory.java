package util;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.apache.ibatis.datasource.DataSourceFactory;

import javax.sql.DataSource;
import java.util.Properties;

public class HikariDataSourceFactory implements DataSourceFactory {

    private final HikariConfig config = new HikariConfig();

    @Override
    public void setProperties(Properties props) {
        config.setDriverClassName(props.getProperty("driver"));
        config.setJdbcUrl(props.getProperty("url"));
        config.setUsername(props.getProperty("username"));
        config.setPassword(props.getProperty("password"));
        config.addDataSourceProperty("cachePrepStmts", props.getProperty("cachePrepStmts"));
        config.addDataSourceProperty("prepStmtCacheSize", props.getProperty("prepStmtCacheSize"));
        config.addDataSourceProperty("prepStmtCacheSqlLimit", props.getProperty("prepStmtCacheSqlLimit"));
    }

    @Override
    public DataSource getDataSource() {
        return new HikariDataSource(config);
    }
}

