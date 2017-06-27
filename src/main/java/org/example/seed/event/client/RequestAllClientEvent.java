package org.example.seed.event.client;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by PINA on 22/05/2017.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@XmlRootElement
public class RequestAllClientEvent {

    private int page;
    private int limit;
}
