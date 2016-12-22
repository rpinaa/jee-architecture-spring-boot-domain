package org.example.seed.event.issue;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.seed.domain.Issue;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

/**
 * Created by Ricardo Pina Arellano on 30/11/2016.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@XmlRootElement
public class CatalogIssueEvent {

    private Long total;
    private List<Issue> issues;
}
