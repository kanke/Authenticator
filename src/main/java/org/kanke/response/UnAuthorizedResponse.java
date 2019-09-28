package org.kanke.response;

import lombok.ToString;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@ToString
public class UnAuthorizedResponse extends StandardResponse {

    public UnAuthorizedResponse(int status, String message) {
        super(status, message);
    }

}
