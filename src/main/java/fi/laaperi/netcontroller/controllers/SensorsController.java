package fi.laaperi.netcontroller.controllers;

import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import fi.laaperi.netcontroller.services.ControllerService;

@Controller
public class SensorsController {

	private static final Logger logger = LoggerFactory.getLogger(SensorsController.class);
	
	@Autowired
	ControllerService controller;
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/sensors", method = RequestMethod.GET)
	public String sensors(Locale locale, Model model) {
		logger.info("Sensors controller");
		return "home";
	}
	
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/sensor", method = RequestMethod.GET)
	public String sensor(Locale locale, Model model) {
		logger.info("Sensor");
		return "redirect:/#";
	}
	
}
