package com.example.greeting.dao;

import com.example.greeting.dto.EmployeeDto;
import com.example.greeting.dto.PostDto;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.dao.DataAccessException;
import org.springframework.data.repository.query.Param;

import java.util.List;

@Mapper
public interface EmployeeDao {

    @Insert("insert into employee (user_id, user_pw, employee_id, user_name, r_num, tel, address, email, gender, department, position) "
            + "values (#{user_id}, #{user_pw}, #{employee_id}, #{user_name}, #{r_num}, #{tel}, #{address}, #{email}, #{gender}, #{department}, #{position})")
    boolean insertEmployee(EmployeeDto dto) throws DataAccessException;

    @Select("select count(*) from employee where user_id=#{user_id}")
    int checkId(@Param("user_id") String user_id) throws DataAccessException;

    // 로그인은 security로 해서 위에 수식과 조금 다름 (회원정보 요청할때도 사용함)  // 메서드랑 클래스는 하나에 하나의 기능만
    @Select("select * from employee where user_id=#{user_id}")
    public EmployeeDto getByUserId(@Param("user_id") String user_id) throws DataAccessException;

    @Select("select employee_id, user_name, hire_date from employee ")
    List<EmployeeDto> selectEmployeeList() throws DataAccessException;

    // 검색된 사원 목록 조회 (이름, 부서, 직책 등으로 검색)
    @Select("SELECT employee_id, user_name, hire_date FROM employee " +
            "WHERE user_name LIKE CONCAT('%', #{keyword}, '%') " +
            "OR department LIKE CONCAT('%', #{keyword}, '%') " +
            "OR position LIKE CONCAT('%', #{keyword}, '%')")
    List<EmployeeDto> searchEmployees(@Param("keyword") String keyword) throws DataAccessException;

    @Select("select employee_id, user_name, r_num, tel, address, email, gender," +
            " department, position, hire_date, resignation_date, employment, permit" +
            " from employee where employee_id = #{employee_id}")
    EmployeeDto selectEmployee(@Param("employee_id") int employee_id) throws DataAccessException;

    @Update("UPDATE employee SET user_name = #{user_name}, r_num = #{r_num}, tel = #{tel}, " +
            "address = #{address}, email = #{email}, gender = #{gender}, department = #{department}, " +
            "position = #{position}, hire_date = #{hire_date}, resignation_date = #{resignation_date}, " +
            "employment = #{employment}, permit = #{permit} WHERE employee_id = #{employee_id}")
    void updateEmployee(EmployeeDto dto) throws DataAccessException;

    @Select("SELECT user_id, user_name, department, position FROM employee WHERE user_id = #{user_id}")
    EmployeeDto findEmployeeById(String user_id);

    @Select("SELECT employee_id, user_name, department FROM employee WHERE user_id = #{userId}")
    EmployeeDto findEmployeeId(@Param("userId") String userId);

    // 검색 기능 (concat을 안해주면 문자로 인식해 오류가 난다.)
    @Select("SELECT * FROM Employee " +
            "WHERE (title LIKE CONCAT('%', #{keyword}, '%') OR content LIKE CONCAT('%', #{keyword}, '%')) " +
            "ORDER BY employee_id DESC LIMIT #{offset}, #{cnt}")
    List<EmployeeDto> selectPostListByKeyword(@Param("offset") int offset,
                                          @Param("cnt") int cnt,
                                          @Param("keyword") String keyword) throws DataAccessException;

    @Select("SELECT COUNT(*) FROM post WHERE" +
            "(title LIKE CONCAT('%',#{keyword},'%') OR content LIKE CONCAT('%',#{keyword},'%'))")
    int selectPostCntByKeyword(@Param("keyword") String keyword) throws DataAccessException;

    @Select("select post_no,title,user_id,create_date,hit_cnt FROM post")
    List<EmployeeDto> selectNotice() throws DataAccessException;

}
