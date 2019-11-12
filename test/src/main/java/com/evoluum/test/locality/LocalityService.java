package com.evoluum.test.locality;

import com.evoluum.test.dto.CountyDto;
import com.evoluum.test.dto.FederationDto;
import com.evoluum.test.dto.LocalityResume;
import com.evoluum.test.ibge.CountiesServelet;
import com.evoluum.test.ibge.StatesServelet;
import com.evoluum.test.persistence.entity.*;
import com.evoluum.test.persistence.repository.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.Charset;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class LocalityService {
    private static final Logger log = LoggerFactory.getLogger(LocalityService.class);

    private final CountyRepository countyRepository;
    private final FederationRepository federationRepository;
    private final MesoRegionRepository mesoRegionRepository;
    private final MicroRegionRepository microRegionRepository;
    private final RegionRepository regionRepository;
    private final CountiesServelet countiesServelet;
    private final StatesServelet statesServelet;

    @Autowired
    public LocalityService(CountyRepository countyRepository, FederationRepository federationRepository,
                           MesoRegionRepository mesoRegionRepository, MicroRegionRepository microRegionRepository,
                           RegionRepository regionRepository, CountiesServelet countiesServelet,
                           StatesServelet statesServelet) {

        this.countyRepository = countyRepository;
        this.federationRepository = federationRepository;
        this.mesoRegionRepository = mesoRegionRepository;
        this.microRegionRepository = microRegionRepository;
        this.regionRepository = regionRepository;
        this.countiesServelet = countiesServelet;
        this.statesServelet = statesServelet;
    }

    //public Object findAll() {
        //if (mesoRegionRepository.getOneResult() == null)
          //  return populateLocalityInfo();

        //return null;
        //return noteIssueServelet.issueInvoice(invoice);
   // }

    /* public Object findAll(Integer uf) {
        if (mesoRegionRepository.getOneResult(uf) == null)
    }

    private void populateCountyDto(Integer uf) {

    } */

    public void populateAll() {
        FederationDto[] federationDtoVector = statesServelet.getStates();

        for (int i = 0; i < federationDtoVector.length; i++) {
            FederationDto federationDto = federationDtoVector[i];

            saveFederationDto(federationDto);

            CountyDto[] countyDtoVector = countiesServelet.getCounties(federationDto.getId().toString());

            if (countyDtoVector == null)
                continue;

            for (int j = 0; j < countyDtoVector.length; j++) {
                CountyDto countyDto = countyDtoVector[j];
                saveCountyDto(countyDto);
            }
        }
    }

    private void saveFederationDto(FederationDto federationDto) {
        try {
            Federation federation = new Federation();
            federation.setId(federationDto.getId());
            federation.setName(federationDto.getNome());
            federation.setInitials(federationDto.getSigla());
            federation.setRegionId(federationDto.getRegiao().getId());

            Region region = new Region();
            region.setId(federationDto.getRegiao().getId());
            region.setName(federationDto.getRegiao().getNome());
            region.setInitials(federationDto.getRegiao().getSigla());

            regionRepository.save(region);
            federationRepository.save(federation);
        } catch (Exception exception) {
            log.error("Not possible to save FederationDto: {}", exception.getMessage());
            throw exception;
        }

    }
    private void saveCountyDto(CountyDto countyDto) {
        try {
            County county = new County();
            county.setId(countyDto.getId());
            county.setName(countyDto.getNome());
            county.setMicroRegionId(countyDto.getMicrorregiao().getId());

            MicroRegion microRegion = new MicroRegion();
            microRegion.setId(countyDto.getMicrorregiao().getId());
            microRegion.setName(countyDto.getMicrorregiao().getNome());
            microRegion.setMesoregionId(countyDto.getMicrorregiao().getMesorregiao().getId());

            MesoRegion mesoRegion = new MesoRegion();
            mesoRegion.setId(countyDto.getMicrorregiao().getMesorregiao().getId());
            mesoRegion.setName(countyDto.getMicrorregiao().getMesorregiao().getNome());
            mesoRegion.setFederationId(countyDto.getMicrorregiao().getMesorregiao().getUF().getId());

            microRegionRepository.save(microRegion);
            mesoRegionRepository.save(mesoRegion);
            countyRepository.save(county);
        } catch (Exception exception) {
            log.error("Not possible to save CountyDto: {}", exception.getMessage());
            throw exception;
        }
    }

    public List<LocalityResume> findAllByUf(Integer uf) {
        List<Map<String, Object>> results = federationRepository.getResume(uf);
        List<LocalityResume> resumeList = results.stream().map(result -> new LocalityResume(result))
                .collect(Collectors.toList());
        return resumeList;
    }

    public byte[] findCsvByUf(Integer uf) throws IOException {
        List<LocalityResume> resumeList = findAllByUf(uf);

        if (resumeList == null)
            return null;

        byte[] buffer;

        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        String header = "idEstado,siglaEstado,regiaoNome,nomeCidade,nomeMesorregiao,nomeFormatado {cidade/UF}\n";

        stream.write(header.getBytes());

        for (LocalityResume resume: resumeList) {
            StringBuffer stringBuffer = new StringBuffer();

            stringBuffer.append(resume.getIdEstado());
            stringBuffer.append(",");
            stringBuffer.append(resume.getSiglaEstado());
            stringBuffer.append(",");
            stringBuffer.append(resume.getRegiaoNome());
            stringBuffer.append(",");
            stringBuffer.append(resume.getNomeCidade());
            stringBuffer.append(",");
            stringBuffer.append(resume.getNomeMesorregiao());
            stringBuffer.append(",");
            stringBuffer.append(resume.getNomeCidade().concat("/").concat(resume.getSiglaEstado()));
            stringBuffer.append("\n");

            stream.write(stringBuffer.toString().getBytes("UTF-8"));

            stream.flush();
        }

        buffer = stream.toByteArray();

        stream.close();

        return buffer;
    }

    public Integer findAllByCityName(String cityName) {
        County county = countyRepository.findByName(cityName);

        if (county != null)
            return county.getId();
        else
            return 0;
    }
}
