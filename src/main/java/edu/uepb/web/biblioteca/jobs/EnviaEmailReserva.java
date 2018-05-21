package edu.uepb.web.biblioteca.jobs;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

/**
 * @autor geovanniovinhas <vinhasgeovannio@gmail.com
 */
public class EnviaEmailReserva implements Job{
	public static final String EMAIL = "email";
	public static final String DATA_PEGAR = "dataPegar";
	
	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
		// TODO Auto-generated method stub
		
	}
}

