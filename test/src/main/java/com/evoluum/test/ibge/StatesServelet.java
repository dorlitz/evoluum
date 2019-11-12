package com.evoluum.test.ibge;

import com.evoluum.test.dto.FederationDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpStatusCodeException;

import java.io.*;
import java.util.zip.GZIPInputStream;


@Service
public class StatesServelet {

    @Autowired
    protected RestTemplateBuilder restTemplateBuilder;

    private static final Logger logger = LoggerFactory.getLogger(StatesServelet.class);

    private final String messageLogFail = "Failed to get states | Details: {}";

    public FederationDto[] getStates() {

        byte[] response;
        ObjectMapper mapper = new ObjectMapper();

        try {
            response = restTemplateBuilder.build().exchange(IbgeUrlEnum.URL_STATES.getUrlValue(),
                    HttpMethod.GET, null,  byte[].class).getBody();

            InputStream inputStream = new GZIPInputStream(new ByteArrayInputStream(response));

            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));

            FederationDto[] federationDto = mapper.readValue(bufferedReader, FederationDto[].class);

            return federationDto;
        } catch (HttpClientErrorException e) {
            String message = messageLogFail.concat(" - {}");
            logger.error(messageLogFail, e.getMessage(), e.getResponseBodyAsString());
            return null;
        } catch (HttpStatusCodeException e) {
            logger.error(messageLogFail, e.getResponseBodyAsString());
            throw e;
        }
        catch (ClassCastException e) {
            throw  e;
        } catch (Exception e) {
            logger.error(messageLogFail, e.getMessage());
            return null;
        }
    }
}
