package org.example.seed.domain;

import lombok.Data;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.Date;

/**
 * Created by Ricardo Pina Arellano on 24/11/2016.
 */
@Data
@XmlRootElement
public class Momentum {
    private Date registerDate;
    private Date changeDate;
}
