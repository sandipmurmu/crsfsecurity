package com.security.ws;

import java.security.Principal;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/rest")
@RestController
public class UserWebService {

	
	@RequestMapping(value = "/hello", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<HelloweenResponse> hello(Principal principal) {
		HttpHeaders headers = new HttpHeaders();
		
		return new ResponseEntity<HelloweenResponse>(
				new HelloweenResponse("Happy Halloween, " + principal.getName() + "!"), headers, HttpStatus.OK);
	}

	@RequestMapping(value = "/hellopost", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<HelloweenResponse> helloPost(Principal principal) {

		return new ResponseEntity<HelloweenResponse>(
			new HelloweenResponse("Happy posting, " + principal.getName() + "!"), HttpStatus.OK);
	}

	public static class HelloweenResponse {
		private String message;
		public HelloweenResponse(String message) {
			this.message = message;
		}
		public String getMessage() {
			return message;
		}
		public void setMessage(String message) {
			this.message = message;
		}
	}
	
	/*@RequestMapping(value="/user", method=RequestMethod.POST, consumes="application/json", produces="application/json")
	public ResponseEntity<Map<String,String>> createUser(@RequestBody Map<String,String> requestAsJson){
		HttpHeaders headers = new HttpHeaders();
		Map<String,String> response = new HashMap<String, String>();
		response.put("username", requestAsJson.get("username"));
		response.put("password", requestAsJson.get("password"));
		
		return new ResponseEntity<Map<String,String>>(response, headers, HttpStatus.OK);
	}
	
	
	@RequestMapping(value="/user/{userId}", method=RequestMethod.GET, consumes="application/json", produces="application/json")
	public ResponseEntity<Map<String,String>> getUser(@PathVariable("userId")String requestAsJson){
		HttpHeaders headers = new HttpHeaders();
		Map<String,String> response = new HashMap<String, String>();
		response.put("username", "sandip murmu");
		response.put("password", "password");
		
		return new ResponseEntity<Map<String,String>>(response, headers, HttpStatus.OK);
	}*/

}
