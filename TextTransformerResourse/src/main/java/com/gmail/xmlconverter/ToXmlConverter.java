package com.gmail.xmlconverter;

import java.io.File;
import java.lang.reflect.InvocationTargetException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;

public interface ToXmlConverter <T>{
	
	public <T> File convertToXml(T obj) throws ParserConfigurationException, ClassNotFoundException, IllegalAccessException, InvocationTargetException, TransformerConfigurationException, TransformerException;
	
}
