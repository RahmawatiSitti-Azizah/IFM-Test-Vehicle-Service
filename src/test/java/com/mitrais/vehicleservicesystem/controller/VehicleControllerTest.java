package com.mitrais.vehicleservicesystem.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mitrais.vehicleservicesystem.advice.ControllerAdvice;
import com.mitrais.vehicleservicesystem.dto.BaseVehicleRequest;
import com.mitrais.vehicleservicesystem.entity.Vehicle;
import com.mitrais.vehicleservicesystem.mapper.VehicleMapper;
import com.mitrais.vehicleservicesystem.service.VehicleService;
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
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

class VehicleControllerTest {
    private static final String BASE_URL = "/vehicle";
    private MockMvc mockMvc;

    @Mock
    private VehicleService service;

    @InjectMocks
    private VehicleController controllerInTest;

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
        BaseVehicleRequest request = new BaseVehicleRequest();
        request.setBrand("aaaa");
        request.setModel("bbbb");
        request.setEngine("cccc");
        request.setMakeYear(2024);
        mockMvc.perform(post(BASE_URL)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(MockMvcResultMatchers.status().isNoContent());
    }

    @Test
    void testCreate_whenInvalidPropertiesValue_thenReturnBadRequest() throws Exception {
        BaseVehicleRequest request = new BaseVehicleRequest();
        request.setBrand("aaaa");
        request.setModel(null);
        request.setEngine("cccc");
        request.setMakeYear(2024);
        mockMvc.perform(post(BASE_URL)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

    @Test
    void testUpdate_whenValid_thenSuccessUpdateEntity() throws Exception {
        BaseVehicleRequest request = new BaseVehicleRequest();
        request.setBrand("aaaa");
        request.setModel("bbbb");
        request.setEngine("cccc");
        request.setMakeYear(2024);
        Vehicle vehicle = VehicleMapper.toEntity(request);
        Mockito.when(service.update(Mockito.any(), Mockito.any())).thenReturn(vehicle);
        Vehicle result = objectMapper.readValue(mockMvc.perform(put(BASE_URL + "/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(MockMvcResultMatchers.status().isOk()).andReturn().getResponse().getContentAsString(), Vehicle.class);
        Assertions.assertTrue(vehicle.getModel().equals(result.getModel()));
    }

    @Test
    void testUpdate_whenInvalidPropertiesValue_thenReturnBadRequest() throws Exception {
        BaseVehicleRequest request = new BaseVehicleRequest();
        request.setBrand("aaaa");
        request.setModel("bbbb");
        request.setEngine(null);
        request.setMakeYear(2024);
        mockMvc.perform(put(BASE_URL + "/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

    @Test
    void testUpdate_whenInvalidId_thenReturnNotFound() throws Exception {
        BaseVehicleRequest request = new BaseVehicleRequest();
        request.setBrand("aaaa");
        request.setModel("bbbb");
        request.setEngine("cccc");
        request.setMakeYear(2024);
        Mockito.when(service.update(Mockito.any(), Mockito.any())).thenThrow(EntityNotFoundException.class);
        mockMvc.perform(put(BASE_URL + "/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    @Test
    void testSearch_whenHaveAllParameter_thenReturnOk() throws Exception {
        Mockito.when(service.findVehicleFrom(Mockito.any(), Mockito.any(), Mockito.any())).thenReturn(List.of(new Vehicle()));
        mockMvc.perform(get(BASE_URL + "?brand=toyota&model=camr&engine=123"))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void testSearch_whenMissingParameter_thenReturnBadRequest() throws Exception {
        Mockito.when(service.findVehicleFrom(Mockito.any(), Mockito.any(), Mockito.any())).thenReturn(List.of());
        mockMvc.perform(get(BASE_URL + "?brand=toyota&model=camry"))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }
}