package com.kyntsevichvova.wtlab.bean;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@EqualsAndHashCode(callSuper = false)
@ToString
@NoArgsConstructor
@JacksonXmlRootElement(localName = "bet")
public class Bet extends BaseEntity {

    private String user;

    private Long raceId;

    private Long horseId;

    private Integer sum;

}
