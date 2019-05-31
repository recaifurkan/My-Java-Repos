package request;

import java.util.HashMap;
import java.util.Map;

public abstract class Request {

	Map<String, Object> parameters = new HashMap<String, Object>();

	String requestPath;

	public Request(String parametersText) {
		this.solveParameters(parametersText);
	}

	abstract void solveParameters(String parametersText);

	public String getRequestPath() {
		return requestPath;
	}

	public void setRequestPath(String requestPath) {
		this.requestPath = requestPath;
	}

	protected void splitParameters(String[] parameters) {
		for (String keyValue : parameters) {
			String[] keyAndValue = keyValue.split("=");
			if (keyAndValue.length > 1) {
				this.parameters.put(keyAndValue[0], keyAndValue[1]);
			}

		}

	}

}
