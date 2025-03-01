package com.kusumita.rest_api.microservice.versioning;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Schema;

@RestController
public class VersioningPersonController {

	// using path parameters
	@GetMapping("/person/v1")
	public PersonV1 getPersonVersion1() {
		return new PersonV1("Bob Charlie");
	}
	
	@GetMapping("/person/v2")
	public PersonV2 getPersonVersion2() {
		return new PersonV2(new Name("Bob", "Charlie"));
	}
	
	// using query parameters
	@GetMapping(path = "/person", params = "version=1")
	public PersonV1 getPersonVersion1PathParams(@RequestParam int version) {
		return new PersonV1("Bob Charlie");
	}
	
	@GetMapping(path = "/person", params = "version=2")
	public PersonV2 getPersonVersion2PathParams(@RequestParam int version) {
		return new PersonV2(new Name("Bob", "Charlie"));
	}
	
	// custom header
	@GetMapping(path = "/person/header", headers = "X-API-VERSION=1")
	public PersonV1 getPersonVersion1CustomHeader() {
		return new PersonV1("Bob Charlie");
	}
	
	@GetMapping(path = "/person/header", headers = "X-API-VERSION=2")
	public PersonV2 getPersonVersion2CustomHeader() {
		return new PersonV2(new Name("Bob", "Charlie"));
	}
	
	// accept header
	@GetMapping(path = "/person/accept-header", produces = "application/vnd.company.app-v1+json")
	public PersonV1 getPersonVersion1AcceptHeader() {
		return new PersonV1("Bob Charlie");
	}
	
	@GetMapping(path = "/person/accept-header", produces = "application/vnd.company.app-v2+json")
	public PersonV2 getPersonVersion2AcceptHeader() {
		return new PersonV2(new Name("Bob", "Charlie"));
	}	

}

