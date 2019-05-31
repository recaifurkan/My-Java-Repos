package messages;


import java.util.Date;


import security.EncryptedFileWriter;

public class MessageSaver {
	
	StringBuilder message = new StringBuilder();
	float keyPressCount = 0;
	EncryptedFileWriter writer = new EncryptedFileWriter("message.txt");
	
	
	
	public StringBuilder getMessage() {
		return message;
	}
	public void setMessage(StringBuilder message) {
		this.message = message;
	}
	
	public void addMessage(String message) {
		this.message.append(message + "\n");
		keyPressCount += 0.5f;
		if(keyPressCount == 5) {
			this.message.append(new Date()+"\n");
			writer.write(this.message.toString());
			this.message = new StringBuilder();
			keyPressCount = 0;
		}
		
		
	}
	
	
	

}
