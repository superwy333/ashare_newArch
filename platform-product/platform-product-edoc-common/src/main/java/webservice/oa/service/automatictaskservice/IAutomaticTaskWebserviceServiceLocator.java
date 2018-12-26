/**
 * IAutomaticTaskWebserviceServiceLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package webservice.oa.service.automatictaskservice;

public class IAutomaticTaskWebserviceServiceLocator extends org.apache.axis.client.Service implements webservice.oa.service.automatictaskservice.IAutomaticTaskWebserviceService {

    public IAutomaticTaskWebserviceServiceLocator() {
    }


    public IAutomaticTaskWebserviceServiceLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public IAutomaticTaskWebserviceServiceLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for IAutomaticTaskWebservicePort
    private java.lang.String IAutomaticTaskWebservicePort_address = "http://testcoa.newhopegroup.com:8080/sys/webservice/automaticTaskWebservice";

    public java.lang.String getIAutomaticTaskWebservicePortAddress() {
        return IAutomaticTaskWebservicePort_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String IAutomaticTaskWebservicePortWSDDServiceName = "IAutomaticTaskWebservicePort";

    public java.lang.String getIAutomaticTaskWebservicePortWSDDServiceName() {
        return IAutomaticTaskWebservicePortWSDDServiceName;
    }

    public void setIAutomaticTaskWebservicePortWSDDServiceName(java.lang.String name) {
        IAutomaticTaskWebservicePortWSDDServiceName = name;
    }

    public webservice.oa.service.automatictaskservice.IAutomaticTaskWebservice getIAutomaticTaskWebservicePort() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(IAutomaticTaskWebservicePort_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getIAutomaticTaskWebservicePort(endpoint);
    }

    public webservice.oa.service.automatictaskservice.IAutomaticTaskWebservice getIAutomaticTaskWebservicePort(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            webservice.oa.service.automatictaskservice.IAutomaticTaskWebserviceServiceSoapBindingStub _stub = new webservice.oa.service.automatictaskservice.IAutomaticTaskWebserviceServiceSoapBindingStub(portAddress, this);
            _stub.setPortName(getIAutomaticTaskWebservicePortWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setIAutomaticTaskWebservicePortEndpointAddress(java.lang.String address) {
        IAutomaticTaskWebservicePort_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (webservice.oa.service.automatictaskservice.IAutomaticTaskWebservice.class.isAssignableFrom(serviceEndpointInterface)) {
                webservice.oa.service.automatictaskservice.IAutomaticTaskWebserviceServiceSoapBindingStub _stub = new webservice.oa.service.automatictaskservice.IAutomaticTaskWebserviceServiceSoapBindingStub(new java.net.URL(IAutomaticTaskWebservicePort_address), this);
                _stub.setPortName(getIAutomaticTaskWebservicePortWSDDServiceName());
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
        if ("IAutomaticTaskWebservicePort".equals(inputPortName)) {
            return getIAutomaticTaskWebservicePort();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("http://webservice.nhImg.fsext.kmss.landray.com/", "IAutomaticTaskWebserviceService");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("http://webservice.nhImg.fsext.kmss.landray.com/", "IAutomaticTaskWebservicePort"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("IAutomaticTaskWebservicePort".equals(portName)) {
            setIAutomaticTaskWebservicePortEndpointAddress(address);
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
