/**
 * Output.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package webservice.fssc.service;

public class Output  implements java.io.Serializable {
    private java.lang.String f_BODY;

    private java.lang.String f_CALL_CODE;

    private java.lang.String f_CALL_MSG;

    private java.lang.String f_CODE;

    private java.lang.String f_IS_BASE64;

    private java.lang.String f_MSG;

    public Output() {
    }

    public Output(
           java.lang.String f_BODY,
           java.lang.String f_CALL_CODE,
           java.lang.String f_CALL_MSG,
           java.lang.String f_CODE,
           java.lang.String f_IS_BASE64,
           java.lang.String f_MSG) {
           this.f_BODY = f_BODY;
           this.f_CALL_CODE = f_CALL_CODE;
           this.f_CALL_MSG = f_CALL_MSG;
           this.f_CODE = f_CODE;
           this.f_IS_BASE64 = f_IS_BASE64;
           this.f_MSG = f_MSG;
    }


    /**
     * Gets the f_BODY value for this Output.
     * 
     * @return f_BODY
     */
    public java.lang.String getF_BODY() {
        return f_BODY;
    }


    /**
     * Sets the f_BODY value for this Output.
     * 
     * @param f_BODY
     */
    public void setF_BODY(java.lang.String f_BODY) {
        this.f_BODY = f_BODY;
    }


    /**
     * Gets the f_CALL_CODE value for this Output.
     * 
     * @return f_CALL_CODE
     */
    public java.lang.String getF_CALL_CODE() {
        return f_CALL_CODE;
    }


    /**
     * Sets the f_CALL_CODE value for this Output.
     * 
     * @param f_CALL_CODE
     */
    public void setF_CALL_CODE(java.lang.String f_CALL_CODE) {
        this.f_CALL_CODE = f_CALL_CODE;
    }


    /**
     * Gets the f_CALL_MSG value for this Output.
     * 
     * @return f_CALL_MSG
     */
    public java.lang.String getF_CALL_MSG() {
        return f_CALL_MSG;
    }


    /**
     * Sets the f_CALL_MSG value for this Output.
     * 
     * @param f_CALL_MSG
     */
    public void setF_CALL_MSG(java.lang.String f_CALL_MSG) {
        this.f_CALL_MSG = f_CALL_MSG;
    }


    /**
     * Gets the f_CODE value for this Output.
     * 
     * @return f_CODE
     */
    public java.lang.String getF_CODE() {
        return f_CODE;
    }


    /**
     * Sets the f_CODE value for this Output.
     * 
     * @param f_CODE
     */
    public void setF_CODE(java.lang.String f_CODE) {
        this.f_CODE = f_CODE;
    }


    /**
     * Gets the f_IS_BASE64 value for this Output.
     * 
     * @return f_IS_BASE64
     */
    public java.lang.String getF_IS_BASE64() {
        return f_IS_BASE64;
    }


    /**
     * Sets the f_IS_BASE64 value for this Output.
     * 
     * @param f_IS_BASE64
     */
    public void setF_IS_BASE64(java.lang.String f_IS_BASE64) {
        this.f_IS_BASE64 = f_IS_BASE64;
    }


    /**
     * Gets the f_MSG value for this Output.
     * 
     * @return f_MSG
     */
    public java.lang.String getF_MSG() {
        return f_MSG;
    }


    /**
     * Sets the f_MSG value for this Output.
     * 
     * @param f_MSG
     */
    public void setF_MSG(java.lang.String f_MSG) {
        this.f_MSG = f_MSG;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof Output)) return false;
        Output other = (Output) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.f_BODY==null && other.getF_BODY()==null) || 
             (this.f_BODY!=null &&
              this.f_BODY.equals(other.getF_BODY()))) &&
            ((this.f_CALL_CODE==null && other.getF_CALL_CODE()==null) || 
             (this.f_CALL_CODE!=null &&
              this.f_CALL_CODE.equals(other.getF_CALL_CODE()))) &&
            ((this.f_CALL_MSG==null && other.getF_CALL_MSG()==null) || 
             (this.f_CALL_MSG!=null &&
              this.f_CALL_MSG.equals(other.getF_CALL_MSG()))) &&
            ((this.f_CODE==null && other.getF_CODE()==null) || 
             (this.f_CODE!=null &&
              this.f_CODE.equals(other.getF_CODE()))) &&
            ((this.f_IS_BASE64==null && other.getF_IS_BASE64()==null) || 
             (this.f_IS_BASE64!=null &&
              this.f_IS_BASE64.equals(other.getF_IS_BASE64()))) &&
            ((this.f_MSG==null && other.getF_MSG()==null) || 
             (this.f_MSG!=null &&
              this.f_MSG.equals(other.getF_MSG())));
        __equalsCalc = null;
        return _equals;
    }

    private boolean __hashCodeCalc = false;
    public synchronized int hashCode() {
        if (__hashCodeCalc) {
            return 0;
        }
        __hashCodeCalc = true;
        int _hashCode = 1;
        if (getF_BODY() != null) {
            _hashCode += getF_BODY().hashCode();
        }
        if (getF_CALL_CODE() != null) {
            _hashCode += getF_CALL_CODE().hashCode();
        }
        if (getF_CALL_MSG() != null) {
            _hashCode += getF_CALL_MSG().hashCode();
        }
        if (getF_CODE() != null) {
            _hashCode += getF_CODE().hashCode();
        }
        if (getF_IS_BASE64() != null) {
            _hashCode += getF_IS_BASE64().hashCode();
        }
        if (getF_MSG() != null) {
            _hashCode += getF_MSG().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(Output.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://ApiService.soaptypes", "Output"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("f_BODY");
        elemField.setXmlName(new javax.xml.namespace.QName("", "F_BODY"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("f_CALL_CODE");
        elemField.setXmlName(new javax.xml.namespace.QName("", "F_CALL_CODE"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("f_CALL_MSG");
        elemField.setXmlName(new javax.xml.namespace.QName("", "F_CALL_MSG"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("f_CODE");
        elemField.setXmlName(new javax.xml.namespace.QName("", "F_CODE"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("f_IS_BASE64");
        elemField.setXmlName(new javax.xml.namespace.QName("", "F_IS_BASE64"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("f_MSG");
        elemField.setXmlName(new javax.xml.namespace.QName("", "F_MSG"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
    }

    /**
     * Return type metadata object
     */
    public static org.apache.axis.description.TypeDesc getTypeDesc() {
        return typeDesc;
    }

    /**
     * Get Custom Serializer
     */
    public static org.apache.axis.encoding.Serializer getSerializer(
           java.lang.String mechType, 
           java.lang.Class _javaType,  
           javax.xml.namespace.QName _xmlType) {
        return 
          new  org.apache.axis.encoding.ser.BeanSerializer(
            _javaType, _xmlType, typeDesc);
    }

    /**
     * Get Custom Deserializer
     */
    public static org.apache.axis.encoding.Deserializer getDeserializer(
           java.lang.String mechType, 
           java.lang.Class _javaType,  
           javax.xml.namespace.QName _xmlType) {
        return 
          new  org.apache.axis.encoding.ser.BeanDeserializer(
            _javaType, _xmlType, typeDesc);
    }

}
