package com.evoluum.test.ibge;

import com.evoluum.test.dto.CountyDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpStatusCodeException;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.zip.GZIPInputStream;

@Service
public class CountiesServelet {
    @Autowired
    protected RestTemplateBuilder restTemplateBuilder;

    private static final Logger logger = LoggerFactory.getLogger(CountiesServelet.class);

    private final String messageLogFail = "Failed to get counties | Details: {}";

    public CountyDto[] getCounties(String uf) {

        HttpEntity<Object> request = new HttpEntity<>(new HttpHeaders());
        byte[] response;
        ObjectMapper mapper = new ObjectMapper();

        try {
            String url = IbgeUrlEnum.URL_COUNTIES.getUrlValue().replace("{UF}", uf);

            response = restTemplateBuilder.build().exchange(url,
                    HttpMethod.GET, request, byte[].class).getBody();

            InputStream inputStream = new GZIPInputStream(new ByteArrayInputStream(response));

            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));

            CountyDto[] countyDtoVector = mapper.readValue(bufferedReader, CountyDto[].class);

            return countyDtoVector;
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