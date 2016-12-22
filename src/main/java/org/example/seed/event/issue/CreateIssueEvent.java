package org.example.seed.event.issue;

import lombok.*;
import org.example.seed.domain.Issue;
import org.example.seed.event.CreateEvent;
import org.example.seed.group.issue.IssueCreateGroup;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by Ricardo Pina Arellano on 30/11/2016.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@XmlRootElement
public class CreateIssueEvent extends CreateEvent {

    @Valid
    @NotNull(groups = {IssueCreateGroup.class})
    private Issue issue;
}
