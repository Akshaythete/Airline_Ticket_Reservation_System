package com.airline.service;

import java.util.List;

import com.airline.dto.TicketDto;
import com.airline.dto.TicketReponse;




public interface TicketService {
	
	//create ticket
	
		public TicketDto createTicket(TicketDto ticketDto,Integer id);
		
		//update ticket
		public TicketDto updateTicket(TicketDto ticketDto,Integer id);
		
		//delete ticket
		public void delete(Integer id);
		
		//get single ticket
		public TicketDto getTicketById(Integer id);
		
		//getAllTicket
		public TicketReponse getAllTicket(Integer pageNumber,Integer pageSize,String sortBy,String sortByDir);
		
		//getTicket
		
		public List<TicketDto> getTicketByUser(Integer id);
		
		//search post
		
		public List<TicketDto> searchTicket(String keyword);
		

}
