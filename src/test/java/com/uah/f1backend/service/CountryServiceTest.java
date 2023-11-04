package com.uah.f1backend.service;

import com.uah.f1backend.model.CountryModel;
import com.uah.f1backend.repository.CountryModelRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.Optional;

import static com.uah.f1backend.utils.CountryUtils.*;
import static com.uah.f1backend.configuration.HttpExceptions.*;

public class CountryServiceTest {
    @InjectMocks
    private CountryService countryService;

    @Mock
    private CountryModelRepository countryModelRepository;

    private AutoCloseable closeable;

    @BeforeEach
    public void initMocks() {
        this.closeable = MockitoAnnotations.openMocks(this);
        this.countryService = new CountryService(countryModelRepository);
    }

    @AfterEach
    public void closeService() throws Exception {
        this.closeable.close();
    }

    @Test
    public void findAllCountriesEmptyTest() {
        final var countryList = new ArrayList<CountryModel>();
        Mockito.doReturn(countryList).when(countryModelRepository).findAll();
        final var actualResultList = countryService.obtainAllCountries();
        Assertions.assertEquals(0, actualResultList.size());
    }

    @Test
    public void findAllCountriesTest() {
        final var countryList = dummyListCountryModel();
        Mockito.doReturn(countryList).when(countryModelRepository).findAll();
        final var actualResult = countryService.obtainAllCountries();
        final var expectedResult = dummyListCountryDTOResponse();
        Assertions.assertEquals(expectedResult, actualResult);
    }

    @Test
    public void findCountryByIdTest() {
        final var cm = dummyCountryModel();
        Mockito.doReturn(Optional.of(cm)).when(countryModelRepository).findById(1);
        final var actualResult = countryService.obtainCountryById(cm.getId());
        final var expectedResult = dummyCountryDTOResponse();
        Assertions.assertEquals(expectedResult, actualResult);
    }

    @Test
    public void findCountryByIdNotFoundTest(){
        Mockito.doReturn(Optional.empty()).when(countryModelRepository).findById(1);
        Assertions.assertThrows(CountryDoesntExistException.class, () -> {
            countryService.obtainCountryById(1);
        });
    }

}
