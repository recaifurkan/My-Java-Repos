package request;

public class GetRequest extends Request {

	public GetRequest(String parametersText) {
		super(parametersText);
	}

	void solveParameters(String parametersText) {
		String[] parameters = parametersText.split("&");
		splitParameters(parameters);
	}

}
