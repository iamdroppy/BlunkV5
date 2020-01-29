package com.suelake.habbo.communication.requests;

import com.suelake.habbo.communication.ClientMessage;
import com.suelake.habbo.communication.ClientRequestHandler;
import com.suelake.habbo.communication.CommunicationHandler;

public class TRADE_CLOSE implements ClientRequestHandler
{
	public void handle(ClientMessage msg, CommunicationHandler comm)
	{
		comm.stopTrading();
	}
}
