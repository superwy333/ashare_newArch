
package webservice.jde.service;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the webservice.jde.service package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _Base64Binary_QNAME = new QName("http://tempuri.org/", "base64Binary");
    private final static QName _String_QNAME = new QName("http://tempuri.org/", "string");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: webservice.jde.service
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link MainWebService }
     * 
     */
    public MainWebService createMainWebService() {
        return new MainWebService();
    }

    /**
     * Create an instance of {@link MainPdfWebService }
     * 
     */
    public MainPdfWebService createMainPdfWebService() {
        return new MainPdfWebService();
    }

    /**
     * Create an instance of {@link MainPdfWebServiceResponse }
     * 
     */
    public MainPdfWebServiceResponse createMainPdfWebServiceResponse() {
        return new MainPdfWebServiceResponse();
    }

    /**
     * Create an instance of {@link MainWebServiceResponse }
     * 
     */
    public MainWebServiceResponse createMainWebServiceResponse() {
        return new MainWebServiceResponse();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link byte[]}{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://tempuri.org/", name = "base64Binary")
    public JAXBElement<byte[]> createBase64Binary(byte[] value) {
        return new JAXBElement<byte[]>(_Base64Binary_QNAME, byte[].class, null, ((byte[]) value));
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://tempuri.org/", name = "string")
    public JAXBElement<String> createString(String value) {
        return new JAXBElement<String>(_String_QNAME, String.class, null, value);
    }

}
