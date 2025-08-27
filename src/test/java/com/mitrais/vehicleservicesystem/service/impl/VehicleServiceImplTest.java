package com.mitrais.vehicleservicesystem.service.impl;

import com.mitrais.vehicleservicesystem.dto.BaseVehicleRequest;
import com.mitrais.vehicleservicesystem.entity.Vehicle;
import com.mitrais.vehicleservicesystem.mapper.VehicleMapper;
import com.mitrais.vehicleservicesystem.repository.VehicleRepository;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class VehicleServiceImplTest {
    private VehicleServiceImpl serviceInTest;
    private VehicleRepository repository = mock(VehicleRepository.class);

    @BeforeEach
    void setup() {
        serviceInTest = new VehicleServiceImpl(repository);
    }

    @Test
    void testCreate_validInput_thenSuccessSaveNewEntity() throws Exception {
        BaseVehicleRequest request = new BaseVehicleRequest();
        request.setBrand("aaaa");
        request.setModel("bbbb");
        request.setEngine("cccc");
        request.setMakeYear(2024);
        serviceInTest.create(request);
        verify(repository, times(1)).save(any());
    }

    @Test
    void testUpdate_validInput_thenSuccessUpdateEntity() throws Exception {
        BaseVehicleRequest request = new BaseVehicleRequest();
        request.setBrand("aaaa");
        request.setModel("bbbb");
        request.setEngine("cccc");
        request.setMakeYear(2024);
        Vehicle vehicle = VehicleMapper.toEntity(request);
        when(repository.findById(any())).thenReturn(Optional.of(vehicle));
        serviceInTest.update(1L, request);
        verify(repository, times(1)).findById(any());
        verify(repository, times(1)).save(any());
    }

    @Test
    void testUpdate_butEntityNotFound_thenThrowEntityNotFoundError() throws Exception {
        BaseVehicleRequest request = new BaseVehicleRequest();
        request.setBrand("aaaa");
        request.setModel("bbbb");
        request.setEngine("cccc");
        request.setMakeYear(2024);
        when(repository.findById(any())).thenReturn(Optional.empty());
        Assertions.assertThrows(EntityNotFoundException.class, () -> serviceInTest.update(1L, request));
        verify(repository, times(1)).findById(any());
        verify(repository, never()).save(any());
    }

    @Test
    void testSearch_withAllFilterNull_thenReturnEmptyData() throws Exception {
        when(repository.findByModelAndBrandAndEngine(null, null, null)).thenReturn(List.of());
        List<Vehicle> result = serviceInTest.findVehicleFrom(null, null, null);
        Assertions.assertTrue(result.isEmpty());
        verify(repository, times(1)).findByModelAndBrandAndEngine(null, null, null);
    }

    @Test
    void testSearch_withAllFilterFilled_thenReturnDataBasedOnSpecification() throws Exception {
        when(repository.findByModelAndBrandAndEngine(any(), any(), any())).thenReturn(List.of(new Vehicle()));
        List<Vehicle> result = serviceInTest.findVehicleFrom("a", "a", "a");
        Assertions.assertTrue(!result.isEmpty());
        verify(repository, times(1)).findByModelAndBrandAndEngine(any(), any(), any());
    }
}