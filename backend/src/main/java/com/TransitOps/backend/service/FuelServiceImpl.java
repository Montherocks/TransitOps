package com.TransitOps.backend.service;

import com.TransitOps.backend.dto.CreateFuelLogRequestDTO;
import com.TransitOps.backend.dto.FuelLogResponseDTO;
import com.TransitOps.backend.dto.FuelStatisticsDTO;
import com.TransitOps.backend.dto.UpdateFuelLogRequestDTO;
import com.TransitOps.backend.entity.FuelLog;
import com.TransitOps.backend.entity.Trip;
import com.TransitOps.backend.entity.Vehicle;
import com.TransitOps.backend.exception.FuelLogNotFoundException;
import com.TransitOps.backend.mapper.FuelMapper;
import com.TransitOps.backend.repository.FuelRepository;
import com.TransitOps.backend.repository.VehicleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
@Transactional
public class FuelServiceImpl implements FuelService {

    private final FuelRepository fuelRepository;

    private final VehicleRepository vehicleRepository;

    private final TripRepository tripRepository;

    @Override
    public FuelLogResponseDTO createFuelLog(CreateFuelLogRequestDTO request){

        Vehicle vehicle = vehicleRepository.findById(request.getVehicleId())
                .orElseThrow(() -> new RuntimeException("Vehicle not found"));

        Trip trip = null;

        if(request.getTripId()!=null){

            trip = tripRepository.findById(request.getTripId())
                    .orElseThrow(() -> new RuntimeException("Trip not found"));

        }

        FuelLog fuelLog = FuelLog.builder()
                .vehicle(vehicle)
                .trip(trip)
                .fuelDate(request.getFuelDate())
                .liters(request.getLiters())
                .cost(request.getCost())
                .fuelStation(request.getFuelStation())
                .build();

        FuelLog saved = fuelRepository.save(fuelLog);

        return FuelMapper.toDTO(saved);

    }

    @Override
    @Transactional(readOnly = true)
    public FuelLogResponseDTO getFuelLogById(Long id){

        FuelLog fuelLog = fuelRepository.findById(id)
                .orElseThrow(() -> new FuelLogNotFoundException("Fuel Log not found."));

        return FuelMapper.toDTO(fuelLog);

    }
    @Override
    @Transactional(readOnly = true)
    public Page<FuelLogResponseDTO> getAllFuelLogs(
            int page,
            int size,
            String sortBy,
            String direction
    ){

        Sort sort = direction.equalsIgnoreCase("desc")
                ? Sort.by(sortBy).descending()
                : Sort.by(sortBy).ascending();

        Pageable pageable = PageRequest.of(page,size,sort);

        return fuelRepository.findAll(pageable).map(FuelMapper::toDTO);

    }

    @Override
    public FuelLogResponseDTO updateFuelLog(Long id, UpdateFuelLogRequestDTO request){

        FuelLog fuelLog = fuelRepository.findById(id)
                .orElseThrow(() -> new FuelLogNotFoundException("Fuel log not found."));

        fuelLog.setFuelDate(request.getFuelDate());
        fuelLog.setLiters(request.getLiters());
        fuelLog.setCost(request.getCost());
        fuelLog.setFuelStation(request.getFuelStation());

        FuelLog updated = fuelRepository.save(fuelLog);

        return FuelMapper.toDTO(updated);

    }

    @Override
    public void deleteFuelLog(Long id){

        FuelLog fuelLog = fuelRepository.findById(id)
                .orElseThrow(() -> new FuelLogNotFoundException("Fuel log not found."));

        fuelRepository.delete(fuelLog);

    }

    @Override
    @Transactional(readOnly = true)
    public FuelStatisticsDTO getStatistics(){

        long totalLogs = fuelRepository.count();

        Double totalFuel = fuelRepository.findAll()
                .stream()
                .mapToDouble(FuelLog::getLiters)
                .sum();

        BigDecimal totalCost = fuelRepository.findAll()
                .stream()
                .map(FuelLog::getCost)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        Double averageFuel = totalLogs == 0
                ? 0.0
                : totalFuel / totalLogs;

        return FuelStatisticsDTO.builder()
                .totalLogs(totalLogs)
                .totalFuelConsumed(totalFuel)
                .totalFuelCost(totalCost)
                .averageFuelPerLog(averageFuel)
                .build();

    }

    @Override
    @Transactional(readOnly = true)
    public Page<FuelLogResponseDTO> getFuelLogsByVehicle(Long vehicleId, int page, int size){

        Pageable pageable = PageRequest.of(page,size);

        return fuelRepository
                .findByVehicleId(vehicleId, pageable)
                .map(FuelMapper::toDTO);

    }


}