package com.mitrais.vehicle_service_system.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mitrais.vehicle_service_system.advice.ControllerAdvice;
import com.mitrais.vehicle_service_system.dto.BaseOperationRequest;
import com.mitrais.vehicle_service_system.entity.Operation;
import com.mitrais.vehicle_service_system.service.OperationService;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

class OperationControllerTest {
    private static final String BASE_URL = "/operation";
    private MockMvc mockMvc;

    @Mock
    private OperationService service;

    @InjectMocks
    private OperationController controllerInTest;

    private ObjectMapper objectMapper = new ObjectMapper();

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(controllerInTest)
                .setControllerAdvice(new ControllerAdvice())
                .build();
    }

    @Test
    void testCreate_whenValid_thenSuccessCreateEntity() throws Exception {
        BaseOperationRequest request = new BaseOperationRequest();
        request.setBrand("aaaa");
        request.setModel("bbbb");
        request.setEngine("cccc");
        request.setYearStart(2024);
        request.setYearEnd(2025);
        request.setDistanceStart(20000.0);
        request.setDistanceEnd(50000.0);
        request.setName("new");
        request.setApproxCost(200000.0);
        request.setDescription("New operation service");
        request.setTime(2);
        mockMvc.perform(post(BASE_URL)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(MockMvcResultMatchers.status().isNoContent());
    }

    @Test
    void testCreate_whenInvalidPropertiesValue_thenReturnBadRequest() throws Exception {
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
        MvcResult result = mockMvc.perform(post(BASE_URL)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andReturn();
    }

    @Test
    void testUpdate_whenValid_thenSuccessUpdateEntity() throws Exception {
        BaseOperationRequest request = new BaseOperationRequest();
        request.setBrand("aaaa");
        request.setModel("bbbb");
        request.setEngine("cccc");
        request.setYearStart(2024);
        request.setYearEnd(2025);
        request.setDistanceStart(20000.0);
        request.setDistanceEnd(50000.0);
        request.setName("new");
        request.setApproxCost(200000.0);
        request.setDescription("New operation service");
        request.setTime(2);
        Operation operation = new Operation();
        operation.setBrand("aaaa");
        operation.setModel("bbbb");
        operation.setEngine("cccc");
        operation.setYearStart(2024);
        operation.setYearEnd(2025);
        operation.setDistanceStart(20000.0);
        operation.setDistanceEnd(50000.0);
        operation.setName("new");
        operation.setApproxCost(200000.0);
        operation.setDescription("New operation service");
        operation.setTime(2);
        operation.setId(1L);
        Mockito.when(service.update(Mockito.any(), Mockito.any())).thenReturn(operation);
        Operation result = objectMapper.readValue(mockMvc.perform(put(BASE_URL + "/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(MockMvcResultMatchers.status().isOk()).andReturn().getResponse().getContentAsString(), Operation.class);
        Assertions.assertTrue(operation.getModel().equals(result.getModel()));
    }

    @Test
    void testUpdate_whenInvalidPropertiesValue_thenReturnBadRequest() throws Exception {
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
        MvcResult result = mockMvc.perform(put(BASE_URL + "/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andReturn();
    }

    @Test
    void testUpdate_whenInvalidId_thenReturnNotFound() throws Exception {
        BaseOperationRequest request = new BaseOperationRequest();
        request.setBrand("aaaa");
        request.setModel("bbbb");
        request.setEngine("cccc");
        request.setYearStart(2024);
        request.setYearEnd(2025);
        request.setDistanceStart(20000.0);
        request.setDistanceEnd(50000.0);
        request.setName("new");
        request.setApproxCost(200000.0);
        request.setDescription("New operation service");
        request.setTime(2);
        Mockito.when(service.update(Mockito.any(), Mockito.any())).thenThrow(EntityNotFoundException.class);
        mockMvc.perform(put(BASE_URL + "/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    @Test
    void testSearch_whenHaveParameter_thenReturnOk() throws Exception {
        Mockito.when(service.search(Mockito.any(), Mockito.any(), Mockito.any(), Mockito.any(), Mockito.any(), Mockito.any(), Mockito.any())).thenReturn(List.of(new Operation()));
        mockMvc.perform(get(BASE_URL + "?brand=toyota"))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void testSearch_whenNoParameter_thenReturnOk() throws Exception {
        Mockito.when(service.search(Mockito.any(), Mockito.any(), Mockito.any(), Mockito.any(), Mockito.any(), Mockito.any(), Mockito.any())).thenReturn(List.of());
        mockMvc.perform(get(BASE_URL + "?brand=toyota"))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void testSuggestion_whenParameterValidWithoutUnit_thenReturnOk() throws Exception {
        Mockito.when(service.searchByVehicle(Mockito.any(), Mockito.any(), Mockito.any(), Mockito.any(), Mockito.any(), Mockito.any())).thenReturn(List.of());
        mockMvc.perform(get(BASE_URL + "/suggestion?brand=toyota&model=camry&engine=2.5L&makeYear=2025&totalDistance=2000.0"))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void testSuggestion_whenParameterValidWithUnit_thenReturnOk() throws Exception {
        Mockito.when(service.searchByVehicle(Mockito.any(), Mockito.any(), Mockito.any(), Mockito.any(), Mockito.any(), Mockito.any())).thenReturn(List.of());
        mockMvc.perform(get(BASE_URL + "/suggestion?brand=toyota&model=camry&engine=2.5L&makeYear=2025&totalDistance=2000.0&unit=miles"))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void testSuggestion_whenParameterInvalid_thenReturnBadRequest() throws Exception {
        Mockito.when(service.searchByVehicle(Mockito.any(), Mockito.any(), Mockito.any(), Mockito.any(), Mockito.any(), Mockito.any())).thenReturn(List.of());
        mockMvc.perform(get(BASE_URL + "/suggestion?model=camry&engine=2.5L&makeYear=2025&totalDistance=2000.0"))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }
}