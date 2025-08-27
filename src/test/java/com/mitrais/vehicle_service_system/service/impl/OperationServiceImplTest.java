package com.mitrais.vehicle_service_system.service.impl;

import com.mitrais.vehicle_service_system.dto.BaseOperationRequest;
import com.mitrais.vehicle_service_system.entity.Operation;
import com.mitrais.vehicle_service_system.mapper.OperationMapper;
import com.mitrais.vehicle_service_system.repository.OperationRepository;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;

class OperationServiceImplTest {
    private OperationServiceImpl serviceInTest;
    private OperationRepository repository = mock(OperationRepository.class);

    @BeforeEach
    void setup() {
        serviceInTest = new OperationServiceImpl(repository);
    }

    @Test
    void testCreate_validInput_thenSuccessSaveNewEntity() throws Exception {
        BaseOperationRequest request = new BaseOperationRequest();
        request.setBrand("aaaa");
        request.setModel("bbbb");
        request.setEngine("cccc");
        request.setYearStart(2024);
        request.setYearEnd(2020);
        request.setDistanceStart(20000.0);
        request.setDistanceEnd(50000.0);
        request.setName("new");
        request.setApproxCost(null);
        request.setDescription("New operation service");
        request.setTime(2);
        serviceInTest.create(request);
        verify(repository, times(1)).save(any());
    }

    @Test
    void testUpdate_validInput_thenSuccessUpdateEntity() throws Exception {
        BaseOperationRequest request = new BaseOperationRequest();
        request.setBrand("aaaa");
        request.setModel("bbbb");
        request.setEngine("cccc");
        request.setYearStart(2024);
        request.setYearEnd(2020);
        request.setDistanceStart(20000.0);
        request.setDistanceEnd(50000.0);
        request.setName("new");
        request.setApproxCost(null);
        request.setDescription("New operation service");
        request.setTime(2);
        Operation operation = OperationMapper.convert(request);
        when(repository.findById(any())).thenReturn(Optional.of(operation));
        serviceInTest.update(1L, request);
        verify(repository, times(1)).findById(any());
        verify(repository, times(1)).save(any());
    }

    @Test
    void testUpdate_butEntityNotFound_thenThrowEntityNotFoundError() throws Exception {
        BaseOperationRequest request = new BaseOperationRequest();
        request.setBrand("aaaa");
        request.setModel("bbbb");
        request.setEngine("cccc");
        request.setYearStart(2024);
        request.setYearEnd(2020);
        request.setDistanceStart(20000.0);
        request.setDistanceEnd(50000.0);
        request.setName("new");
        request.setApproxCost(null);
        request.setDescription("New operation service");
        request.setTime(2);
        when(repository.findById(any())).thenReturn(Optional.empty());
        Assertions.assertThrows(EntityNotFoundException.class, () -> serviceInTest.update(1L, request));
        verify(repository, times(1)).findById(any());
        verify(repository, never()).save(any());
    }

    @Test
    void testSearch_withAllFilterNull_thenReturnAllData() throws Exception {
        when(repository.findAll()).thenReturn(List.of(new Operation()));
        List<Operation> result = serviceInTest.search(null, null, null, null, null, null, null);
        Assertions.assertTrue(!result.isEmpty());
        verify(repository, times(1)).findAll();
        verify(repository, never()).findAll(any(Specification.class));
    }

    @Test
    void testSearch_withAllFilterFilled_thenReturnDataBasedOnSpecification() throws Exception {
        when(repository.findAll(any(Specification.class))).thenReturn(List.of(new Operation()));
        List<Operation> result = serviceInTest.search("a", "a", "a", 1, 2, 3000.1, 3000.1);
        Assertions.assertTrue(!result.isEmpty());
        verify(repository, times(1)).findAll(any(Specification.class));
        verify(repository, never()).findAll();
    }

    @Test
    void testSearch_withPartialFilterFilled_thenReturnDataBasedOnSpecification() throws Exception {
        when(repository.findAll(any(Specification.class))).thenReturn(List.of(new Operation()));
        List<Operation> result = serviceInTest.search("a", null, "a", 1, null, 3000.1, 3000.1);
        Assertions.assertTrue(!result.isEmpty());
        verify(repository, times(1)).findAll(any(Specification.class));
        verify(repository, never()).findAll();
    }
}