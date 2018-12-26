/**
 * ILbpmextWebserviceServiceServiceLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package webservice.oa.service;

public class ILbpmextWebserviceServiceServiceLocator extends org.apache.axis.client.Service implements webservice.oa.service.ILbpmextWebserviceServiceService {

    public ILbpmextWebserviceServiceServiceLocator() {
    }


    public ILbpmextWebserviceServiceServiceLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public ILbpmextWebserviceServiceServiceLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for ILbpmextWebserviceServicePort
    private java.lang.String ILbpmextWebserviceServicePort_address = "http://testcoa.newhopegroup.com:8080/sys/webservice/lbpmextWebserviceService";

    public java.lang.String getILbpmextWebserviceServicePortAddress() {
        return ILbpmextWebserviceServicePort_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String ILbpmextWebserviceServicePortWSDDServiceName = "ILbpmextWebserviceServicePort";

    public java.lang.String getILbpmextWebserviceServicePortWSDDServiceName() {
        return ILbpmextWebserviceServicePortWSDDServiceName;
    }

    public void setILbpmextWebserviceServicePortWSDDServiceName(java.lang.String name) {
        ILbpmextWebserviceServicePortWSDDServiceName = name;
    }

    public webservice.oa.service.ILbpmextWebserviceService getILbpmextWebserviceServicePort() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(ILbpmextWebserviceServicePort_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getILbpmextWebserviceServicePort(endpoint);
    }

    public webservice.oa.service.ILbpmextWebserviceService getILbpmextWebserviceServicePort(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            webservice.oa.service.ILbpmextWebserviceServiceServiceSoapBindingStub _stub = new webservice.oa.service.ILbpmextWebserviceServiceServiceSoapBindingStub(portAddress, this);
            _stub.setPortName(getILbpmextWebserviceServicePortWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setILbpmextWebserviceServicePortEndpointAddress(java.lang.String address) {
        ILbpmextWebserviceServicePort_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (webservice.oa.service.ILbpmextWebserviceService.class.isAssignableFrom(serviceEndpointInterface)) {
                webservice.oa.service.ILbpmextWebserviceServiceServiceSoapBindingStub _stub = new webservice.oa.service.ILbpmextWebserviceServiceServiceSoapBindingStub(new java.net.URL(ILbpmextWebserviceServicePort_address), this);
                _stub.setPortName(getILbpmextWebserviceServicePortWSDDServiceName());
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
        if ("ILbpmextWebserviceServicePort".equals(inputPortName)) {
            return getILbpmextWebserviceServicePort();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("http://lpmext.fsext.kmss.landray.com/", "ILbpmextWebserviceServiceService");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("http://lpmext.fsext.kmss.landray.com/", "ILbpmextWebserviceServicePort"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("ILbpmextWebserviceServicePort".equals(portName)) {
            setILbpmextWebserviceServicePortEndpointAddress(address);
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
