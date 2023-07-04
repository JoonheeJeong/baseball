package dao;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

public abstract class AbstractMybatisDao {

    private SqlSessionFactory sqlSessionFactory;
    protected SqlSession session;

    public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
        this.sqlSessionFactory = sqlSessionFactory;
        session = sqlSessionFactory.openSession();
    }

    public void rollback() {
        session.rollback();
    }

    public void commit() {
        session.commit();
    }

    protected void manageNewSession() {
        session.close();
        session = sqlSessionFactory.openSession();
    }
}
