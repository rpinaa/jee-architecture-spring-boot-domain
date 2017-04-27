package org.example.seed.mapper;

import org.apache.ibatis.annotations.*;
import org.apache.ibatis.session.RowBounds;
import org.example.seed.domain.Issue;
import org.example.seed.event.issue.CreateIssueEvent;
import org.example.seed.event.issue.DeleteIssueEvent;
import org.example.seed.event.issue.RequestIssueEvent;
import org.example.seed.event.issue.UpdateIssueEvent;

import java.util.List;

/**
 * Created by Ricardo Pina Arellano on 30/11/2016.
 */
@Mapper
public interface IssueMapper {

    @Insert("INSERT INTO ISSUE" +
            "(id, title, description, type, priority, status, register_date, change_date)" +
            "VALUES(#{issue.id}, #{issue.title}, #{issue.description}, #{issue.type}, " +
            "#{issue.priority}, #{issue.status}, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP)")
    @Options(useGeneratedKeys = true)
    int createIssue(final CreateIssueEvent createIssueEvent);

    @Select("SELECT COUNT(*) FROM ISSUE")
    long countAllIssues();

    @Select("SELECT * FROM ISSUE")
    List<Issue> findAllIssues(final RowBounds rb);

    @Select("SELECT * FROM ISSUE WHERE id = #{requestIssueEvent.id}")
    Issue findIssueById(@Param("requestIssueEvent") final RequestIssueEvent requestIssueEvent);

    @Update("UPDATE ISSUE SET" +
            "description = #{issue.description}, " +
            "type = #{issue.type}, " +
            "priority = #{issue.priority}, " +
            "status = #{issue.status}, " +
            "change_date = CURRENT_TIMESTAMP" +
            "WHERE id = #{issue.id, javaType=java.util.UUID}")
    int saveIssue(final UpdateIssueEvent updateIssueEvent);

    @Delete("DELETE FROM ISSUE WHERE id = #{id, javaType=java.util.UUID}")
    int deleteIssue(final DeleteIssueEvent id);
}
