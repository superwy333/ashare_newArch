
package webservice.jde.service;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;



@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "dbNsp",
    "sType",
    "sWhere"
})
@XmlRootElement(name = "mainWebService1")
public class MainWebService1 {

    @XmlElement(name = "DBNsp")
    protected String dbNsp;
    protected String sType;
    protected String sWhere;

    /**
     *
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDBNsp() {
        return dbNsp;
    }

    /**
     *
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDBNsp(String value) {
        this.dbNsp = value;
    }

    /**
     *
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSType() {
        return sType;
    }

    /**
     *
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSType(String value) {
        this.sType = value;
    }

    /**
     *
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSWhere() {
        return sWhere;
    }

    /**
     *
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSWhere(String value) {
        this.sWhere = value;
    }

}
