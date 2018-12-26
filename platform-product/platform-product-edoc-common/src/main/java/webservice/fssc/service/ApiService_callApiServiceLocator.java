/**
 * ApiService_callApiServiceLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package webservice.fssc.service;

public class ApiService_callApiServiceLocator extends org.apache.axis.client.Service implements webservice.fssc.service.ApiService_callApiService {

    public ApiService_callApiServiceLocator() {
    }


    public ApiService_callApiServiceLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public ApiService_callApiServiceLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for BISSendPort
    private java.lang.String BISSendPort_address = "http://118.122.93.246:8082/NewHope/EAIServer?Invoke=BizSoapService%7CApiService%7CcallApi";

    public java.lang.String getBISSendPortAddress() {
        return BISSendPort_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String BISSendPortWSDDServiceName = "BISSendPort";

    public java.lang.String getBISSendPortWSDDServiceName() {
        return BISSendPortWSDDServiceName;
    }

    public void setBISSendPortWSDDServiceName(java.lang.String name) {
        BISSendPortWSDDServiceName = name;
    }

    public webservice.fssc.service.ApiService_callApi getBISSendPort() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(BISSendPort_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getBISSendPort(endpoint);
    }

    public webservice.fssc.service.ApiService_callApi getBISSendPort(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            webservice.fssc.service.ApiServiceStub _stub = new webservice.fssc.service.ApiServiceStub(portAddress, this);
            _stub.setPortName(getBISSendPortWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setBISSendPortEndpointAddress(java.lang.String address) {
        BISSendPort_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (webservice.fssc.service.ApiService_callApi.class.isAssignableFrom(serviceEndpointInterface)) {
                webservice.fssc.service.ApiServiceStub _stub = new webservice.fssc.service.ApiServiceStub(new java.net.URL(BISSendPort_address), this);
                _stub.setPortName(getBISSendPortWSDDServiceName());
                return _stub;
            }
        }
        catch (java.lang.Throwable t) {
            throw new javax.xml.rpc.ServiceException(t);
        }
        throw new javax.xml.rpc.ServiceException("There is no stub implementation for the interface:  " + (serviceEndpointInterface == null ? "null" : serviceEndpointInterface.getName()));
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(javax.xml.namespace.QName portName, Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        if (portName == null) {
            return getPort(serviceEndpointInterface);
        }
        java.lang.String inputPortName = portName.getLocalPart();
        if ("BISSendPort".equals(inputPortName)) {
            return getBISSendPort();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("http://com.eai.soapservice", "ApiService_callApiService");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("http://com.eai.soapservice", "BISSendPort"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("BISSendPort".equals(portName)) {
            setBISSendPortEndpointAddress(address);
        }
        else 
{ // Unknown Port Name
            throw new javax.xml.rpc.ServiceException(" Cannot set Endpoint Address for Unknown Port" + portName);
        }
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(javax.xml.namespace.QName portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        setEndpointAddress(portName.getLocalPart(), address);
    }

}
