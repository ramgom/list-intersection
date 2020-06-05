package com.ramgom.listintersection;

import com.ramgom.listintersection.calculator.IntersectionCalculatorResponse;
import com.ramgom.listintersection.calculator.IntersectionCalculatorService;
import com.ramgom.listintersection.intersections.HashIntersectionFunction;
import com.ramgom.listintersection.intersections.HashIntersectionList;
import com.ramgom.listintersection.intersections.IntersectionFunction;
import com.ramgom.listintersection.random.RandomsGeneratorFactory;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TextFormatter;
import javafx.scene.text.Text;
import lombok.RequiredArgsConstructor;

import java.util.Map;
import java.util.regex.Pattern;

@RequiredArgsConstructor
public class ListIntersectionController {

    private static final Pattern DIGITS_PATTERN = Pattern.compile("\\d{1,9}");
    private static final int SPINNER_MIN = 0;
    private static final int SPINNER_MAX = Integer.MAX_VALUE;
    private static final int SPINNER_STEP = 10;

    private IntersectionCalculatorService calculatorService;
    private Map<String, IntersectionFunction> intersectionFunctions;

    @FXML
    private ComboBox<String> listToPutInHash;

    @FXML
    private Spinner<Integer> firstListSize;

    @FXML
    private Spinner<Integer> secondListSize;

    @FXML
    private Text intersectionSize;

    @FXML
    private Text calculationTime;

    @FXML
    protected void initialize() {
        calculatorService = new IntersectionCalculatorService(RandomsGeneratorFactory.getLocalRandomGenerator());

        intersectionFunctions = Map.of(
                HashIntersectionList.FIRST.getReadableValue(), new HashIntersectionFunction(HashIntersectionList.FIRST),
                HashIntersectionList.SECOND.getReadableValue(), new HashIntersectionFunction(HashIntersectionList.SECOND)
        );
        listToPutInHash.getItems().setAll(HashIntersectionList.FIRST.getReadableValue(), HashIntersectionList.SECOND.getReadableValue());

        listToPutInHash.setValue(HashIntersectionList.FIRST.getReadableValue());

        initializeSpinner(firstListSize);
        initializeSpinner(secondListSize);
    }

    protected void initializeSpinner(Spinner<Integer> spinner) {

        SpinnerValueFactory.IntegerSpinnerValueFactory valueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(SPINNER_MIN, SPINNER_MAX, SPINNER_MIN, SPINNER_STEP);

        spinner.setValueFactory(valueFactory);

        spinner.getEditor().setTextFormatter(new TextFormatter<>(this::spinnerFilter));
    }

    protected TextFormatter.Change spinnerFilter(TextFormatter.Change change) {
        if (DIGITS_PATTERN.matcher(change.getControlNewText()).matches()) {
            return change;
        } else {
            return null;
        }
    }

    @FXML
    protected void handleCalculateIntersection(ActionEvent event) {
        IntersectionFunction intersectionFunction = intersectionFunctions.get(listToPutInHash.getValue());

        IntersectionCalculatorResponse calculatorResponse = calculatorService.calculateIntersection(firstListSize.getValue(), secondListSize.getValue(), intersectionFunction);

        intersectionSize.setText(Integer.toString(calculatorResponse.getIntersectionSize()));
        calculationTime.setText(Long.toString(calculatorResponse.getTimeToCalculateInMilliseconds()));
    }
}
