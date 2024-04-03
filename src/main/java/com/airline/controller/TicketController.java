package com.airline.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.airline.config.AppConstant;
import com.airline.dto.ApiResponse;
import com.airline.dto.TicketDto;
import com.airline.dto.TicketReponse;
import com.airline.service.TicketService;




@RestController
@RequestMapping("/ticket")
public class TicketController {
	
	@Autowired
	private TicketService ticketService;
	
	
	
	
	//create ticket
		@PostMapping("/user/{id}/tickets")
		public ResponseEntity<TicketDto> createTicket(@RequestBody TicketDto ticketDto,
				                                  @PathVariable Integer id)
				                                  
		{
			
			TicketDto createTicket=this.ticketService.createTicket(ticketDto, id);
			
			return new ResponseEntity<TicketDto>(createTicket,HttpStatus.CREATED) ;
			
		}
	
	//update ticket
	
	@PutMapping("/tickets/{id}")
	public ResponseEntity<TicketDto> updatePost(@RequestBody TicketDto ticketDto,@PathVariable Integer id)
	{
		TicketDto updateTicket=this.ticketService.updateTicket(ticketDto, id);
		
		return new ResponseEntity<TicketDto>(updateTicket,HttpStatus.OK);
	}
	
	//delete ticket
	
	@DeleteMapping("/tickets/{id}")
	public ApiResponse deleteTicket(@PathVariable Integer id)
	{
		this.ticketService.delete(id);
		return  new ApiResponse("Ticket successfully deleted !!!",true);
	}
	
	//get all ticket
	@GetMapping("/tickets")
	public ResponseEntity <TicketReponse> getAllTicket(@RequestParam(value="pageNumber",defaultValue = AppConstant.PAGE_NUMBER,required = false) Integer pageNumber,
			                                         @RequestParam(value = "pageSize",defaultValue = AppConstant.PAGE_SIZE,required = false) Integer pageSize,
			                                         @RequestParam (value="sortBy",defaultValue = AppConstant.SORT_BY,required = false) String sortBy,
			                                         @RequestParam (value="sortByDir",defaultValue = AppConstant.SORT_BY_DIR ,required = false) String sortByDir)
	{
	TicketReponse ticketReponse=this.ticketService.getAllTicket(pageNumber,pageSize,sortBy,sortByDir);
		return new ResponseEntity<TicketReponse>(ticketReponse,HttpStatus.OK);
	}
	//get single ticket
	@GetMapping("ticket/{id}")
	public ResponseEntity<TicketDto> getTicket(@PathVariable Integer id)
	{
		TicketDto ticket=this.ticketService.getTicketById(id);
		return new ResponseEntity<TicketDto>(ticket,HttpStatus.OK);
	}
	
	
	//get ticket by user
	@GetMapping("/user/{id}/tickets")
	public ResponseEntity<List<TicketDto>> getTicketByUser(@PathVariable Integer id)
	{
		List<TicketDto> ticket=this.ticketService.getTicketByUser(id);
		return new ResponseEntity<List<TicketDto>>(ticket,HttpStatus.OK);
	}
	//search ticket
		@GetMapping("/tickets/search/{keywords}")
		public ResponseEntity<List<TicketDto>> searchTicketByArrived(@PathVariable("keywords")String keywords)
		{
			List<TicketDto> searchTicket=this.ticketService.searchTicket(keywords);
			return new ResponseEntity<List<TicketDto>>(searchTicket,HttpStatus.OK);
			
		}

}
