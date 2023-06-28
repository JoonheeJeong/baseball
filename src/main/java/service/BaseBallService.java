package service;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.h2.jdbc.JdbcSQLIntegrityConstraintViolationException;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Optional;

public interface BaseBallService {
    void register(HashMap<String, String> map);

    void show(HashMap<String, String> map);

    default SqlSessionFactory get() throws IOException {
        String resource = "mapper/mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        return new SqlSessionFactoryBuilder().build(inputStream);
    }
}
