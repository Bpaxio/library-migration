package ru.otus.bbpax.shell;

import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

import java.util.UUID;

@ShellComponent("batch")
public class BatchController {
    private final JobLauncher jobLauncher;
    private final Job job;

    private JobExecution execution;

    public BatchController(JobLauncher jobLauncher, Job job) {
        this.jobLauncher = jobLauncher;
        this.job = job;
    }

    @ShellMethod(key = {"start"}, value = "start migration from Mongo to PSQL")
    public void startMiration() throws JobParametersInvalidException, JobExecutionAlreadyRunningException, JobRestartException, JobInstanceAlreadyCompleteException {
        if(execution != null && execution.isRunning()) throw new IllegalStateException("wait until prev migration fill be done or stop it");
        execution = jobLauncher.run(job, new JobParametersBuilder()
                .addString("unique-key", UUID.randomUUID().toString())
                .toJobParameters());
    }

    @ShellMethod(key = {"stop"}, value = "stop migration from Mongo to PSQL with previous parameters")
    public void stop() {
        if (execution == null) throw new IllegalStateException("no execution to stop. use 'start'");
        execution.stop();
    }


    @ShellMethod(key = {"restart"}, value = "restart migration from Mongo to PSQL with previous parameters")
    public void restart() throws JobParametersInvalidException, JobExecutionAlreadyRunningException, JobRestartException, JobInstanceAlreadyCompleteException {
        if (execution == null) throw new IllegalStateException("no previous data for restart use 'start'");
        execution = jobLauncher.run(job, execution.getJobParameters());
    }
}
