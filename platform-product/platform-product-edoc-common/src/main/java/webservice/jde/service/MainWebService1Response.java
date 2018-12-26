
package webservice.jde.service;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;



@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "mainWebService1Result"
})
@XmlRootElement(name = "mainWebService1Response")
public class MainWebService1Response {

    protected byte[] mainWebService1Result;


    public byte[] getMainWebService1Result() {
        return mainWebService1Result;
    }


    public void setMainWebService1Result(byte[] value) {
        this.mainWebService1Result = value;
    }

}
