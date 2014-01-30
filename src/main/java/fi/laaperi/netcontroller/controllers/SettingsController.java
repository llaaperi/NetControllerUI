package fi.laaperi.netcontroller.controllers;

import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class SettingsController {
	
	private static final Logger logger = LoggerFactory.getLogger(SettingsController.class);
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/settings", method = RequestMethod.GET)
	public String status(Locale locale, Model model) {
		logger.info("Settings");
		return "redirect:/#";
	}
	
}
