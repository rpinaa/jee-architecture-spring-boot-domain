package org.example.seed.event.issue;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by Ricardo Pina Arellano on 20/12/2016.
 */
@XmlRootElement
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RequestAllIssueEvent {

    private int numberPage;
    private int recordsPerPage;
}
