package com.example.demo.controller;





import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import com.example.demo.entity.Ride;
import com.example.demo.repository.RideRepo;
import com.example.demo.service.RideService;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/rides")
@CrossOrigin(origins = "http://127.0.0.1:5500")

public class RideController {

@Autowired
RideService service;

@PostMapping("/publish")
public Ride publishRide(@RequestBody Ride ride){

return service.publishRide(ride);

}

@GetMapping("/search")
public ResponseEntity<?> searchRide(@RequestParam String query){

		

		    String url = "https://nominatim.openstreetmap.org/search?q=" 
		            + URLEncoder.encode(query, StandardCharsets.UTF_8)
		            + "&format=json&addressdetails=1";

		    RestTemplate restTemplate = new RestTemplate();
		    
		    HttpHeaders headers = new HttpHeaders();
		    headers.set("User-Agent", "WayV-App");

		    HttpEntity<String> entity = new HttpEntity<>(headers);

		    ResponseEntity<String> response = restTemplate.exchange(
		            url,
		            HttpMethod.GET,
		            entity,
		            String.class
		    );

		    return ResponseEntity.ok(response.getBody());
		}
@GetMapping("/searchRides")
public ResponseEntity<?> searchForRide(@RequestParam String from,@RequestParam String to){
	List<Ride> ride=service.searchRide(from, to);
   return ResponseEntity.status(HttpStatus.OK).body(ride);
}



@PostMapping("/book/{id}")
public Ride bookSeat(@PathVariable Long id){

return service.bookSeat(id);

}

}