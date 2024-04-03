package com.airline.dto;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class TicketDto {
	
	
   private int id;
	
	private String arrived ;
	private String destination;
	private String status;
	private Date date;
	
	private UserDto user;

}
