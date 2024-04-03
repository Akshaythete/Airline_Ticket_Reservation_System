package com.airline.service.serviceImpl;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.airline.dto.TicketDto;
import com.airline.dto.TicketReponse;
import com.airline.exception.ResourceNotFoundException;
import com.airline.model.Ticket;
import com.airline.model.User;
import com.airline.repo.TicketRepo;
import com.airline.repo.UserRepo;
import com.airline.service.TicketService;



@Service
public class TicketServiceImpl implements TicketService {

	@Autowired
	private TicketRepo ticketRepo;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private UserRepo userRepo;
	
	
	
	
	//create post
		@Override
		public TicketDto createTicket(TicketDto ticketDto,Integer id)
		{ 
			User user=this.userRepo.findById(id).orElseThrow(()-> new ResourceNotFoundException("User", " id", id));
			
			 
			
			
			
			Ticket ticket=this.modelMapper.map(ticketDto, Ticket.class);
			
			ticket.setAddDate(new Date());
			ticket.setUser(user);
			
			
			 Ticket newTicket=this.ticketRepo.save(ticket);
			
			
			return this.modelMapper.map(newTicket, TicketDto.class);
		}

	//update ticket
	
	@Override
	public TicketDto updateTicket(TicketDto ticketDto, Integer id) {
		Ticket ticket = this.ticketRepo.findById(id).orElseThrow(()->new ResourceNotFoundException("Ticket", " id", id));
		
		ticket.setArrived(ticketDto.getArrived());
		ticket.setDestination(ticketDto.getDestination());
		ticket.setStatus(ticketDto.getStatus());
		
		Ticket updatedTicket=this.ticketRepo.save(ticket);
		
		return this.modelMapper.map(updatedTicket, TicketDto.class);
	}

	//delete ticket
	
	@Override
	public void delete(Integer id) {
		Ticket ticket=this.ticketRepo.findById(id).orElseThrow(()->new ResourceNotFoundException("Ticket", "id", id));
		this.ticketRepo.delete(ticket);
	}

	//get single ticket
	@Override
	public TicketDto getTicketById(Integer id) {
		Ticket ticket=this.ticketRepo.findById(id).orElseThrow(()->new ResourceNotFoundException("Ticket", "id", id));
		return this.modelMapper.map(ticket, TicketDto.class);
	}

	
	//get all ticket
	@Override
	public TicketReponse getAllTicket(Integer pageNumber,Integer pageSize,String sortBy,String sortByDir) 
	{  
		Sort sort=null;
		
		if(sortByDir.equalsIgnoreCase("asc"))
		{
			sort=Sort.by(sortBy).ascending();
		}
		else {
			sort=Sort.by(sortBy).descending();
		}
		//pagination
		
		 Pageable p=PageRequest.of(pageNumber, pageSize,sort); 
				 //Sort.by(sortBy));
	    Page<Ticket> pageTicket	= this.ticketRepo.findAll(p);
	    List<Ticket> allTickets=pageTicket.getContent();
	
		
		List<TicketDto> ticketDto=allTickets.stream().map((ticket)->this.modelMapper.map(ticket, TicketDto.class)).collect(Collectors.toList());
		
		TicketReponse ticketReponse=new TicketReponse();
		ticketReponse.setContent(ticketDto);
		ticketReponse.setPageNumber(pageTicket.getNumber());
		ticketReponse.setPageSize(pageTicket.getSize());
		ticketReponse.setTotalElement(pageTicket.getTotalElements());
		ticketReponse.setTotalPages(pageTicket.getTotalPages());
		ticketReponse.setLastPage(pageTicket.isLast());
		
		return ticketReponse;
	}
     //get ticket by user
	@Override
	public List<TicketDto> getTicketByUser(Integer id) {
		
		User user=this.userRepo.findById(id).orElseThrow(()->new ResourceNotFoundException("User", "id", id));
		
		Optional<Ticket> tickets=this.ticketRepo.findById(id);
		  List<TicketDto> ticketDto=tickets.stream().map((ticket)->this.modelMapper.map(ticket, TicketDto.class)).collect(Collectors.toList());
		return ticketDto;
	}
	
	
	
	//search ticket
	

		@Override
		public List<TicketDto> searchTicket(String keyword) {
			List<Ticket>tickets=this.ticketRepo.findByArrivedContaining(keyword);
			List<TicketDto> ticketDto= tickets.stream().map((ticket)->this.modelMapper.map(ticket, TicketDto.class)).collect(Collectors.toList());
			return ticketDto ;
		
		}

	
	
}
