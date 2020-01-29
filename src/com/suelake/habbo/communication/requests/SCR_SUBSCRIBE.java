package com.suelake.habbo.communication.requests;

import com.suelake.habbo.HabboHotel;
import com.suelake.habbo.communication.ClientCommands;
import com.suelake.habbo.communication.ClientMessage;
import com.suelake.habbo.communication.ClientRequestHandler;
import com.suelake.habbo.communication.CommunicationHandler;
import com.suelake.habbo.users.User;

public class SCR_SUBSCRIBE implements ClientRequestHandler
{
	public void handle(ClientMessage msg, CommunicationHandler comm)
	{
		// These are hardcoded magic numbers!
		final short days = 31;
		final short cost = 150;
		
		// Get user object
		User usr = comm.getUserObject();
		
		// Check if user can afford this
		if(usr.credits >= cost && !usr.isHC())
		{
			// Charge user
			usr.credits -= cost;
			
			// Add the days
			usr.updateLastHcUpdate(); // No more subtraction than needed!
			usr.hcDaysTotal += days;
			usr.calculateHC(true);
			
			// Call the handlers that update credits + HC info for client
			comm.getRequestHandlers().callHandler(ClientCommands.GETCREDITS);
			comm.getRequestHandlers().callHandler(ClientCommands.GETAVAILABLESETS);
			comm.getRequestHandlers().callHandler(ClientCommands.SCR_GINFO);
			
			// Update user object
			HabboHotel.getUserRegister().updateUser(usr);
		}
		else
		{
			comm.systemMsg("You do not have enough credits to purchase that item! (cost: " + cost + " credits)");
		}
	}
}
