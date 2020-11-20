
package pl.jsystems.qa.qaapi.model.usersdevice;

import com.fasterxml.jackson.annotation.JsonProperty;
import pl.jsystems.qa.qaapi.model.usersdevice.Device;

import java.util.List;

public class User {

    @JsonProperty(required = true)
    public String imie;

    @JsonProperty(required = true)
    public String nazwisko;

    @JsonProperty(required = true)
    public List<Device> device;


    @Override
    public String toString() {
        return "User{" +
                "imie='" + imie + '\'' +
                ", nazwisko='" + nazwisko + '\'' +
                ", device=" + device +
                '}';
    }
}


