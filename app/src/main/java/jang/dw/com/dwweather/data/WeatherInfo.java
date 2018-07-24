package jang.dw.com.dwweather.data;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Data-Object holding weather information.
 *
 * @author ISchwarz
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public final class WeatherInfo implements Serializable {

    @JsonProperty("dt")
    private ZonedDateTime dateTime;
    @JsonProperty("main")
    private TemperatureInfo temperatureInfo;
    @JsonProperty("weather")
    private List<MetaInfo> metaInfo = new ArrayList<>();
    @JsonProperty("rain")
    private PrecipitationInfo rainInfo = new PrecipitationInfo();
    @JsonProperty("snow")
    private PrecipitationInfo snowInfo = new PrecipitationInfo();
    @JsonProperty("clouds")
    private CloudInfo cloudInfo = new CloudInfo();

    public ZonedDateTime getDateTime() {
        return dateTime;
    }

    public TemperatureInfo getTemperatureInfo() {
        return temperatureInfo;
    }

    public PrecipitationInfo getRainInfo() {
        return rainInfo;
    }

    public PrecipitationInfo getSnowInfo() {
        return snowInfo;
    }

    public List<MetaInfo> getMetaInfo() {
        return metaInfo;
    }

    public CloudInfo getCloudInfo() {
        return cloudInfo;
    }

    @Override
    public String toString() {
        return "WeatherInfo{" +
                "dateTime=" + dateTime +
                ", temperatureInfo=" + temperatureInfo +
                ", metaInfo=" + metaInfo +
                ", rainInfo=" + rainInfo +
                ", snowInfo=" + snowInfo +
                ", cloudInfo=" + cloudInfo +
                '}';
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static final class TemperatureInfo implements Serializable {

        @JsonProperty("temp")
        private Temperature temperature;
        @JsonProperty("temp_min")
        private Temperature minimalTemperature;
        @JsonProperty("temp_max")
        private Temperature maximalTemperature;

        public Temperature getTemperature() {
            return temperature;
        }

        public Temperature getMinimalTemperature() {
            return minimalTemperature;
        }

        public Temperature getMaximalTemperature() {
            return maximalTemperature;
        }

        @Override
        public String toString() {
            return "Main{" +
                    "temperature=" + temperature +
                    ", minimalTemperature=" + minimalTemperature +
                    ", maximalTemperature=" + maximalTemperature +
                    '}';
        }
    }


    @JsonIgnoreProperties(ignoreUnknown = true)
    public static final class PrecipitationInfo implements Serializable {

        @JsonProperty("3h")
        private double value;

        public double getValue() {
            return value;
        }

        @Override
        public String toString() {
            return "PrecipitationInfo{" +
                    "value=" + value +
                    '}';
        }
    }


    @JsonIgnoreProperties(ignoreUnknown = true)
    public static final class MetaInfo implements Serializable {

        @JsonProperty("all")
        private int id;
        @JsonProperty("main")
        private String name;
        @JsonProperty("description")
        private String description;
        @JsonProperty("icon")
        private String icon;

        public int getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        public String getDescription() {
            return description;
        }

        public String getIcon() {
            return icon;
        }

        @Override
        public String toString() {
            return "MetaInfo{" +
                    "id=" + id +
                    ", name='" + name + '\'' +
                    ", description='" + description + '\'' +
                    ", icon='" + icon + '\'' +
                    '}';
        }
    }


    @JsonIgnoreProperties(ignoreUnknown = true)
    public static final class CloudInfo implements Serializable {

        @JsonProperty("all")
        private int percentage;

        public int getPercentage() {
            return percentage;
        }

        @Override
        public String toString() {
            return "CloudInfo{" +
                    "percentage=" + percentage +
                    '}';
        }
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static final class WindInfo implements Serializable {

        @JsonProperty("speed")
        private double speed;
        @JsonProperty("deg")
        private double direction;

        public double getSpeed() {
            return speed;
        }

        public double getDirection() {
            return direction;
        }

        @Override
        public String toString() {
            return "WindInfo{" +
                    "speed=" + speed +
                    ", direction=" + direction +
                    '}';
        }
    }

}
