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
    @Async
    @CacheEvict(value = "issues", allEntries = true)
    @Transactional(isolation = Isolation.READ_COMMITTED)
    public Future<ResponseIssueEvent> createIssue(final CreateIssueEvent createIssueEvent) {

        this.logger.info("> create");

        createIssueEvent.getIssue().setStatus(IssueStatus.OPEN);

        if (createIssueEvent.getIssue().getPriority() == null) {
            createIssueEvent.getIssue().setPriority(IssuePriority.MEDIUM);
        }

        this.issueMapper.createIssue(createIssueEvent);

        this.logger.info("< create");

        return new AsyncResult<>(null);
    }

    @Override
    @Async
    @Cacheable(value = "issues")
    @Transactional(isolation = Isolation.READ_COMMITTED, readOnly = true)
    public Future<ResponseIssueEvent> requestIssue(final RequestIssueEvent requestIssueEvent) {

        this.logger.info("> find");

        final Issue issue = this.issueMapper.findIssueById(requestIssueEvent);

        this.logger.info("< find");

        return new AsyncResult<>(ResponseIssueEvent.builder().issue(issue).build());
    }

    @Override
    @Async
    @CacheEvict(value = "issues", allEntries = true)
    @Transactional(isolation = Isolation.READ_COMMITTED)
    public Future<ResponseIssueEvent> updateIssue(final UpdateIssueEvent updateIssueEvent) {

        this.logger.info("> update");

        this.issueMapper.saveIssue(updateIssueEvent);

        this.logger.info("< update");

        return new AsyncResult<>(null);
    }

    @Override
    @Async
    @CacheEvict(value = "issues", allEntries = true)
    @Transactional(isolation = Isolation.READ_COMMITTED)
    public void deleteIssue(final DeleteIssueEvent deleteIssueEvent) {

        this.logger.info("> delete");

        this.issueMapper.deleteIssue(deleteIssueEvent);

        this.logger.info("< delete");
    }
}
