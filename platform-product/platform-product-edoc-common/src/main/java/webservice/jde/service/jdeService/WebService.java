/**
 * WebService.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package webservice.jde.service.jdeService;

public interface WebService extends javax.xml.rpc.Service {
    public java.lang.String getWebServiceSoapAddress();

    public webservice.jde.service.jdeService.WebServiceSoap_PortType getWebServiceSoap() throws javax.xml.rpc.ServiceException;

    public webservice.jde.service.jdeService.WebServiceSoap_PortType getWebServiceSoap(java.net.URL portAddress) throws javax.xml.rpc.ServiceException;
}
