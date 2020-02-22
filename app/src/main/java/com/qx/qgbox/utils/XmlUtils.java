package com.qx.qgbox.utils;

import com.qx.qgbox.entitys.GunInfo;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class XmlUtils
{
  public static List<GunInfo> dom2xml(InputStream paramInputStream)
    throws Exception
  {
    ArrayList localArrayList = new ArrayList();
    paramInputStream = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(paramInputStream).getElementsByTagName("gunInfo");
    int i = 0;
    while (i < paramInputStream.getLength())
    {
      NodeList localNodeList = paramInputStream.item(i).getChildNodes();
      GunInfo localGunInfo = new GunInfo();
      int j = 0;
      while (j < localNodeList.getLength())
      {
        Node localNode = localNodeList.item(j);
        if ("gunName".equals(localNode.getNodeName()))
          localGunInfo.setGunName(localNode.getTextContent());
        else if ("mode".equals(localNode.getNodeName()))
          localGunInfo.setMode(localNode.getTextContent());
        else if ("fireMode".equals(localNode.getNodeName()))
          localGunInfo.setFireMode(localNode.getTextContent());
        else if ("range".equals(localNode.getNodeName()))
          localGunInfo.setRange(Integer.valueOf(localNode.getTextContent()).intValue());
        else if ("autoFireRate".equals(localNode.getNodeName()))
          localGunInfo.setAutoFireRate(Integer.valueOf(localNode.getTextContent()).intValue());
        j += 1;
      }
      localArrayList.add(localGunInfo);
      i += 1;
    }
    return localArrayList;
  }

  public static List<GunInfo> domXml(File paramFile)
    throws Exception
  {
    ArrayList localArrayList = new ArrayList();
    paramFile = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(paramFile).getElementsByTagName("gunInfo");
    int i = 0;
    while (i < paramFile.getLength())
    {
      NodeList localNodeList = paramFile.item(i).getChildNodes();
      GunInfo localGunInfo = new GunInfo();
      int j = 0;
      while (j < localNodeList.getLength())
      {
        Node localNode = localNodeList.item(j);
        if ("gunName".equals(localNode.getNodeName()))
          localGunInfo.setGunName(localNode.getTextContent());
        else if ("mode".equals(localNode.getNodeName()))
          localGunInfo.setMode(localNode.getTextContent());
        else if ("fireMode".equals(localNode.getNodeName()))
          localGunInfo.setFireMode(localNode.getTextContent());
        else if ("range".equals(localNode.getNodeName()))
          localGunInfo.setRange(Integer.valueOf(localNode.getTextContent()).intValue());
        else if ("autoFireRate".equals(localNode.getNodeName()))
          localGunInfo.setAutoFireRate(Integer.valueOf(localNode.getTextContent()).intValue());
        j += 1;
      }
      localArrayList.add(localGunInfo);
      i += 1;
    }
    return localArrayList;
  }

  public static boolean write(List<GunInfo> paramList, File paramFile)
  {
    Object localObject1 = DocumentBuilderFactory.newInstance();
    try
    {
      localObject1 = ((DocumentBuilderFactory)localObject1).newDocumentBuilder().newDocument();
      Element localElement1 = ((Document)localObject1).createElement("gunInfos");
      int i = 0;
      while (i < paramList.size())
      {
        Element localElement2 = ((Document)localObject1).createElement("gunInfo");
        Object localObject2 = new StringBuilder();
        ((StringBuilder)localObject2).append(i);
        ((StringBuilder)localObject2).append("");
        localElement2.setAttribute("id", ((StringBuilder)localObject2).toString());
        localObject2 = ((Document)localObject1).createElement("gunName");
        ((Element)localObject2).setTextContent(((GunInfo)paramList.get(i)).getGunName());
        Element localElement3 = ((Document)localObject1).createElement("mode");
        localElement3.setTextContent(((GunInfo)paramList.get(i)).getMode());
        Element localElement4 = ((Document)localObject1).createElement("fireMode");
        localElement4.setTextContent(((GunInfo)paramList.get(i)).getFireMode());
        Element localElement5 = ((Document)localObject1).createElement("range");
        localElement5.setTextContent(String.valueOf(((GunInfo)paramList.get(i)).getRange()));
        Element localElement6 = ((Document)localObject1).createElement("autoFireRate");
        localElement6.setTextContent(String.valueOf(((GunInfo)paramList.get(i)).getAutoFireRate()));
        localElement2.appendChild((Node)localObject2);
        localElement2.appendChild(localElement3);
        localElement2.appendChild(localElement4);
        localElement2.appendChild(localElement5);
        localElement2.appendChild(localElement6);
        localElement1.appendChild(localElement2);
        i += 1;
      }
      ((Document)localObject1).appendChild(localElement1);
      paramList = TransformerFactory.newInstance().newTransformer();
      paramList.setOutputProperty("encoding", "UTF-8");
      paramFile = new FileOutputStream(paramFile);
      paramList.transform(new DOMSource((Node)localObject1), new StreamResult(paramFile));
      return true;
    }
    catch (TransformerException paramList)
    {
      paramList.printStackTrace();
      return false;
    }
    catch (FileNotFoundException paramList)
    {
      paramList.printStackTrace();
      return false;
    }
    catch (TransformerConfigurationException paramList)
    {
      paramList.printStackTrace();
      return false;
    }
    catch (ParserConfigurationException paramList)
    {
      paramList.printStackTrace();
    }
    return false;
  }
}
