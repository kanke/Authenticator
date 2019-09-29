package org.kanke.response;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@Getter
@Setter
@ToString
public class StandardResponse {

    public static final int FAILURE = 0;
    public static final int SUCCESS = 1;

    private String message;
    private int status;

    public StandardResponse(int status, String message) {
        this.message = message;
        this.status = status;
    }

}