package fi.laaperi.netcontroller.controllers;

import java.util.List;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import fi.laaperi.netcontroller.repository.Relay;
import fi.laaperi.netcontroller.repository.RelayDao;
import fi.laaperi.netcontroller.repository.Sensor;
import fi.laaperi.netcontroller.repository.SensorDao;
import fi.laaperi.netcontroller.services.ControllerService;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	@Autowired
	SensorDao sensorDao;
	
	@Autowired
	RelayDao relayDao;
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		logger.info("Home");
		
		List<Sensor> sensors = sensorDao.getAll();
		List<Relay> relays = relayDao.getAll();
		
		model.addAttribute("sensors", sensors);
		model.addAttribute("relays", relays);
		
		return "home";
	}
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/status", method = RequestMethod.GET)
	@ResponseBody
	public String status(Locale locale, Model model) {
		logger.info("Status");
		return "OK";
	}
	
}
