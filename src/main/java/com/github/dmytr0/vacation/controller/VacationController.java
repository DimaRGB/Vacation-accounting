package com.github.dmytr0.vacation.controller;

import com.github.dmytr0.vacation.dto.CreateVacationRecordsDto;
import com.github.dmytr0.vacation.dto.UserVacationsStats;
import com.github.dmytr0.vacation.service.VacationCalculationService;
import com.github.dmytr0.vacation.service.VacationService;
import com.github.dmytr0.vacation.service.auth.IAuthenticationFacade;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDate;

@Log4j2
@RestController
@RequestMapping("vacations")
@RequiredArgsConstructor
public class VacationController {

    private final VacationService vacationService;
    private final VacationCalculationService vacationCalculationService;
    private final IAuthenticationFacade authenticationFacade;

    @GetMapping("calculate")
    public UserVacationsStats getAvailableDays(@RequestParam(name = "user", required = false) String userName,
                                               @RequestParam(name = "onDate", required = false) LocalDate onDate) {

        onDate = onDate == null ? LocalDate.now() : onDate;
        userName = userName != null ? userName : authenticationFacade.getCurrentUserName();
        validateUser(userName);
        return vacationCalculationService.calc(userName, onDate);
    }

    @PostMapping
    public void createVacationRecord(@RequestBody @Valid CreateVacationRecordsDto createVacationRecordsDto) {
        log.info("Request to create vacation records {} from user {}", createVacationRecordsDto, authenticationFacade.getCurrentUserName());
        validateUser(createVacationRecordsDto.getUserName());
        vacationService.createRecord(createVacationRecordsDto);
    }


    //TODO verify permission
    private void validateUser(String userName) {
        authenticationFacade.getAuthentication().getAuthorities();

    }
}
