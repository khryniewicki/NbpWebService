### NbpWebService [PL]
Nbp WebService to aplikacja usługi sieciowej SOAP utworzona przy użyciu frameworku Spring i Hibernate. Aplikacja została zaprojektowana w celu dostarczania informacji o walutach USD, EUR i RUB.

Aby przetestować zapytania za pośrednictwem aplikacji, użyj:
\
curl --header "content-type: text/xml" -d @request.xml http://localhost:8080/ws


Wypróbuj przykładowe żądanie przy pomocy pliku request.xml:
```xml

<soapenv:Envelope xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/"
                  xmlns:gs="http://response.dto.khryniewicki.com.pl">
    <soapenv:Header/>
    <soapenv:Body>
        <gs:getCurrencyRequest>
            <gs:Currency>euro</gs:Currency>
            <gs:StartingDate>2020-02-12</gs:StartingDate>
            <gs:EndingDate>2020-05-10</gs:EndingDate>
    </gs:getCurrencyRequest>
    </soapenv:Body>
</soapenv:Envelope>
```
----

### NbpWebService [ENG]
Nbp WebService is a SOAP webservice application created using Spring and Hibernate. This app was designed to provide information about currencies USD, EUR and RUB.

To test querries via app use :
\
curl --header "content-type: text/xml" -d @request.xml http://localhost:8080/ws


Try this request example using request.xml file:
```xml

<soapenv:Envelope xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/"
                  xmlns:gs="http://response.dto.khryniewicki.com.pl">
    <soapenv:Header/>
    <soapenv:Body>
        <gs:getCurrencyRequest>
            <gs:Currency>euro</gs:Currency>
            <gs:StartingDate>2020-02-12</gs:StartingDate>
            <gs:EndingDate>2020-05-10</gs:EndingDate>
    </gs:getCurrencyRequest>
    </soapenv:Body>
</soapenv:Envelope>
```
