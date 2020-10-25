package Project;

public class Message
{
	//-----INSTANCE VARIABLES----
	private int senderId;
	private String message;
	private boolean isRead;

	//--------------CONSTRUCTOR---------
	public Message(int senderId, String message)
	{
		this.senderId = senderId;
		this.message = message;
		this.isRead = false;
	}

	public boolean getIsRead()
	{
		return isRead;
	}

	public void viewedMessage()
	{
		isRead = true;
	}

	public String toString()
	{
		return "From " + senderId + ":\n" + message;
	}
}
