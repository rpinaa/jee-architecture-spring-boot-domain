package org.example.seed.service;

import org.example.seed.event.issue.*;

import java.util.concurrent.Future;

public interface IssueService {

    Future<CatalogIssueEvent> requestAllIssues();

    Future<ResponseIssueEvent> createIssue(final CreateIssueEvent createIssueEvent);

    Future<ResponseIssueEvent> requestIssue(final RequestIssueEvent requestIssueEvent);

    Future<ResponseIssueEvent> updateIssue(final UpdateIssueEvent updateIssueEvent);

    void deleteIssue(final DeleteIssueEvent deleteIssueEvent);
}
