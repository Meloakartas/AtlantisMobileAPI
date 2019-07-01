package restapi.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CalculatedMetric {
    private Long metric_Calculation_Type_ID;
    private String devicemacaddress;
    private String dateStart;
    private String dateEnd;
    private String calculated_Metric_Value;

    public CalculatedMetric()
    {

    }

    public CalculatedMetric(Long metric_Calculation_Type_ID, String devicemacaddress, String dateStart, String dateEnd, String calculated_Metric_Value)
    {
        this.metric_Calculation_Type_ID = metric_Calculation_Type_ID;
        this.devicemacaddress = devicemacaddress;
        this.dateStart = dateStart;
        this.dateEnd = dateEnd;
        this.calculated_Metric_Value = calculated_Metric_Value;
    }

    public Long getMetric_Calculation_Type_ID() {
        return metric_Calculation_Type_ID;
    }

    public void setMetric_Calculation_Type_ID(Long metric_Calculation_Type_ID) {
        this.metric_Calculation_Type_ID = metric_Calculation_Type_ID;
    }

    public String getDevicemacaddress() {
        return devicemacaddress;
    }

    public void setDevicemacaddress(String devicemacaddress) {
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

    public String getCalculated_Metric_Value() {
        return calculated_Metric_Value;
    }

    public void setCalculated_Metric_Value(String calculated_Metric_Value) {
        this.calculated_Metric_Value = calculated_Metric_Value;
    }
}
