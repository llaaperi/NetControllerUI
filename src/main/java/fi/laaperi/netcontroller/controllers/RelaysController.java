package fi.laaperi.netcontroller.controllers;

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

import fi.laaperi.netcontroller.repository.Relay;
import fi.laaperi.netcontroller.repository.RelayDao;
import fi.laaperi.netcontroller.repository.Sensor;
import fi.laaperi.netcontroller.services.ControllerService;

@Controller
public class RelaysController {

	private static final Logger logger = LoggerFactory.getLogger(RelaysController.class);
	
	@Autowired
	ControllerService controller;
	
	@Autowired
	RelayDao relayDao;
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/relays", method = RequestMethod.GET)
	public String relays(Locale locale, Model model) {
		logger.info("Relays controller");
		return "home";
	}
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/relay", method = RequestMethod.GET, produces="application/json")
	@ResponseBody
	public Relay relay(@RequestParam("id")long id) {
		logger.info("Get relay " + id);
		Relay relay = relayDao.findById(id);
		return relay;
	}
	
	@RequestMapping(value = "/relay/rename", method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.OK)
	public void rename(@RequestParam("id")long id,
						@RequestParam("name")String name) {
		logger.info("Rename sensor " + id + " to " + name);
		Relay relay = relayDao.findById(id);
		relay.setName(name);
		relayDao.persist(relay);
	}
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/relay/toggle", method = RequestMethod.POST)
	@ResponseBody
	public String toggle(@RequestParam("id")long id) {
		logger.info("Toggle relay " + id);
		controller.setState(id, true);
		return "OK";
	}
	
}
