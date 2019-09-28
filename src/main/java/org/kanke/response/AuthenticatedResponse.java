package org.kanke.response;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@Getter @Setter
@ToString
public class AuthenticatedResponse extends StandardResponse {

        private String privateKey;

        public AuthenticatedResponse(int status, String message, String privateKey) {
            super(status, message);
            this.privateKey = privateKey;
        }

//        @Override
//        public String toString() {
//            return "AuthorizationResponse{" +
//                    super.toString() +
//                    "privateKey='" + privateKey + '\'' +
//                    '}';
//        }
    }
