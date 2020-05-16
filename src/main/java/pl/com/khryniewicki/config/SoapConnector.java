package pl.com.khryniewicki.config;

import org.springframework.stereotype.Service;
import org.springframework.ws.client.core.WebServiceTemplate;
import org.springframework.ws.transport.WebServiceConnection;
import org.springframework.ws.transport.support.TransportUtils;

import java.io.IOException;
import java.net.URI;

@Service
public class SoapConnector extends WebServiceTemplate {
    private WebServiceConnection connection;

    public void connect() throws IOException {

        WebServiceConfig webServiceConfig = new WebServiceConfig();
        setMarshaller(webServiceConfig.marshaller());
        setUnmarshaller(webServiceConfig.marshaller());
        URI uri = URI.create("http://localhost:8080/ws");
        connection = createConnection(uri);

    }


    public Object sendAndReceive(String url, Object request) {
        return marshalSendAndReceive(url, request);
    }

    public void disconnect() {
        TransportUtils.closeConnection(connection);
    }
}
