package com.airline.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.airline.model.Ticket;
import com.airline.model.User;



public interface TicketRepo extends JpaRepository<Ticket, Integer> {

	//custom finder method
		List<Ticket> findByUser(User user);
		
		List<Ticket> findByArrivedContaining(String title);
}
