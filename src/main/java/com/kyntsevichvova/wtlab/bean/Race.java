package com.kyntsevichvova.wtlab.bean;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;

@Data
@EqualsAndHashCode(callSuper = false)
@ToString
@NoArgsConstructor
@JacksonXmlRootElement(localName = "race")
public class Race extends BaseEntity {

    private Date date;

    private Long winnerId;

}
