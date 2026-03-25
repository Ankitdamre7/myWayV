package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Ride;
import com.example.demo.repository.RideRepo;

import java.util.List;

@Service
public class RideService {

@Autowired
RideRepo repo;

public Ride publishRide(Ride ride){

return repo.save(ride);

}

public List<Ride> searchRide(String from,String to){

return repo.findByFromCityAndToCity(from,to);

}

public Ride bookSeat(Long id){

Ride ride=repo.findById(id).get();

if(ride.getSeats()>0){

ride.setSeats(ride.getSeats()-1);

repo.save(ride);

}

return ride;

}

}