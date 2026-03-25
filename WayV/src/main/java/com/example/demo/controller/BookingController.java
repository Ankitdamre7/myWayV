package com.example.demo.controller;

import org.springframework.web.bind.annotation.*;

import com.example.demo.entity.Booking;
import com.example.demo.service.BookingService;

import java.util.List;

@RestController
@RequestMapping("/bookings")
public class BookingController {

    private final BookingService bookingService;

    public BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    @PostMapping
    public Booking createBooking(
            @RequestBody Booking booking,
            @RequestHeader("Authorization") String header) {

        return bookingService.createBooking(booking, header);
    }

    @GetMapping
    public List<Booking> getBookings() {
        return bookingService.getAllBookings();
    }
}