package hauke.aufgabe.problem;


public class EvaluationException extends RuntimeException {

    public EvaluationException() {
        super("Evaluation failed.");
    }

    public EvaluationException(String message) {
        super("Evaluation failed. " + message);
    }

}
