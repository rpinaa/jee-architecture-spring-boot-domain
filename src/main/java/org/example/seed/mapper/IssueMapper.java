package org.example.seed.mapper;

import org.apache.ibatis.annotations.Mapper;
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

    int createIssue(final CreateIssueEvent createIssueEvent);

    long countAllIssues();

    List<Issue> findAllIssues(final RowBounds rb);

    Issue findIssueById(final RequestIssueEvent requestIssueEvent);

    int saveIssue(final UpdateIssueEvent updateIssueEvent);

    int deleteIssue(final DeleteIssueEvent id);
}
