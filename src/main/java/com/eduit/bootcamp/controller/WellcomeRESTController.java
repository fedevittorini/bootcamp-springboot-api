package com.eduit.bootcamp.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.eduit.bootcamp.service.ClientService;
import com.fasterxml.jackson.databind.ObjectMapper;


@RestController
public class WellcomeRESTController {

	private ClientService clientSevice;
	
	public WellcomeRESTController(@Autowired ClientService theClientSevice) {
		this.clientSevice = theClientSevice;
	}
	
	private final List<String> buff = new ArrayList<>(); 
	
	@GetMapping("/salut")
    private ResponseEntity<String> getS() throws Exception {
		ObjectMapper om = new ObjectMapper();
		String out = "{\"attr\":"+ om.writeValueAsString(buff) +"}";
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(out);
    }
	
	@GetMapping("/wellcome")
    private String getWellcome() throws Exception {
		ObjectMapper om = new ObjectMapper();
        return "{\"attr\":"+ om.writeValueAsString(buff) +"}";
    }
	
    @GetMapping("/wellcome/{name}")
    private String getWellcomeName(@PathVariable String name){
        return "{\"attr\":\""+name+"\"}";
    }
    
    @PostMapping("/wellcome/{id}")
    private String createWellcome(@RequestBody String name) {
    	buff.add(name);
    	return "1";
    }

}
