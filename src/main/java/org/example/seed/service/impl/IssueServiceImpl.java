package org.example.seed.service.impl;

import org.example.seed.catalog.IssuePriority;
import org.example.seed.catalog.IssueStatus;
import org.example.seed.domain.Issue;
import org.example.seed.event.issue.*;
import org.example.seed.mapper.IssueMapper;
import org.example.seed.service.IssueService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private IssueMapper issueMapper;

    @Override
    @Async
    @Cacheable(value = "issues")
    @Transactional(isolation = Isolation.READ_COMMITTED, readOnly = true)
    public Future<CatalogIssueEvent> requestAllIssues() {

        this.logger.info("> findAll");

        List<Issue> issues = this.issueMapper.findAllIssues();

        this.logger.info("< findAll");

        return new AsyncResult<>(CatalogIssueEvent.builder().issues(issues).build());
    }

    @Override
    @CacheEvict(value = "issues", allEntries = true)
    @Transactional(isolation = Isolation.READ_COMMITTED)
    public ResponseIssueEvent createIssue(final CreateIssueEvent createIssueEvent) {

        this.logger.info("> create");

        Issue issue = createIssueEvent.getIssue();

        issue.setStatus(IssueStatus.OPEN);

        if (issue.getPriority() == null) {
            issue.setPriority(IssuePriority.MEDIUM);
        }

        issue = this.issueMapper.createIssue(issue);

        this.logger.info("< create");

        return ResponseIssueEvent.builder().issue(issue).build();
    }

    @Override
    @Async
    @Cacheable(value = "issues")
    @Transactional(isolation = Isolation.READ_COMMITTED, readOnly = true)
    public Future<ResponseIssueEvent> requestIssue(final RequestIssueEvent requestIssueEvent) {

        this.logger.info("> find");

        final Issue issue = this.issueMapper.findIssueById(requestIssueEvent.getId());

        this.logger.info("< find");

        return new AsyncResult<>(ResponseIssueEvent.builder().issue(issue).build());
    }

    @Override
    @CacheEvict(value = "issues", allEntries = true)
    @Transactional(isolation = Isolation.READ_COMMITTED)
    public ResponseIssueEvent updateIssue(final UpdateIssueEvent updateIssueEvent) {

        this.logger.info("> update");

        final Issue updatedIssue = this.issueMapper.saveIssue(updateIssueEvent.getIssue());

        this.logger.info("< update");

        return ResponseIssueEvent.builder().issue(updatedIssue).build();
    }

    @Override
    @CacheEvict(value = "issues", allEntries = true)
    @Transactional(isolation = Isolation.READ_COMMITTED)
    public void deleteIssue(final DeleteIssueEvent deleteIssueEvent) {

        this.logger.info("> delete");

        this.issueMapper.deleteIssue(deleteIssueEvent.getId());

        this.logger.info("< delete");
    }
}
