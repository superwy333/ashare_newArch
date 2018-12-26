/**
 * WebServiceSoap_PortType.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package webservice.jde.service.jdeService;

public interface WebServiceSoap_PortType extends java.rmi.Remote {

    /**
     * 程序入口
     */
    public java.lang.String mainWebService(java.lang.String DBNsp, java.lang.String sType, java.lang.String sWhere) throws java.rmi.RemoteException;

    /**
     * PDF程序入口
     */
    public byte[] mainPdfWebService(java.lang.String DBNsp, java.lang.String sType, java.lang.String sWhere) throws java.rmi.RemoteException;
}
