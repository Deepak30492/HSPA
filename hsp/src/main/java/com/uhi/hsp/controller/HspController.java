package com.uhi.hsp.controller;


import com.dhp.sdk.beans.OnTBody;
import com.uhi.hsp.dto.EuaRequestBody;
import com.uhi.hsp.dto.HspRequestBody;
import com.uhi.hsp.dto.OnSelect;
import com.uhi.hsp.service.StatusService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;

@RestController
@RequestMapping("/HSP")
public class HspController {

	public static final String GATEWAY_URL = "http://localhost:4030/bg";
	@Value("${spring.application.resp_json}")
	String resp_json;

	@Value("${spring.application.resp_ack}")
	String resp_ack;

	final
	RestTemplate restTemplate;

	public HspController(RestTemplate restTemplate, StatusService service) {
		this.restTemplate = restTemplate;
		this.service = service;
	}

	private final StatusService service;

	@PostMapping(value="/search", consumes = "APPLICATION/JSON",produces = "APPLICATION/JSON")
	public ResponseEntity<EuaRequestBody> search(@RequestBody HspRequestBody req) throws IOException {
		EuaRequestBody body = service.mapSearch(req);
		//HttpHeaders headers = new HttpHeaders();
		//headers.setContentType(MediaType.APPLICATION_JSON);
		//HttpEntity<EuaRequestBody> httpEntity = new HttpEntity<>(body, headers);
		return ResponseEntity.status(HttpStatus.OK).body(body);
//		return restTemplate.postForEntity(GATEWAY_URL + "/on_search", httpEntity, String.class);
	}

	@PostMapping(value="/select",consumes = "APPLICATION/JSON",produces = "APPLICATION/JSON")
	public ResponseEntity<OnTBody> select(@RequestBody HspRequestBody req) throws IOException {
		OnTBody body = service.mapSelect(req);
		System.out.println("****"+req);
		return ResponseEntity.status(HttpStatus.OK).body(body);
//		return ResponseEntity.status(HttpStatus.OK).body(resp_ack);

	}

	@PostMapping("/init")
	public ResponseEntity<String> init(@RequestBody HspRequestBody req) throws IOException {
		OnTBody body = service.mapInit(req);
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<OnTBody> httpEntity = new HttpEntity<>(body, headers);
		restTemplate.postForEntity("http://localhost:8090/on_init", httpEntity, OnTBody.class);
		return ResponseEntity.status(HttpStatus.OK).body(resp_ack);

	}

	@PostMapping("/confirm")
	public ResponseEntity<String> confirm(@RequestBody HspRequestBody req) throws IOException {
		OnTBody body = service.mapConfirm(req);
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<OnTBody> httpEntity = new HttpEntity<>(body, headers);
		restTemplate.postForEntity("http://localhost:8090/on_confirm", httpEntity, OnTBody.class);
		return ResponseEntity.status(HttpStatus.OK).body(resp_ack);

	}

	@PostMapping("/status")
	public ResponseEntity<String> status(@RequestBody HspRequestBody req) throws IOException {
		OnTBody body = service.mapStatus(req);
		HttpEntity<OnTBody> httpEntity = new HttpEntity<>(body);
		restTemplate.postForEntity("http://localhost:8090/on_status", httpEntity, OnTBody.class);
		return ResponseEntity.status(HttpStatus.OK).body(resp_ack);
	}



}
