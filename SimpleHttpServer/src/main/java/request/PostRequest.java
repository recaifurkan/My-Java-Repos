package request;

public class PostRequest extends Request {

	public PostRequest(String parametersText) {
		super(parametersText);
	}

	@Override
	void solveParameters(String parametersText) {
		String[] parameters = parametersText.split("&");
		splitParameters(parameters);

	}

}
