package net;

import net.specificsenders.GetSender;
import net.specificsenders.PostSender;

public class Net {

	PostSender postSender;
	GetSender getSender;

	public Net() {

		this.postSender = new PostSender();
		this.getSender = new GetSender();
	}

	public PostSender getPostSender() {
		return postSender;
	}

	public GetSender getGetSender() {
		return getSender;
	}

}
