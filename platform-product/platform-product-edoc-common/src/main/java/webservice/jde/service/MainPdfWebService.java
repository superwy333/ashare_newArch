
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
@XmlRootElement(name = "mainPdfWebService")
public class MainPdfWebService {

    @XmlElement(name = "DBNsp")
    protected String dbNsp;
    protected String sType;
    protected String sWhere;


    public String getDBNsp() {
        return dbNsp;
    }


    public void setDBNsp(String value) {
        this.dbNsp = value;
    }


    public String getSType() {
        return sType;
    }


    public void setSType(String value) {
        this.sType = value;
    }


    public String getSWhere() {
        return sWhere;
    }


    public void setSWhere(String value) {
        this.sWhere = value;
    }

}
