
package webservice.jde.service;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;



@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "mainPdfWebServiceResult"
})
@XmlRootElement(name = "mainPdfWebServiceResponse")
public class MainPdfWebServiceResponse {

    protected byte[] mainPdfWebServiceResult;

    /**
     *
     * 
     * @return
     *     possible object is
     *     byte[]
     */
    public byte[] getMainPdfWebServiceResult() {
        return mainPdfWebServiceResult;
    }

    /**
     *
     * 
     * @param value
     *     allowed object is
     *     byte[]
     */
    public void setMainPdfWebServiceResult(byte[] value) {
        this.mainPdfWebServiceResult = value;
    }

}
