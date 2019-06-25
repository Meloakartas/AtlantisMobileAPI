package restapi.model;

public class CalculatedMetric {
    private Long id;
    private Long deviceID;
    private Long metricTypeID;
    private String dateBegin;
    private String dateEnd;
    private Double value;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getDeviceID() {
        return deviceID;
    }

    public void setDeviceID(Long deviceID) {
        this.deviceID = deviceID;
    }

    public Long getMetricTypeID() {
        return metricTypeID;
    }

    public void setMetricTypeID(Long metricTypeID) {
        this.metricTypeID = metricTypeID;
    }

    public String getDateBegin() {
        return dateBegin;
    }

    public void setDateBegin(String dateBegin) {
        this.dateBegin = dateBegin;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    public String getDateEnd() {
        return dateEnd;
    }

    public void setDateEnd(String dateEnd) {
        this.dateEnd = dateEnd;
    }
}
