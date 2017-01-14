package com.purplebeen.rssparser;


import android.os.AsyncTask;
import android.util.Log;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;


/**
 * Created by baehy on 2016-12-26.
 */

public class Parser extends AsyncTask<Void, Void, ArrayList[]>{
    private ArrayList<String> titles = new ArrayList<String>();
    private ArrayList<String> descriptions = new ArrayList<String>();
    private String url = "";
    private URL xmlURL;

    public void setURL(String url) {
        this.url = url;
    }
    @Override
    protected ArrayList[] doInBackground(Void... voids) {
        ArrayList[] array = new ArrayList[2];
        try {
            xmlURL = new URL(url);
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            InputStream inputStream = xmlURL.openStream();
            Document document = builder.parse(inputStream, "UTF-8");
            XPath xpath = XPathFactory.newInstance().newXPath();
            NodeList titleList = (NodeList) xpath.evaluate("//item/title", document, XPathConstants.NODESET);
            NodeList descriptionList = (NodeList) xpath.evaluate("//item/description", document, XPathConstants.NODESET);
            for (int i = 0; i < titleList.getLength(); i++) {
                titles.add(titleList.item(i).getTextContent());
                Log.d("Parser", "title : " + titles.get(i));
                descriptions.add(descriptionList.item(i).getTextContent());
            }
            array[0] = titles;
            array[1] = descriptions;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return array;
    }
}
