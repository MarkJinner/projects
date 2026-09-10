package com.gmail.xmlconverter;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.TransformerFactoryConfigurationError;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.DOMException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

import com.gmail.database.Resources;
import com.gmail.emailsender.HostProperties;
import com.gmail.emailsender.SenderProperties;

public class XmlSaver<T> implements ToXmlConverter<T> {
	private File file = null;


	public XmlSaver(File file) {
		this.file = file;
	}

	public XmlSaver() {

	}

	public static void main(String[] args) throws IOException, ParserConfigurationException, ClassNotFoundException,
			IllegalAccessException, InvocationTargetException, TransformerConfigurationException, TransformerException, NoSuchMethodException, SecurityException, DOMException, InstantiationException, IllegalArgumentException, SAXException {
		File file = new File(Resources.RESOURSES_FOLDER + "temp_storage.xml");
		XmlSaver<SenderProperties> saver = new XmlSaver<>();
		SenderProperties props = new SenderProperties();

//		saver.convertToXml(props);
		
		HostProperties hProps = new HostProperties();
		saver = new XmlSaver<>();
		hProps.setAuth("mail.smtp.auth");
		hProps.setAuthFlag("true");
		hProps.setHost("mail.smtp.host");
		hProps.setSslFlag("true");
		hProps.setPort("mail.smtp.port");
		hProps.setPortValue("465");
		
		saver.convertToXml(hProps);
//		File file = new File(Resources.RESOURSES_FOLDER + "temp_storage.xml");
	}

	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
	}

	@Override
	public <T> File convertToXml(T obj) throws ParserConfigurationException, ClassNotFoundException,
		
			IllegalAccessException, InvocationTargetException, TransformerConfigurationException, TransformerException,
			SecurityException, DOMException, TransformerFactoryConfigurationError {
		
		String name = obj.toString().substring(obj.toString().lastIndexOf(".")+1, obj.toString().indexOf("@"));
		
		File file = new File(Resources.RESOURSES_FOLDER +""+name+".xml");
		Class res = Class.forName(((Class<T>) obj.getClass()).getName());
		
		Document doc = getDoc();
		List<Method> annotated = Arrays.stream(res.getDeclaredMethods())
				.filter(s -> s.isAnnotationPresent(jakarta.xml.bind.annotation.XmlElement.class)).toList();
		List<Method> getters = convertSetIntoGet(annotated, res);
		
		Element root = doc.createElement(res.getSimpleName());
		doc.appendChild(root);
		fillDOM(obj, doc, annotated, getters, root);// appending all childNodes to root element

		DOMSource source = new DOMSource(doc);
		TransformerFactory tFactory = TransformerFactory.newInstance();
		Transformer transformer = tFactory.newTransformer();
		StreamResult result = new StreamResult(file);
		transformer.transform(source, result);

		return file;
	}

	private <T> void fillDOM(T obj, Document doc, List<Method> annotated, List<Method> getters, Element root)
			throws IllegalAccessException, InvocationTargetException {
		for (int i = 0; i < annotated.size(); i++) {
			String fieldName = getFieldName(annotated.get(i)).toLowerCase();

			Element field = doc.createElement(fieldName);
			String fieldContext = (String) getters.get(i).invoke(obj, null);
//			System.out.println(fieldName+": "+fieldContext);
			field.setTextContent(fieldContext);
			root.appendChild(field);
		}
	}

	private Document getDoc() throws ParserConfigurationException {
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = factory.newDocumentBuilder();

		return builder.newDocument();
	}

	private List<Method> convertSetIntoGet(List<Method> annotated, Class<T> class1) {
		List<Method> getters = new ArrayList<>();

		annotated.stream().forEach(s -> {
			try {
				Method temp = class1.getMethod(getGetterName(s), null);
				getters.add(temp);
			} catch (NoSuchMethodException | SecurityException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		});

		return getters;
	}

	private String getGetterName(Method setter) {
		return "get" + (getFieldName(setter));
	}

	private String getFieldName(Method md) {
		return md.getName().substring(md.getName().indexOf("set") + 3, md.getName().length());
	}

}
