package org.example.seed.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.example.seed.catalog.IssuePriority;
import org.example.seed.catalog.IssueStatus;
import org.example.seed.catalog.IssueType;
import org.example.seed.group.issue.IssueCreateGroup;
import org.example.seed.group.issue.IssueUpdateGroup;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Issue extends Momentum {

    @NotNull(groups = {IssueUpdateGroup.class})
    private Long id;

    @Size(min = 3, max = 80, groups = {IssueCreateGroup.class, IssueUpdateGroup.class})
    @NotEmpty(groups = {IssueCreateGroup.class, IssueUpdateGroup.class})
    private String title;

    @Size(min = 3, max = 150, groups = {IssueCreateGroup.class, IssueUpdateGroup.class})
    @NotEmpty(groups = {IssueCreateGroup.class, IssueUpdateGroup.class})
    private String description;

    @NotNull(groups = {IssueCreateGroup.class, IssueUpdateGroup.class})
    private IssueType type;

    @NotNull(groups = {IssueUpdateGroup.class})
    private IssuePriority priority;

    @NotNull(groups = {IssueUpdateGroup.class})
    private IssueStatus status;
}
