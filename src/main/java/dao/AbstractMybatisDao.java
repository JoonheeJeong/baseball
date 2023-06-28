package dao;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

public abstract class AbstractMybatisDao {

    protected SqlSessionFactory sqlSessionFactory;
    protected SqlSession session;

    public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
        this.sqlSessionFactory = sqlSessionFactory;
        session = sqlSessionFactory.openSession();
    }

    public void rollback() {
        session.rollback();
    }

    protected void manageNewSession() {
        session.close();
        session = sqlSessionFactory.openSession();
    }
}
