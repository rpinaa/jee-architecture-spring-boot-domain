package org.example.seed.rest.impl;

import org.example.seed.event.issue.*;
import org.example.seed.group.issue.IssueCreateGroup;
import org.example.seed.group.issue.IssueUpdateGroup;
import org.example.seed.rest.IssueRest;
import org.example.seed.service.IssueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.metrics.CounterService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;

@RestController
public class IssueRestImpl implements IssueRest {

    @Autowired
    private IssueService issueService;

    @Autowired
    private CounterService counterService;

    public Callable<CatalogIssueEvent> getAllIssues(final int numberPage, final int recordsPerPage) throws ExecutionException, InterruptedException {

        this.counterService.increment("services.issues.getAllIssues.invoke");

        final RequestAllIssueEvent requestAllIssueEvent = RequestAllIssueEvent.builder()
                .numberPage(numberPage)
                .recordsPerPage(recordsPerPage)
                .build();

        final CatalogIssueEvent catalogIssueEvent = this.issueService.requestAllIssues(requestAllIssueEvent).get();

        return () -> catalogIssueEvent;
    }

    public Callable<ResponseIssueEvent> createIssue(@RequestBody @Validated(value = {IssueCreateGroup.class}) final CreateIssueEvent issueEvent) throws ExecutionException, InterruptedException {

        final ResponseIssueEvent responseIssueEvent = this.issueService.createIssue(issueEvent).get();

        return () -> responseIssueEvent;
    }

    public Callable<ResponseIssueEvent> getIssue(final UUID id) throws ExecutionException, InterruptedException {

        final RequestIssueEvent requestIssueEvent = RequestIssueEvent.builder().id(id).build();

        final ResponseIssueEvent responseIssueEvent = this.issueService.requestIssue(requestIssueEvent).get();

        return () -> responseIssueEvent;
    }

    public Callable<ResponseIssueEvent> updateIssue(@RequestBody @Validated(value = {IssueUpdateGroup.class}) final UpdateIssueEvent issueEvent) throws ExecutionException, InterruptedException {

        final ResponseIssueEvent updateIssueEvent = this.issueService.updateIssue(issueEvent).get();

        return () -> updateIssueEvent;
    }

    public void deleteIssue(final UUID id) {

        final DeleteIssueEvent deleteIssueEvent = DeleteIssueEvent.builder().id(id).build();

        this.issueService.deleteIssue(deleteIssueEvent);
    }
}
