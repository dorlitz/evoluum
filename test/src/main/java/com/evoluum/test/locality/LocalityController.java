package com.evoluum.test.locality;

import com.evoluum.test.dto.LocalityResume;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpStatusCodeException;

import java.io.IOException;
import java.util.List;

@RestController
public class LocalityController {
    private static final Logger log = LoggerFactory.getLogger(LocalityController.class);
    private static final String messageReturn = "Não foi possível retornar as informações de localidade";

    private final LocalityService localityService;

    @Autowired
    public LocalityController(LocalityService localityService) {

        this.localityService = localityService;
    }

    @RequestMapping(value = "/populate-all",
            method = RequestMethod.GET, produces = "application/json" )
    public ResponseEntity saveAll() {

        try {
            localityService.populateAll();
            return ResponseEntity.ok(HttpStatus.OK);
        } catch (ClassCastException e) {
            return new ResponseEntity(messageReturn, HttpStatus.BAD_GATEWAY);

        } catch (HttpStatusCodeException e) {
            log.warn(e.getMessage());
            return new ResponseEntity(messageReturn, HttpStatus.BAD_GATEWAY);
        } catch (Exception e) {
            log.warn(e.getMessage());
            return new ResponseEntity(messageReturn, HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(value = "/find-by-uf/{uf}",
            method = RequestMethod.GET, produces = "application/json" )
    public ResponseEntity<?> findByUf(@PathVariable Integer uf) {

        try {
            List<LocalityResume> locality = localityService.findAllByUf(uf);

            if (locality == null) {
                return new ResponseEntity(messageReturn, HttpStatus.BAD_GATEWAY);
            } else {
                return new ResponseEntity<>(locality, HttpStatus.OK);
            }

        } catch (ClassCastException e) {
            log.warn(e.getMessage());
            return new ResponseEntity(messageReturn, HttpStatus.BAD_GATEWAY);

        } catch (HttpStatusCodeException e) {
            log.warn(e.getMessage());
            return new ResponseEntity(messageReturn, HttpStatus.BAD_GATEWAY);
        } catch (Exception e) {
            log.warn(e.getMessage());
            return new ResponseEntity(messageReturn, HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(value = "/find-by-city-name/{cityName}",
            method = RequestMethod.GET)
    public ResponseEntity<Integer> findByUf(@PathVariable String cityName) {

        try {
            Integer cityId = localityService.findAllByCityName(cityName);

            if (cityId == 0) {
                return new ResponseEntity(messageReturn, HttpStatus.NOT_FOUND);
            } else {
                return new ResponseEntity<>(cityId, HttpStatus.OK);
            }
        } catch (Exception e) {
            log.warn(e.getMessage());
            return new ResponseEntity(messageReturn, HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(method = RequestMethod.GET, value = "/find-csv-by-uf/{uf}")
    public @ResponseBody byte[] findCsvByUf(@PathVariable Integer uf) {

        try {
            return localityService.findCsvByUf(uf);
        } catch ( IOException e ) {
            log.warn(e.getMessage());
            return null;
        }
    }
}
