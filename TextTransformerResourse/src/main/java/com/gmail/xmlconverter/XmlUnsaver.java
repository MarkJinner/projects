package com.gmail.xmlconverter;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.DOMException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import com.gmail.emailsender.HostProperties;
import com.gmail.emailsender.SenderProperties;

public class XmlUnsaver<T> implements FromXmlConverter<T> {
	private Class<?> cl;
	
	public XmlUnsaver(Class<?> cl) {
		this.cl = cl;
	}
	
	public XmlUnsaver() {
		
	}
	
	
	public Class<?> getCl() {
		return cl;
	}

	public void setCl(Class<?> cl) {
		this.cl = cl;
	}

	public static void main(String [] args) throws NoSuchMethodException, SecurityException, SAXException, IOException, ParserConfigurationException, IllegalAccessException, InvocationTargetException, DOMException, InstantiationException, IllegalArgumentException {
		XmlUnsaver<?> unsaver = new XmlUnsaver(SenderProperties.class);
		String name = "/Users/olegivanov/git/projects/TextTransformerResourse/src/main/webapp/resource/emailSender_Properties.xml";
		File file = new File(name);
		SenderProperties props = (SenderProperties) unsaver.convertfromXml(file);
		
		String name2 = "/Users/olegivanov/eclipse-workspace2026/TextTransformerResourse/src/main/webapp/resource/host_properties.xml";
		File file2 = new File(name2);
		unsaver = new XmlUnsaver(HostProperties.class);
		HostProperties hProps = (HostProperties) unsaver.convertfromXml(file2);
		System.out.println(props);
		System.out.println(hProps);
		
	}

	@Override
	public T convertfromXml(File file) throws SAXException, IOException, ParserConfigurationException, NoSuchMethodException, SecurityException, IllegalAccessException, InvocationTargetException, DOMException, InstantiationException, IllegalArgumentException { 
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

		DocumentBuilder builder = factory.newDocumentBuilder();
		
		Document doc = builder.parse(file);
		Element root = (Element) doc.getFirstChild();
		T obj = (T) cl.getConstructor().newInstance();
		Method[] methods = obj.getClass().getDeclaredMethods();
		List<Method> annotated = Arrays.stream(methods).filter(s->s.isAnnotationPresent(jakarta.xml.bind.annotation.XmlElement.class)).toList();
		
		if(root.getNodeType()==Node.ELEMENT_NODE) {
				if(root.getChildNodes() != null){

					NodeList nodes = root.getChildNodes();
					for(int i = 0; i< nodes.getLength();i++) {
						Element field = (Element) nodes.item(i);
						if(field.getNodeType()==Node.ELEMENT_NODE) {
							for(int j = 0; j< annotated.size();j++) {
								if(annotated.get(j).getName().toLowerCase() .contains(field.getNodeName())) {
									Method init = annotated.get(j);						
									init.invoke(obj, field.getTextContent());
								}
							}
						}

					}
				}
		}
		
		return obj;
	}

}
