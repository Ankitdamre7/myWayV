
	package com.example.demo.service;

	import org.springframework.stereotype.Service;

import com.example.demo.config.JwtUtil;
import com.example.demo.entity.Booking;
	import com.example.demo.entity.User;
	import com.example.demo.repository.BookingRepo;
	import com.example.demo.repository.UserRepo;

	

	import java.util.List;
	import java.util.Optional;

	@Service
	public class BookingService {

        private final JwtUtil jwtUtil;

	    private final BookingRepo bookingRepo;
	    private final UserRepo userRepo;

	    public BookingService(BookingRepo bookingRepo, UserRepo userRepo, JwtUtil jwtUtil) {
	        this.bookingRepo = bookingRepo;
	        this.userRepo = userRepo;
	        this.jwtUtil = jwtUtil;
	    }

	    // ✅ Create Booking
	    public Booking createBooking(Booking booking, String header) {

	        if (header == null || !header.startsWith("Bearer ")) {
	            throw new RuntimeException("Invalid Authorization header");
	        }

	        String token = header.substring(7);

	        String email = null;
			try {
				email = jwtUtil.extractEmail(token);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

	        Optional<User> user = userRepo.findByEmail(email);

	        if (user.isEmpty()) {
	            throw new RuntimeException("User not found");
	        }

	        if ("DRIVER".equals(user.get().getDriverMode())) {
	            throw new RuntimeException("Switch to passenger mode");
	        }

	        booking.setPassengerEmail(email);

	        return bookingRepo.save(booking);
	    }

	    // ✅ Get All Bookings
	    public List<Booking> getAllBookings() {
	        return bookingRepo.findAll();
	    }
	}


