package com.example.demo.repository;



import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.Ride;

import java.util.List;

public interface RideRepo extends JpaRepository<Ride,Long>{

List<Ride> findByFromCityAndToCity(String fromCity,String toCity);

}