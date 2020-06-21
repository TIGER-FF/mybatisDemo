package bean;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.apache.ibatis.type.Alias;

@Getter
@Setter
@ToString
//@Alias("emp")
public class Employee {
    private Integer id;
    private String lastName;
    private String gender;
    private String email;
}
