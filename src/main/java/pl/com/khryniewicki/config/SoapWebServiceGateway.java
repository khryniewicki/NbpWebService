package pl.com.khryniewicki.config;

import org.springframework.ws.client.core.support.WebServiceGatewaySupport;

public class SoapWebServiceGateway extends WebServiceGatewaySupport{

        public Object callWebService(String url, Object request){
            return getWebServiceTemplate().marshalSendAndReceive(url, request);
        }

    }
