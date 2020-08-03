package com.github.dmytr0.vacation.service;

import com.github.dmytr0.vacation.converter.UserConverter;
import com.github.dmytr0.vacation.domain.User;
import com.github.dmytr0.vacation.domain.VacationRecord;
import com.github.dmytr0.vacation.dto.CreateVacationRecordsDto;
import com.github.dmytr0.vacation.dto.UserDto;
import com.github.dmytr0.vacation.dto.UserVacationsDto;
import com.github.dmytr0.vacation.repository.UsersRepository;
import com.github.dmytr0.vacation.repository.VacationRecordRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.toList;

@Service
@Log4j2
@RequiredArgsConstructor
public class VacationService {

    private final VacationRecordRepository vacationRecordRepository;
    private final UsersRepository usersRepository;
    private final UserConverter userConverter;

    public void createRecord(CreateVacationRecordsDto createVacationRecordsDto) {
        Set<VacationRecord> entities = convertToEntities(createVacationRecordsDto);
        vacationRecordRepository.saveAll(entities);
    }

    public List<UserVacationsDto> getVacationRecords() {
        List<UserDto> allUsers = usersRepository.findAll().stream()
                .map(userConverter::convertToDto)
                .collect(toList());

        Map<String, List<VacationRecord>> vacationMap = vacationRecordRepository.findAll().stream()
                .collect(groupingBy(VacationRecord::getUserName));

        return allUsers.stream().map(user -> new UserVacationsDto(user, vacationMap.get(user.getEmail())))
                .collect(Collectors.toList());
    }

    private Set<VacationRecord> convertToEntities(CreateVacationRecordsDto createVacationRecordsDto) {
        return createVacationRecordsDto.getDates().stream()
                .map(d -> new VacationRecord(createVacationRecordsDto.getUserName(), d))
                .collect(Collectors.toSet());
    }
}
