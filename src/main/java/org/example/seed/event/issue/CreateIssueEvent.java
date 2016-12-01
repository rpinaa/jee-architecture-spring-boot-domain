package org.example.seed.event.issue;

import lombok.*;
import org.example.seed.domain.Issue;
import org.example.seed.event.CreateEvent;

/**
 * Created by Ricardo Pina Arellano on 30/11/2016.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class CreateIssueEvent extends CreateEvent {
    private Issue issue;
}
