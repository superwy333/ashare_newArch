<?xml version="1.0" encoding="utf-8"?>
<wsdl:definitions xmlns:tm="http://microsoft.com/wsdl/mime/textMatching/" xmlns:soapenc="http://schemas.xmlsoap.org/soap/encoding/" xmlns:mime="http://schemas.xmlsoap.org/wsdl/mime/" xmlns:tns="http://tempuri.org/" xmlns:soap="http://schemas.xmlsoap.org/wsdl/soap/" xmlns:s="http://www.w3.org/2001/XMLSchema" xmlns:soap12="http://schemas.xmlsoap.org/wsdl/soap12/" xmlns:http="http://schemas.xmlsoap.org/wsdl/http/" targetNamespace="http://tempuri.org/" xmlns:wsdl="http://schemas.xmlsoap.org/wsdl/">
  <wsdl:types>
    <s:schema elementFormDefault="qualified" targetNamespace="http://tempuri.org/">
      <s:element name="mainWebService">
        <s:complexType>
          <s:sequence>
            <s:element minOccurs="0" maxOccurs="1" name="DBNsp" type="s:string" />
            <s:element minOccurs="0" maxOccurs="1" name="sType" type="s:string" />
            <s:element minOccurs="0" maxOccurs="1" name="sWhere" type="s:string" />
          </s:sequence>
        </s:complexType>
      </s:element>
      <s:element name="mainWebServiceResponse">
        <s:complexType>
          <s:sequence>
            <s:element minOccurs="0" maxOccurs="1" name="mainWebServiceResult" type="s:string" />
          </s:sequence>
        </s:complexType>
      </s:element>
      <s:element name="mainPdfWebService">
        <s:complexType>
          <s:sequence>
            <s:element minOccurs="0" maxOccurs="1" name="DBNsp" type="s:string" />
            <s:element minOccurs="0" maxOccurs="1" name="sType" type="s:string" />
            <s:element minOccurs="0" maxOccurs="1" name="sWhere" type="s:string" />
          </s:sequence>
        </s:complexType>
      </s:element>
      <s:element name="mainPdfWebServiceResponse">
        <s:complexType>
          <s:sequence>
            <s:element minOccurs="0" maxOccurs="1" name="mainPdfWebServiceResult" type="s:base64Binary" />
          </s:sequence>
        </s:complexType>
      </s:element>
      <s:element name="string" nillable="true" type="s:string" />
      <s:element name="base64Binary" nillable="true" type="s:base64Binary" />
    </s:schema>
  </wsdl:types>
  <wsdl:message name="mainWebServiceSoapIn">
    <wsdl:part name="parameters" element="tns:mainWebService" />
  </wsdl:message>
  <wsdl:message name="mainWebServiceSoapOut">
    <wsdl:part name="parameters" element="tns:mainWebServiceResponse" />
  </wsdl:message>
  <wsdl:message name="mainPdfWebServiceSoapIn">
    <wsdl:part name="parameters" element="tns:mainPdfWebService" />
  </wsdl:message>
  <wsdl:message name="mainPdfWebServiceSoapOut">
    <wsdl:part name="parameters" element="tns:mainPdfWebServiceResponse" />
  </wsdl:message>
  <wsdl:message name="mainWebServiceHttpGetIn">
    <wsdl:part name="DBNsp" type="s:string" />
    <wsdl:part name="sType" type="s:string" />
    <wsdl:part name="sWhere" type="s:string" />
  </wsdl:message>
  <wsdl:message name="mainWebServiceHttpGetOut">
    <wsdl:part name="Body" element="tns:string" />
  </wsdl:message>
  <wsdl:message name="mainPdfWebServiceHttpGetIn">
    <wsdl:part name="DBNsp" type="s:string" />
    <wsdl:part name="sType" type="s:string" />
    <wsdl:part name="sWhere" type="s:string" />
  </wsdl:message>
  <wsdl:message name="mainPdfWebServiceHttpGetOut">
    <wsdl:part name="Body" element="tns:base64Binary" />
  </wsdl:message>
  <wsdl:message name="mainWebServiceHttpPostIn">
    <wsdl:part name="DBNsp" type="s:string" />
    <wsdl:part name="sType" type="s:string" />
    <wsdl:part name="sWhere" type="s:string" />
  </wsdl:message>
  <wsdl:message name="mainWebServiceHttpPostOut">
    <wsdl:part name="Body" element="tns:string" />
  </wsdl:message>
  <wsdl:message name="mainPdfWebServiceHttpPostIn">
    <wsdl:part name="DBNsp" type="s:string" />
    <wsdl:part name="sType" type="s:string" />
    <wsdl:part name="sWhere" type="s:string" />
  </wsdl:message>
  <wsdl:message name="mainPdfWebServiceHttpPostOut">
    <wsdl:part name="Body" element="tns:base64Binary" />
  </wsdl:message>
  <wsdl:portType name="WebServiceSoap">
    <wsdl:operation name="mainWebService">
      <wsdl:documentation xmlns:wsdl="http://schemas.xmlsoap.org/wsdl/">程序入口</wsdl:documentation>
      <wsdl:input message="tns:mainWebServiceSoapIn" />
      <wsdl:output message="tns:mainWebServiceSoapOut" />
    </wsdl:operation>
    <wsdl:operation name="mainPdfWebService">
      <wsdl:documentation xmlns:wsdl="http://schemas.xmlsoap.org/wsdl/">PDF程序入口</wsdl:documentation>
      <wsdl:input message="tns:mainPdfWebServiceSoapIn" />
      <wsdl:output message="tns:mainPdfWebServiceSoapOut" />
    </wsdl:operation>
  </wsdl:portType>
  <wsdl:portType name="WebServiceHttpGet">
    <wsdl:operation name="mainWebService">
      <wsdl:documentation xmlns:wsdl="http://schemas.xmlsoap.org/wsdl/">程序入口</wsdl:documentation>
      <wsdl:input message="tns:mainWebServiceHttpGetIn" />
      <wsdl:output message="tns:mainWebServiceHttpGetOut" />
    </wsdl:operation>
    <wsdl:operation name="mainPdfWebService">
      <wsdl:documentation xmlns:wsdl="http://schemas.xmlsoap.org/wsdl/">PDF程序入口</wsdl:documentation>
      <wsdl:input message="tns:mainPdfWebServiceHttpGetIn" />
      <wsdl:output message="tns:mainPdfWebServiceHttpGetOut" />
    </wsdl:operation>
  </wsdl:portType>
  <wsdl:portType name="WebServiceHttpPost">
    <wsdl:operation name="mainWebService">
      <wsdl:documentation xmlns:wsdl="http://schemas.xmlsoap.org/wsdl/">程序入口</wsdl:documentation>
      <wsdl:input message="tns:mainWebServiceHttpPostIn" />
      <wsdl:output message="tns:mainWebServiceHttpPostOut" />
    </wsdl:operation>
    <wsdl:operation name="mainPdfWebService">
      <wsdl:documentation xmlns:wsdl="http://schemas.xmlsoap.org/wsdl/">PDF程序入口</wsdl:documentation>
      <wsdl:input message="tns:mainPdfWebServiceHttpPostIn" />
      <wsdl:output message="tns:mainPdfWebServiceHttpPostOut" />
    </wsdl:operation>
  </wsdl:portType>
  <wsdl:binding name="WebServiceSoap" type="tns:WebServiceSoap">
    <soap:binding transport="http://schemas.xmlsoap.org/soap/http" />
    <wsdl:operation name="mainWebService">
      <soap:operation soapAction="http://tempuri.org/mainWebService" style="document" />
      <wsdl:input>
        <soap:body use="literal" />
      </wsdl:input>
      <wsdl:output>
        <soap:body use="literal" />
      </wsdl:output>
    </wsdl:operation>
    <wsdl:operation name="mainPdfWebService">
      <soap:operation soapAction="http://tempuri.org/mainPdfWebService" style="document" />
      <wsdl:input>
        <soap:body use="literal" />
      </wsdl:input>
      <wsdl:output>
        <soap:body use="literal" />
      </wsdl:output>
    </wsdl:operation>
  </wsdl:binding>
  <wsdl:binding name="WebServiceSoap12" type="tns:WebServiceSoap">
    <soap12:binding transport="http://schemas.xmlsoap.org/soap/http" />
    <wsdl:operation name="mainWebService">
      <soap12:operation soapAction="http://tempuri.org/mainWebService" style="document" />
      <wsdl:input>
        <soap12:body use="literal" />
      </wsdl:input>
      <wsdl:output>
        <soap12:body use="literal" />
      </wsdl:output>
    </wsdl:operation>
    <wsdl:operation name="mainPdfWebService">
      <soap12:operation soapAction="http://tempuri.org/mainPdfWebService" style="document" />
      <wsdl:input>
        <soap12:body use="literal" />
      </wsdl:input>
      <wsdl:output>
        <soap12:body use="literal" />
      </wsdl:output>
    </wsdl:operation>
  </wsdl:binding>
  <wsdl:binding name="WebServiceHttpGet" type="tns:WebServiceHttpGet">
    <http:binding verb="GET" />
    <wsdl:operation name="mainWebService">
      <http:operation location="/mainWebService" />
      <wsdl:input>
        <http:urlEncoded />
      </wsdl:input>
      <wsdl:output>
        <mime:mimeXml part="Body" />
      </wsdl:output>
    </wsdl:operation>
    <wsdl:operation name="mainPdfWebService">
      <http:operation location="/mainPdfWebService" />
      <wsdl:input>
        <http:urlEncoded />
      </wsdl:input>
      <wsdl:output>
        <mime:mimeXml part="Body" />
      </wsdl:output>
    </wsdl:operation>
  </wsdl:binding>
  <wsdl:binding name="WebServiceHttpPost" type="tns:WebServiceHttpPost">
    <http:binding verb="POST" />
    <wsdl:operation name="mainWebService">
      <http:operation location="/mainWebService" />
      <wsdl:input>
        <mime:content type="application/x-www-form-urlencoded" />
      </wsdl:input>
      <wsdl:output>
        <mime:mimeXml part="Body" />
      </wsdl:output>
    </wsdl:operation>
    <wsdl:operation name="mainPdfWebService">
      <http:operation location="/mainPdfWebService" />
      <wsdl:input>
        <mime:content type="application/x-www-form-urlencoded" />
      </wsdl:input>
      <wsdl:output>
        <mime:mimeXml part="Body" />
      </wsdl:output>
    </wsdl:operation>
  </wsdl:binding>
  <wsdl:service name="WebService">
    <wsdl:port name="WebServiceSoap" binding="tns:WebServiceSoap">
      <soap:address location="http://192.168.1.146:8090/NewHope/WebService.asmx" />
    </wsdl:port>
    <wsdl:port name="WebServiceSoap12" binding="tns:WebServiceSoap12">
      <soap12:address location="http://192.168.1.146:8090/NewHope/WebService.asmx" />
    </wsdl:port>
    <wsdl:port name="WebServiceHttpGet" binding="tns:WebServiceHttpGet">
      <http:address location="http://192.168.1.146:8090/NewHope/WebService.asmx" />
    </wsdl:port>
    <wsdl:port name="WebServiceHttpPost" binding="tns:WebServiceHttpPost">
      <http:address location="http://192.168.1.146:8090/NewHope/WebService.asmx" />
    </wsdl:port>
  </wsdl:service>
</wsdl:definitions>