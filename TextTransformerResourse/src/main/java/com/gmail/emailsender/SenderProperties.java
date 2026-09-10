package com.gmail.emailsender;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactoryConfigurationError;

import org.w3c.dom.DOMException;
import org.xml.sax.SAXException;

import com.gmail.database.FileAddresses;
import com.gmail.xmlconverter.XmlSaver;
import com.gmail.xmlconverter.XmlUnsaver;

import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class SenderProperties {

	private File propsFile = null;
	private XmlUnsaver <SenderProperties>unsaver = new XmlUnsaver(this.getClass());
	private XmlSaver<SenderProperties> saver = new XmlSaver<>();
//	private String key = "lonKHOjPldv6yQWW";
//	private String sender = "zlobinna@ukr.net"; 
//	private String host = "smtp.ukr.net";
	
	private String key;
	private String sender;
	private String host;
	
	
	
	public SenderProperties() throws IOException{

			propsFile =  new File(FileAddresses.getEmailSender_properties(),"emailSender_properties.xml");

		
	
	}
	static {
		System.out.println("Sender properties initatied with ");
		
	}
	
	
	public String getKey() {
		return key;
	}
	
	@XmlElement
	public void setKey(String key) {
		this.key = key;
	}
	
	public String getSender() {
		return sender;
	}
	
	@XmlElement
	public void setSender(String sender) {
		this.sender = sender;
	}
	
	public String getHost() {
		return host;
	}
	
	@XmlElement
	public void setHost(String host) {
		this.host = host;
	}



	
	public static void main(String [] args) throws IOException, ClassNotFoundException, IllegalAccessException, InvocationTargetException, TransformerConfigurationException, SecurityException, DOMException, ParserConfigurationException, TransformerException, TransformerFactoryConfigurationError {
		SenderProperties props = new SenderProperties();
		File file = props.saver.convertToXml(props);
		System.out.println(props);
//		System.out.println(file);
		
		
	
	}
	
//	private File propertiesToXML() throws JAXBException, IOException {
//		
//		JAXBContext context = JAXBContext.newInstance(SenderProperties.class);
//		Marshaller marshaller = context.createMarshaller();
//		marshaller.marshal(this, propsFile);
//		return propsFile;
//	}
	
	private File propertiesToXML() throws ClassNotFoundException, IllegalAccessException, InvocationTargetException, TransformerConfigurationException, SecurityException, DOMException, ParserConfigurationException, TransformerException, TransformerFactoryConfigurationError {
		return this.saver.convertToXml(this);
	}
	
	public void updateProperties(String elementName, String elementNewValue) throws JAXBException, IllegalArgumentException, IllegalAccessException, IOException, ClassNotFoundException, InvocationTargetException, TransformerConfigurationException, SecurityException, DOMException, ParserConfigurationException, TransformerException, TransformerFactoryConfigurationError {

		Field []fields = SenderProperties.class.getDeclaredFields();
		
		for(int i = 0; i< fields.length;i++) {
			if(fields[i].getName().equals(elementName)) {	
				fields[i].setAccessible(true);
				fields[i].set(this, elementNewValue);	
			}
		}
		
		propertiesToXML();
		
	}
	
//	public  SenderProperties getSenderProperties() throws ParserConfigurationException, JAXBException {
//		JAXBContext context = JAXBContext.newInstance(SenderProperties.class);
//		Unmarshaller unmarsh = context.createUnmarshaller();
//		SenderProperties props = (SenderProperties) unmarsh.unmarshal(propsFile);
//		return props;
//	}
	
	public SenderProperties getSenderProperties() throws NoSuchMethodException, SecurityException, IllegalAccessException, InvocationTargetException, DOMException, InstantiationException, IllegalArgumentException, SAXException, IOException, ParserConfigurationException {
		return this.unsaver.convertfromXml(propsFile);
	}

	@Override
	public String toString() {
		return "SenderProperties [key=" + key + ", sender=" + sender + ", host=" + host + "]";
	}
	
	
	

}
