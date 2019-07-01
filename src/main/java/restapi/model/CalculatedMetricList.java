package restapi.model;

import java.util.ArrayList;
import java.util.List;

public class CalculatedMetricList {
    private List<CalculatedMetric> calculatedMetrics;

    public CalculatedMetricList() {
        calculatedMetrics = new ArrayList<>();
    }

    public List<CalculatedMetric> getCalculatedMetrics() {
        return calculatedMetrics;
    }

    public void setCalculatedMetrics(List<CalculatedMetric> calculatedMetrics) {
        this.calculatedMetrics = calculatedMetrics;
    }

    // standard constructor and getter/setter
}