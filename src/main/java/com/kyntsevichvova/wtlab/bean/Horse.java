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
@JacksonXmlRootElement(localName = "horse")
public class Horse extends BaseEntity {

    private String name;

    private Integer wins;

}
