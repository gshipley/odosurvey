package com.openshift.odosurvey.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.CrossOrigin;


@CrossOrigin(origins = "*")
@RestController
public class APIController {
	@Autowired
	private SurveyController surveyController;

	@RequestMapping("/survey")
	public void saveSurvey(@RequestParam(value = "survey_document") String surveyDocument) {
		surveyController.saveDocument(surveyDocument);
	}
}