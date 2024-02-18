package controller;

import domain.Car;
import domain.Service;
import java.util.List;
import view.InputView;
import view.OutputView;

public class Controller {
    private final Service service;
    private final OutputView outputView;
    private final InputView inputView;

    public Controller(Service service, OutputView outputView, InputView inputView) {
        this.service = service;
        this.outputView = outputView;
        this.inputView = inputView;
    }

    public void run() {
        inputCarName();
        int inputAttemptLimit = inputValue();
        service.playGame(inputAttemptLimit);
        List<String> winners = service.getWinnerName();
        outputView.printWinners(winners);
    }

    private List<Car> inputCarName() {
        try {
            String inputCarName = inputView.requestCarName();
            return service.setCars(service.separateCarName(inputCarName));
        } catch (IllegalArgumentException e) {
            outputView.printErrorMessage(e.getMessage());
            return inputCarName();
        }
    }

    private int inputValue() {
        String inputAttemptLimit = inputView.requestAttemptLimit();
        return validateNum(inputAttemptLimit);
    }

    private int validateNum(String inputValue) {
        try {

            int inputAttemptLimit = validateInputAttemptLimit(inputValue);
            validateNumber(inputAttemptLimit);
            return inputAttemptLimit;
        } catch (IllegalArgumentException e) {
            outputView.printErrorMessage(e.getMessage());
            return inputValue();
        }
    }

    public int validateInputAttemptLimit(String inputValue) {
        try {
            return Integer.parseInt(inputValue);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("[ERROR] 시도 횟수는 정수여야 합니다.");
        }
    }

    public void validateNumber(int inputAttemptLimit) {
        if (inputAttemptLimit <= 0) {
            throw new IllegalArgumentException("[ERROR] 시도 횟수는 양수여야 합니다.");
        }
    }
}
