package space;

import java.io.IOException;

import pixeng.Connection;
import pixeng.NetListener;
import pixeng.PixEng;
import pixeng.SClient;

public class NetPingHandler implements NetListener
{
	@Override
	public void onConnect(Connection connection) throws IOException 
	{
		System.out.println("Client Connected");
	}

	@Override
	public void onReceive(Object object, Connection connection) throws IOException 
	{
		if(object instanceof NetFriend)
		{
			for(SClient c : PixEng.getServer().getSClient())
			{
				c.getConnection().oos.writeObject(object);
			}
		}
	}
}
