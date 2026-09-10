package com.gmail.xmlconverter;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.DOMException;
import org.xml.sax.SAXException;

public interface FromXmlConverter <T>{
	
	

	public T convertfromXml(File file)
			throws SAXException, IOException, ParserConfigurationException, NoSuchMethodException, SecurityException, IllegalAccessException, InvocationTargetException, DOMException, InstantiationException, IllegalArgumentException;
}
