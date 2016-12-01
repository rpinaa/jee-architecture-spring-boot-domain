package org.example.seed.mapper;

import org.apache.ibatis.annotations.*;
import org.example.seed.domain.Issue;

import java.util.List;

/**
 * Created by Ricardo Pina Arellano on 30/11/2016.
 */
@Mapper
public interface IssueMapper {

    @Insert("INSERT INTO ISSUE(title, description, type, priority, status, register_date, change_date) values(#{title}, #{description}, #{type}, #{priority}, #{status}, #{register_date}, #{change_date})")
    @SelectKey(statement = "call identity()", keyProperty = "id", before = false, resultType = Issue.class)
    Issue createIssue(@Param("issue") final Issue issue);

    @Select("SELECT * FROM ISSUE")
    List<Issue> findAllIssues();

    @Select("SELECT * FROM ISSUE WHERE id = #{id}")
    Issue findIssueById(@Param("id") final Long id);

    @Update("UPDATE ISSUE SET description = #{description}, type = #{type}, priority = #{priority}, status = #{status}, change_date = #{change_date} WHERE id = #{id}")
    Issue saveIssue(@Param("issue") final Issue issue);

    @Select("DELETE FROM ISSUE WHERE id = #{id}")
    void deleteIssue(@Param("id") final Long id);
}
