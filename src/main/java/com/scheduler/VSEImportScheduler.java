package com.scheduler;

import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.ejb.ScheduleExpression;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.ejb.Timeout;
import javax.ejb.Timer;
import javax.ejb.TimerConfig;
import javax.ejb.TimerService;
import javax.inject.Inject;

import com.rulebeans.Configuration;
import com.services.ImportService;
import com.services.RuleService;

/**
 * This class creates an automatic scheduler for the import of vehicles from the
 * VSE system. Read the date and time from the MySQL database, sets up a timer,
 * and when the time comes do the import.
 * 
 * @author sandor.naghi
 *
 */
@Singleton
@Startup
public class VSEImportScheduler {

	private static final Logger LOGGER = Logger.getLogger(VSEImportScheduler.class.getName());

	@Inject
	private RuleService ruleService;

	@Inject
	private ImportService importService;

	@Resource
	private TimerService timerService;

	@PostConstruct
	private void init() {

		for (Configuration conf : ruleService.getImportTimeInterval()) {

			SchedulerContext context = new SchedulerContext();
			context.setCountry(conf.getCountry());
			context.setVehicleCategory(conf.getVehicleCategory());

			String[] dayHourMin = conf.getValue().split(" ");

			ScheduleExpression exp = new ScheduleExpression();
			exp.dayOfWeek(dayHourMin[0]).hour(dayHourMin[1]).minute(dayHourMin[2]).second(0);

			TimerConfig timerConfig = new TimerConfig(context, false);
			timerService.createCalendarTimer(exp, timerConfig);
		}
	}

	@Timeout
	private void automaticVseImport(Timer timer) {

		SchedulerContext context = (SchedulerContext) timer.getInfo();

		try {
			importService.importVseVehicle(context.getCountry(), context.getVehicleCategory());
			LOGGER.info(String.format("The import for market %s/%s has been made!", context.getCountry(),
					context.getVehicleCategory()));
		} catch (Exception e) {
			LOGGER.severe(e.getMessage());
		}
	}

}
