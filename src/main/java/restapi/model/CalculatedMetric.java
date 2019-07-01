package restapi.model;

public class CalculatedMetric {
    private Long metric_Calculation_Type_ID;
    private Long devicemacaddress;
    private String dateStart;
    private String dateEnd;
    private Double calculated_Metric_Value;

    public Long getMetric_Calculation_Type_ID() {
        return metric_Calculation_Type_ID;
    }

    public void setMetric_Calculation_Type_ID(Long metric_Calculation_Type_ID) {
        this.metric_Calculation_Type_ID = metric_Calculation_Type_ID;
    }

    public Long getDevicemacaddress() {
        return devicemacaddress;
    }

    public void setDevicemacaddress(Long devicemacaddress) {
        this.devicemacaddress = devicemacaddress;
    }

    public String getDateStart() {
        return dateStart;
    }

    public void setDateStart(String dateStart) {
        this.dateStart = dateStart;
    }

    public String getDateEnd() {
        return dateEnd;
    }

    public void setDateEnd(String dateEnd) {
        this.dateEnd = dateEnd;
    }

    public Double getCalculated_Metric_Value() {
        return calculated_Metric_Value;
    }

    public void setCalculated_Metric_Value(Double calculated_Metric_Value) {
        this.calculated_Metric_Value = calculated_Metric_Value;
    }
}
