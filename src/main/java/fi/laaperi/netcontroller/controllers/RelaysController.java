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
public class RelaysController {

	private static final Logger logger = LoggerFactory.getLogger(RelaysController.class);
	
	@Autowired
	ControllerService controller;
	
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
	@RequestMapping(value = "/relay", method = RequestMethod.GET)
	public String relay(Locale locale, Model model) {
		logger.info("Relay");
		return "redirect:/#";
	}
	
}
