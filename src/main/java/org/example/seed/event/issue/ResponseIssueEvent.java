package org.example.seed.event.issue;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.seed.domain.Issue;

/**
 * Created by Ricardo Pina Arellano on 30/11/2016.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ResponseIssueEvent {
    private Issue issue;
}
