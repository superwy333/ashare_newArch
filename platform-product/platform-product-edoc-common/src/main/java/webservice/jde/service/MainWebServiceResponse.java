
package webservice.jde.service;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;



@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "mainWebServiceResult"
})
@XmlRootElement(name = "mainWebServiceResponse")
public class MainWebServiceResponse {

    protected String mainWebServiceResult;


    public String getMainWebServiceResult() {
        return mainWebServiceResult;
    }


    public void setMainWebServiceResult(String value) {
        this.mainWebServiceResult = value;
    }

}
