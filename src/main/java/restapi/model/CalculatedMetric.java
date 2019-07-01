package restapi.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CalculatedMetric {
    private Long Metric_Calculation_Type_ID;
    private String Devicemacaddress;
    private String DateStart;
    private String DateEnd;
    private String Calculated_Metric_Value;

    public CalculatedMetric()
    {

    }

    public CalculatedMetric(Long Metric_Calculation_Type_ID, String Devicemacaddress, String DateStart, String DateEnd, String Calculated_Metric_Value)
    {
        this.Metric_Calculation_Type_ID = Metric_Calculation_Type_ID;
        this.Devicemacaddress = Devicemacaddress;
        this.DateStart = DateStart;
        this.DateEnd = DateEnd;
        this.Calculated_Metric_Value = Calculated_Metric_Value;
    }

    public Long getMetric_Calculation_Type_ID() {
        return Metric_Calculation_Type_ID;
    }

    public void setMetric_Calculation_Type_ID(Long metric_Calculation_Type_ID) {
        Metric_Calculation_Type_ID = metric_Calculation_Type_ID;
    }

    public String getDevicemacaddress() {
        return Devicemacaddress;
    }

    public void setDevicemacaddress(String devicemacaddress) {
        Devicemacaddress = devicemacaddress;
    }

    public String getDateStart() {
        return DateStart;
    }

    public void setDateStart(String dateStart) {
        DateStart = dateStart;
    }

    public String getDateEnd() {
        return DateEnd;
    }

    public void setDateEnd(String dateEnd) {
        DateEnd = dateEnd;
    }

    public String getCalculated_Metric_Value() {
        return Calculated_Metric_Value;
    }

    public void setCalculated_Metric_Value(String calculated_Metric_Value) {
        Calculated_Metric_Value = calculated_Metric_Value;
    }
}
