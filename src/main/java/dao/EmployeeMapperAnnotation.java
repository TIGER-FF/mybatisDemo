package dao;

import bean.Employee;
import org.apache.ibatis.annotations.Select;

public interface EmployeeMapperAnnotation {
    @Select("select * from tab1_employee where id = #{id}")
    public Employee getEmployeeById(int id);
}
