package mybatisTest;

import bean.Employee;
import dao.EmployeeMapper;
import dao.EmployeeMapperAnnotation;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;

public class test1 {
    /**
     * 这个是以前的写法，现在的mybatis写法应该是面向接口的编程，详情见demo2
     * 1、通过全局的xml配置获取sqlSessionFactory
     * 2、然后就可以从SqlSessionFactory中通过openSession获取到SqlSession（这就相当于一个sql会话）
     */
    @Test
    public void demo1() throws IOException {
        String resource = "mybatis_config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession sqlSession = sqlSessionFactory.openSession();
        try {
            //里面有两个参数，第一个是标识符，就是你在Mapper中写的selectId,第二个参数为你在sql语句中出入的参数
            Employee employee = sqlSession.selectOne("selectEmp", 1);
            System.out.print(employee.toString());
        }finally {
            sqlSession.close();
        }

    }

    /**
     * 这个是面向接口的编程
     */
    @Test
    public void test2() throws IOException {
        //1、获取到sqlSessionFactory
        String path="mybatis_config.xml";
        InputStream isr=Resources.getResourceAsStream(path);
        SqlSessionFactory sqlSessionFactory=new SqlSessionFactoryBuilder().build(isr);
        //2、获取sqlSession
        SqlSession sqlSession = sqlSessionFactory.openSession();
        //3、获取到接口的代理对象
        EmployeeMapper mapper = sqlSession.getMapper(EmployeeMapper.class);
        //4、用mapper进行数据库的操作
        //下面采用try--finally就是说sqlSession必须关闭
        try {
            Employee employee = mapper.getEmployeeById(1);
            System.out.print(employee.toString());
        }finally {
            sqlSession.close();
        }

    }
    @Test
    public void test3() throws IOException {
        //1、获取到sqlSessionFactory
        String path="mybatis_config.xml";
        InputStream ins=Resources.getResourceAsStream(path);
        SqlSessionFactory sqlSessionFactory=new SqlSessionFactoryBuilder().build(ins);
        //2、获取sqlSession
        SqlSession sqlSession = sqlSessionFactory.openSession();
        //3、获取接口的代理对象
        EmployeeMapperAnnotation mapper = sqlSession.getMapper(EmployeeMapperAnnotation.class);
        try {
            Employee employee = mapper.getEmployeeById(1);
            System.out.print(employee);
        }finally {
            //关闭
            sqlSession.close();
        }
    }
}
