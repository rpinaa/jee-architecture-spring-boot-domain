package org.example.seed.service.impl;

import org.apache.ibatis.session.RowBounds;
import org.example.seed.catalog.IssuePriority;
import org.example.seed.catalog.IssueStatus;
import org.example.seed.domain.Issue;
import org.example.seed.event.issue.*;
import org.example.seed.mapper.IssueMapper;
import org.example.seed.service.IssueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.concurrent.Future;

@Service
public class IssueServiceImpl implements IssueService {

    @Autowired
    private IssueMapper issueMapper;

    @Override
    @Async
    @Cacheable(value = "issues")
    @Transactional(isolation = Isolation.READ_COMMITTED, readOnly = true)
    public Future<CatalogIssueEvent> requestAllIssues(final RequestAllIssueEvent requestAllIssueEvent) {

        final int offset = (requestAllIssueEvent.getNumberPage() - 1) * requestAllIssueEvent.getRecordsPerPage();
        final int limit = requestAllIssueEvent.getNumberPage() * requestAllIssueEvent.getRecordsPerPage();

        List<Issue> issues = this.issueMapper.findAllIssues(new RowBounds(offset, limit));
        long total = this.issueMapper.countAllIssues();

        return new AsyncResult<>(CatalogIssueEvent.builder().issues(issues).total(total).build());
    }

    @Override
    @Async
    @CacheEvict(value = "issues", allEntries = true)
    @Transactional(isolation = Isolation.READ_COMMITTED)
    public Future<ResponseIssueEvent> createIssue(final CreateIssueEvent createIssueEvent) {

        createIssueEvent.getIssue().setStatus(IssueStatus.OPEN);

        if (createIssueEvent.getIssue().getPriority() == null) {
            createIssueEvent.getIssue().setPriority(IssuePriority.MEDIUM);
        }

        this.issueMapper.createIssue(createIssueEvent);

        return new AsyncResult<>(null);
    }

    @Override
    @Async
    @Cacheable(value = "issues")
    @Transactional(isolation = Isolation.READ_COMMITTED, readOnly = true)
    public Future<ResponseIssueEvent> requestIssue(final RequestIssueEvent requestIssueEvent) {

        final Issue issue = this.issueMapper.findIssueById(requestIssueEvent);

        return new AsyncResult<>(ResponseIssueEvent.builder().issue(issue).build());
    }

    @Override
    @Async
    @CacheEvict(value = "issues", allEntries = true)
    @Transactional(isolation = Isolation.READ_COMMITTED)
    public Future<ResponseIssueEvent> updateIssue(final UpdateIssueEvent updateIssueEvent) {

        this.issueMapper.saveIssue(updateIssueEvent);

        return new AsyncResult<>(null);
    }

    @Override
    @Async
    @CacheEvict(value = "issues", allEntries = true)
    @Transactional(isolation = Isolation.READ_COMMITTED)
    public void deleteIssue(final DeleteIssueEvent deleteIssueEvent) {

        this.issueMapper.deleteIssue(deleteIssueEvent);
    }
}
