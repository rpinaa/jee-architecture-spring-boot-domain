package org.example.seed.rest.api;

import org.example.seed.event.issue.*;
import org.example.seed.group.issue.IssueCreateGroup;
import org.example.seed.group.issue.IssueUpdateGroup;
import org.example.seed.service.IssueService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.metrics.CounterService;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping(path = "/issues")
public class IssueRestController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private IssueService issueService;

    @Autowired
    private CounterService counterService;

    @RequestMapping(method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public Callable<CatalogIssueEvent> getAllIssues() throws ExecutionException, InterruptedException {

        this.logger.info("> getAllIssues");

        this.counterService.increment("services.issues.findAll.invoke");

        final CatalogIssueEvent catalogIssueEvent = this.issueService.requestAllIssues().get();

        this.logger.info("< getAllIssues");

        return () -> catalogIssueEvent;
    }

    @RequestMapping(method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public Callable<ResponseIssueEvent> createIssue(@RequestBody @Validated(value = {IssueCreateGroup.class}) final CreateIssueEvent issueEvent) throws ExecutionException, InterruptedException {

        this.logger.info("> createIssue");

        final ResponseIssueEvent responseIssueEvent = this.issueService.createIssue(issueEvent).get();

        this.logger.info("< createIssue");

        return () -> responseIssueEvent;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public Callable<ResponseIssueEvent> getIssue(@PathVariable("id") final Long id) throws ExecutionException, InterruptedException {

        this.logger.info("> getIssue");

        final RequestIssueEvent requestIssueEvent = RequestIssueEvent.builder().id(id).build();

        final ResponseIssueEvent responseIssueEvent = this.issueService.requestIssue(requestIssueEvent).get();

        this.logger.info("< getIssue");

        return () -> responseIssueEvent;
    }

    @RequestMapping(method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.OK)
    public Callable<ResponseIssueEvent> updateIssue(@RequestBody @Validated(value = {IssueUpdateGroup.class}) final UpdateIssueEvent issueEvent) throws ExecutionException, InterruptedException {

        this.logger.info("> updateIssue");

        final ResponseIssueEvent updateIssueEvent = this.issueService.updateIssue(issueEvent).get();

        this.logger.info("< updateIssue");

        return () -> updateIssueEvent;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteIssue(@PathVariable("id") final Long id) {

        this.logger.info("> deleteIssue");

        final DeleteIssueEvent deleteIssueEvent = DeleteIssueEvent.builder().id(id).build();

        this.issueService.deleteIssue(deleteIssueEvent);

        this.logger.info("< deleteIssue");
    }
}
