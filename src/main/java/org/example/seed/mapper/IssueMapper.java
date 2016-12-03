package org.example.seed.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.example.seed.domain.Issue;

import java.util.List;

/**
 * Created by Ricardo Pina Arellano on 30/11/2016.
 */
@Mapper
public interface IssueMapper {

    Issue createIssue(final Issue issue);

    List<Issue> findAllIssues();

    Issue findIssueById(final Long id);

    Issue saveIssue(final Issue issue);

    void deleteIssue(final Long id);
}
