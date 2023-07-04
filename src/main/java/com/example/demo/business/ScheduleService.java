package com.example.demo.business;

import com.example.demo.business.impl.MyJobLauncher;
import com.example.demo.exceptions.ScheduleServiceException;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
@EnableScheduling
public class ScheduleService {
    private final MyJobLauncher myJobLauncher;

    public ScheduleService(MyJobLauncher myJobLauncher) {
        this.myJobLauncher = myJobLauncher;
    }
    @Scheduled(initialDelay = 10000, fixedRate = 5000)
    public void sendMessage() throws JobInstanceAlreadyCompleteException, JobExecutionAlreadyRunningException, JobParametersInvalidException, JobRestartException {
        System.out.println("First, I execute myself");
        String message = "CCOP                                                            IP                                                              000123012345678                                                 FOREIGNCARD                                                     aadde-ddddee-eeeedd-eeeedd                                      enrique.rojas_stefanini@scotiabank.com.pe                                                                                                                                                                                                                       SBP00001            esListo Jorge, Descubre como usar tu Cuenta Sueldo                                                                                                                                                                                                                productos@scotiabank.com.pe                                                                                                                                                                                                                                     Productos                                                                                                                                                                                                                                                       110                   Laura García                                                                    1                   20 de Mayo de 2023                                                              2                   1234567890                                                                      3                   0031230004567890                                                                4                   0031230004567890                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                            ";
        if (message != null) {
            try {
                myJobLauncher.launch(message);
            } catch (Exception e) {
                throw new ScheduleServiceException("Error in ScheduleService while launching the job.", e);
            }
        }
    }
}
