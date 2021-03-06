package fi.laaperi.netcontroller.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import fi.laaperi.netcontroller.repository.Sensor;
import fi.laaperi.netcontroller.repository.SensorDao;
import fi.laaperi.netcontroller.services.ControllerService;

@Controller
public class SensorsController {

	private static final Logger logger = LoggerFactory.getLogger(SensorsController.class);
	
	@Autowired
	ControllerService controller;
	
	@Autowired
	SensorDao sensorDao;
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/sensors", method = RequestMethod.GET, produces="application/json")
	public @ResponseBody List<Sensor> sensors() {
		logger.info("Sensors");
		//List<Sensor> sensors = controller.getSensors();
		List<Sensor> sensors = new ArrayList<Sensor>();
		Sensor s1 = new Sensor(1, "1", 1);
		Sensor s2 = new Sensor(2, "2", 2);
		sensors.add(s1);
		sensors.add(s2);
		return sensors;
	}
	
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/sensor", method = RequestMethod.GET, produces="application/json")
	@ResponseBody
	public Sensor sensor(@RequestParam("id")long id) {
		logger.info("Get sensor " + id);
		Sensor sensor = sensorDao.findById(id);
		return sensor;
	}
	
	@RequestMapping(value = "/sensor/rename", method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.OK)
	public void rename(@RequestParam("id")long id,
						@RequestParam("name")String name) {
		logger.info("Rename sensor " + id + " to " + name);
		Sensor sensor = sensorDao.findById(id);
		sensor.setName(name);
		sensorDao.persist(sensor);
	}
	
}
