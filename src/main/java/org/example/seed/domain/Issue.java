package org.example.seed.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.example.seed.catalog.IssuePriority;
import org.example.seed.catalog.IssueStatus;
import org.example.seed.catalog.IssueType;
import org.example.seed.group.issue.IssueCreateGroup;
import org.example.seed.group.issue.IssueUpdateGroup;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.UUID;

@Data
@EqualsAndHashCode(callSuper = true)
@XmlRootElement
public class Issue extends Momentum {

    public Issue() {
        this.id = UUID.randomUUID();
    }

    @NotNull(groups = {IssueUpdateGroup.class})
    private UUID id;

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
