package com.github.dmytr0.vacation.service;

import com.github.dmytr0.vacation.domain.VacationRecord;
import com.github.dmytr0.vacation.dto.CreateVacationRecordsDto;
import com.github.dmytr0.vacation.repository.VacationRecordRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
@Log4j2
@RequiredArgsConstructor
public class VacationService {

    private final VacationRecordRepository vacationRecordRepository;

    public void createRecord(CreateVacationRecordsDto createVacationRecordsDto) {
        Set<VacationRecord> entities = convertToEntities(createVacationRecordsDto);
        vacationRecordRepository.saveAll(entities);
    }

    private Set<VacationRecord> convertToEntities(CreateVacationRecordsDto createVacationRecordsDto) {
        return createVacationRecordsDto.getDates().stream()
                .map(d -> new VacationRecord(createVacationRecordsDto.getUserName(), d))
                .collect(Collectors.toSet());
    }
}
