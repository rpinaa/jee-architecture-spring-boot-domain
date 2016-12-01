package org.example.seed.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.example.seed.catalog.IssuePriority;
import org.example.seed.catalog.IssueStatus;
import org.example.seed.catalog.IssueType;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class Issue extends Momentum {

    private static final long serialVersionUID = 4672563797607270069L;

    private Long id;
    private String title;
    private String description;
    private IssueType type;
    private IssuePriority priority;
    private IssueStatus status;
}
