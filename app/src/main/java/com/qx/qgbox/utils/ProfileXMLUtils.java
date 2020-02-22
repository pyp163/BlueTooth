package com.qx.qgbox.utils;

import com.qx.qgbox.entitys.DirKey;
import com.qx.qgbox.entitys.EyesView;
import com.qx.qgbox.entitys.NormalKey;
import com.qx.qgbox.entitys.Resolution;
import com.qx.qgbox.entitys.pFileInfo;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.ArrayList;
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

public class ProfileXMLUtils
{
  public static final String DIR_KEY = "DirKey";
  public static final String DIR_KEY_DOWN = "down";
  public static final String DIR_KEY_LEFT = "left";
  public static final String DIR_KEY_ORIGIN_X_HIGH = "originXHigh";
  public static final String DIR_KEY_ORIGIN_X_LOW = "originXLow";
  public static final String DIR_KEY_ORIGIN_Y_HIGH = "originYHigh";
  public static final String DIR_KEY_ORIGIN_Y_LOW = "originYLow";
  public static final String DIR_KEY_RADIUS_HIGH = "dirRadiusHigh";
  public static final String DIR_KEY_RADIUS_LOW = "dirRadiusLow";
  public static final String DIR_KEY_RIGHT = "right";
  public static final String DIR_KEY_UP = "up";
  public static final String EYES_VIEW = "EyesView";
  public static final String EYES_VIEW_MOUSE_SPEED = "mouseSpeed";
  public static final String EYES_VIEW_X_HIGH = "eyesViewXHigh";
  public static final String EYES_VIEW_X_LOW = "eyesViewXLow";
  public static final String EYES_VIEW_Y_HIGH = "eyesViewYHigh";
  public static final String EYES_VIEW_Y_LOW = "eyesViewYLow";
  public static final String KEY = "Key";
  public static final String KEYS = "Keys";
  public static final String KEY_CODE = "code";
  public static final String KEY_P1XH = "p1xh";
  public static final String KEY_P1XL = "p1xl";
  public static final String KEY_P1YH = "p1yh";
  public static final String KEY_P1YL = "p1yl";
  public static final String KEY_P2XH = "p2xh";
  public static final String KEY_P2XL = "p2xl";
  public static final String KEY_P2YH = "p2yh";
  public static final String KEY_P2YL = "p2yl";
  public static final String KEY_TYPE = "type";
  public static final String PROFILE_INFO = "FileInfo";
  public static final String PROFILE_INFO_TYPE = "type";
  public static final String PROFILE_INFO_VERSION_CODE_HIGH = "versionCodeHigh";
  public static final String PROFILE_INFO_VERSION_CODE_LOW = "versionCodeLow";
  public static final String RESOLUTION = "Resolution";
  public static final String RESOLUTION_X_HIGH = "resolutionXHigh";
  public static final String RESOLUTION_X_LOW = "resolutionXLow";
  public static final String RESOLUTION_Y_HIGH = "resolutionYHigh";
  public static final String RESOLUTION_Y_LOW = "resolutionYLow";

  public static ArrayList<DirKey> getDirKeyList(File paramFile)
    throws Exception
  {
    ArrayList localArrayList = new ArrayList();
    paramFile = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(paramFile).getElementsByTagName("DirKey");
    int i = 0;
    while (i < paramFile.getLength())
    {
      NodeList localNodeList = paramFile.item(i).getChildNodes();
      DirKey localDirKey = new DirKey();
      int j = 0;
      while (j < localNodeList.getLength())
      {
        Node localNode = localNodeList.item(j);
        if ("originXLow".equals(localNode.getNodeName()))
          localDirKey.setOriginXLow(Byte.valueOf(localNode.getTextContent()).byteValue());
        else if ("originXHigh".equals(localNode.getNodeName()))
          localDirKey.setOriginXHigh(Byte.valueOf(localNode.getTextContent()).byteValue());
        else if ("originYLow".equals(localNode.getNodeName()))
          localDirKey.setOriginYLow(Byte.valueOf(localNode.getTextContent()).byteValue());
        else if ("originYHigh".equals(localNode.getNodeName()))
          localDirKey.setOriginYHigh(Byte.valueOf(localNode.getTextContent()).byteValue());
        else if ("dirRadiusLow".equals(localNode.getNodeName()))
          localDirKey.setDirRadiusLow(Byte.valueOf(localNode.getTextContent()).byteValue());
        else if ("dirRadiusHigh".equals(localNode.getNodeName()))
          localDirKey.setDirRadiusHigh(Byte.valueOf(localNode.getTextContent()).byteValue());
        else if ("up".equals(localNode.getNodeName()))
          localDirKey.setUp(Byte.valueOf(localNode.getTextContent()).byteValue());
        else if ("down".equals(localNode.getNodeName()))
          localDirKey.setDown(Byte.valueOf(localNode.getTextContent()).byteValue());
        else if ("left".equals(localNode.getNodeName()))
          localDirKey.setLeft(Byte.valueOf(localNode.getTextContent()).byteValue());
        else if ("right".equals(localNode.getNodeName()))
          localDirKey.setRight(Byte.valueOf(localNode.getTextContent()).byteValue());
        j += 1;
      }
      localArrayList.add(localDirKey);
      i += 1;
    }
    return localArrayList;
  }

  public static ArrayList<DirKey> getDirKeyList(InputStream paramInputStream)
    throws Exception
  {
    ArrayList localArrayList = new ArrayList();
    paramInputStream = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(paramInputStream).getElementsByTagName("DirKey");
    int i = 0;
    while (i < paramInputStream.getLength())
    {
      NodeList localNodeList = paramInputStream.item(i).getChildNodes();
      DirKey localDirKey = new DirKey();
      int j = 0;
      while (j < localNodeList.getLength())
      {
        Node localNode = localNodeList.item(j);
        if ("originXLow".equals(localNode.getNodeName()))
          localDirKey.setOriginXLow(Byte.valueOf(localNode.getTextContent()).byteValue());
        else if ("originXHigh".equals(localNode.getNodeName()))
          localDirKey.setOriginXHigh(Byte.valueOf(localNode.getTextContent()).byteValue());
        else if ("originYLow".equals(localNode.getNodeName()))
          localDirKey.setOriginYLow(Byte.valueOf(localNode.getTextContent()).byteValue());
        else if ("originYHigh".equals(localNode.getNodeName()))
          localDirKey.setOriginYHigh(Byte.valueOf(localNode.getTextContent()).byteValue());
        else if ("dirRadiusLow".equals(localNode.getNodeName()))
          localDirKey.setDirRadiusLow(Byte.valueOf(localNode.getTextContent()).byteValue());
        else if ("dirRadiusHigh".equals(localNode.getNodeName()))
          localDirKey.setDirRadiusHigh(Byte.valueOf(localNode.getTextContent()).byteValue());
        else if ("up".equals(localNode.getNodeName()))
          localDirKey.setUp(Byte.valueOf(localNode.getTextContent()).byteValue());
        else if ("down".equals(localNode.getNodeName()))
          localDirKey.setDown(Byte.valueOf(localNode.getTextContent()).byteValue());
        else if ("left".equals(localNode.getNodeName()))
          localDirKey.setLeft(Byte.valueOf(localNode.getTextContent()).byteValue());
        else if ("right".equals(localNode.getNodeName()))
          localDirKey.setRight(Byte.valueOf(localNode.getTextContent()).byteValue());
        j += 1;
      }
      localArrayList.add(localDirKey);
      i += 1;
    }
    return localArrayList;
  }

  public static ArrayList<EyesView> getEyesViewList(File paramFile)
    throws Exception
  {
    ArrayList localArrayList = new ArrayList();
    paramFile = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(paramFile).getElementsByTagName("EyesView");
    int i = 0;
    while (i < paramFile.getLength())
    {
      NodeList localNodeList = paramFile.item(i).getChildNodes();
      EyesView localEyesView = new EyesView();
      int j = 0;
      while (j < localNodeList.getLength())
      {
        Node localNode = localNodeList.item(j);
        if ("eyesViewXLow".equals(localNode.getNodeName()))
          localEyesView.setEyesViewXLow(Byte.valueOf(localNode.getTextContent()).byteValue());
        else if ("eyesViewXHigh".equals(localNode.getNodeName()))
          localEyesView.setEyesViewXHigh(Byte.valueOf(localNode.getTextContent()).byteValue());
        else if ("eyesViewYLow".equals(localNode.getNodeName()))
          localEyesView.setEyesViewYLow(Byte.valueOf(localNode.getTextContent()).byteValue());
        else if ("eyesViewYHigh".equals(localNode.getNodeName()))
          localEyesView.setEyesViewYHigh(Byte.valueOf(localNode.getTextContent()).byteValue());
        else if ("mouseSpeed".equals(localNode.getNodeName()))
          localEyesView.setMouseSpeed(Byte.valueOf(localNode.getTextContent()).byteValue());
        j += 1;
      }
      localArrayList.add(localEyesView);
      i += 1;
    }
    return localArrayList;
  }

  public static ArrayList<EyesView> getEyesViewList(InputStream paramInputStream)
    throws Exception
  {
    ArrayList localArrayList = new ArrayList();
    paramInputStream = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(paramInputStream).getElementsByTagName("EyesView");
    int i = 0;
    while (i < paramInputStream.getLength())
    {
      NodeList localNodeList = paramInputStream.item(i).getChildNodes();
      EyesView localEyesView = new EyesView();
      int j = 0;
      while (j < localNodeList.getLength())
      {
        Node localNode = localNodeList.item(j);
        if ("eyesViewXLow".equals(localNode.getNodeName()))
          localEyesView.setEyesViewXLow(Byte.valueOf(localNode.getTextContent()).byteValue());
        else if ("eyesViewXHigh".equals(localNode.getNodeName()))
          localEyesView.setEyesViewXHigh(Byte.valueOf(localNode.getTextContent()).byteValue());
        else if ("eyesViewYLow".equals(localNode.getNodeName()))
          localEyesView.setEyesViewYLow(Byte.valueOf(localNode.getTextContent()).byteValue());
        else if ("eyesViewYHigh".equals(localNode.getNodeName()))
          localEyesView.setEyesViewYHigh(Byte.valueOf(localNode.getTextContent()).byteValue());
        else if ("mouseSpeed".equals(localNode.getNodeName()))
          localEyesView.setMouseSpeed(Byte.valueOf(localNode.getTextContent()).byteValue());
        j += 1;
      }
      localArrayList.add(localEyesView);
      i += 1;
    }
    return localArrayList;
  }

  public static ArrayList<NormalKey> getKeyList(File paramFile)
    throws Exception
  {
    ArrayList localArrayList = new ArrayList();
    paramFile = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(paramFile).getElementsByTagName("Key");
    int i = 0;
    while (i < paramFile.getLength())
    {
      NodeList localNodeList = paramFile.item(i).getChildNodes();
      NormalKey localNormalKey = new NormalKey();
      int j = 0;
      while (j < localNodeList.getLength())
      {
        Node localNode = localNodeList.item(j);
        if ("type".equals(localNode.getNodeName()))
          localNormalKey.setType(Byte.valueOf(localNode.getTextContent()).byteValue());
        else if ("code".equals(localNode.getNodeName()))
          localNormalKey.setCode(Byte.valueOf(localNode.getTextContent()).byteValue());
        else if ("p1xl".equals(localNode.getNodeName()))
          localNormalKey.setP1xl(Byte.valueOf(localNode.getTextContent()).byteValue());
        else if ("p1xh".equals(localNode.getNodeName()))
          localNormalKey.setP1xh(Byte.valueOf(localNode.getTextContent()).byteValue());
        else if ("p1yl".equals(localNode.getNodeName()))
          localNormalKey.setP1yl(Byte.valueOf(localNode.getTextContent()).byteValue());
        else if ("p1yh".equals(localNode.getNodeName()))
          localNormalKey.setP1yh(Byte.valueOf(localNode.getTextContent()).byteValue());
        else if ("p2xl".equals(localNode.getNodeName()))
          localNormalKey.setP2xl(Byte.valueOf(localNode.getTextContent()).byteValue());
        else if ("p2xh".equals(localNode.getNodeName()))
          localNormalKey.setP2xh(Byte.valueOf(localNode.getTextContent()).byteValue());
        else if ("p2yl".equals(localNode.getNodeName()))
          localNormalKey.setP2yl(Byte.valueOf(localNode.getTextContent()).byteValue());
        else if ("p2yh".equals(localNode.getNodeName()))
          localNormalKey.setP2yh(Byte.valueOf(localNode.getTextContent()).byteValue());
        j += 1;
      }
      localArrayList.add(localNormalKey);
      i += 1;
    }
    return localArrayList;
  }

  public static ArrayList<NormalKey> getKeyList(InputStream paramInputStream)
    throws Exception
  {
    ArrayList localArrayList = new ArrayList();
    paramInputStream = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(paramInputStream).getElementsByTagName("Key");
    int i = 0;
    while (i < paramInputStream.getLength())
    {
      NodeList localNodeList = paramInputStream.item(i).getChildNodes();
      NormalKey localNormalKey = new NormalKey();
      int j = 0;
      while (j < localNodeList.getLength())
      {
        Node localNode = localNodeList.item(j);
        if ("type".equals(localNode.getNodeName()))
          localNormalKey.setType(Byte.valueOf(localNode.getTextContent()).byteValue());
        else if ("code".equals(localNode.getNodeName()))
          localNormalKey.setCode(Byte.valueOf(localNode.getTextContent()).byteValue());
        else if ("p1xl".equals(localNode.getNodeName()))
          localNormalKey.setP1xl(Byte.valueOf(localNode.getTextContent()).byteValue());
        else if ("p1xh".equals(localNode.getNodeName()))
          localNormalKey.setP1xh(Byte.valueOf(localNode.getTextContent()).byteValue());
        else if ("p1yl".equals(localNode.getNodeName()))
          localNormalKey.setP1yl(Byte.valueOf(localNode.getTextContent()).byteValue());
        else if ("p1yh".equals(localNode.getNodeName()))
          localNormalKey.setP1yh(Byte.valueOf(localNode.getTextContent()).byteValue());
        else if ("p2xl".equals(localNode.getNodeName()))
          localNormalKey.setP2xl(Byte.valueOf(localNode.getTextContent()).byteValue());
        else if ("p2xh".equals(localNode.getNodeName()))
          localNormalKey.setP2xh(Byte.valueOf(localNode.getTextContent()).byteValue());
        else if ("p2yl".equals(localNode.getNodeName()))
          localNormalKey.setP2yl(Byte.valueOf(localNode.getTextContent()).byteValue());
        else if ("p2yh".equals(localNode.getNodeName()))
          localNormalKey.setP2yh(Byte.valueOf(localNode.getTextContent()).byteValue());
        j += 1;
      }
      localArrayList.add(localNormalKey);
      i += 1;
    }
    return localArrayList;
  }

  public static ArrayList<Resolution> getResolutionList(File paramFile)
    throws Exception
  {
    ArrayList localArrayList = new ArrayList();
    paramFile = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(paramFile).getElementsByTagName("Resolution");
    int i = 0;
    while (i < paramFile.getLength())
    {
      NodeList localNodeList = paramFile.item(i).getChildNodes();
      Resolution localResolution = new Resolution();
      int j = 0;
      while (j < localNodeList.getLength())
      {
        Node localNode = localNodeList.item(j);
        if ("resolutionXLow".equals(localNode.getNodeName()))
          localResolution.setResolutionXLow(Byte.valueOf(localNode.getTextContent()).byteValue());
        else if ("resolutionXHigh".equals(localNode.getNodeName()))
          localResolution.setResolutionXHigh(Byte.valueOf(localNode.getTextContent()).byteValue());
        else if ("resolutionYLow".equals(localNode.getNodeName()))
          localResolution.setResolutionYLow(Byte.valueOf(localNode.getTextContent()).byteValue());
        else if ("resolutionYHigh".equals(localNode.getNodeName()))
          localResolution.setResolutionYHigh(Byte.valueOf(localNode.getTextContent()).byteValue());
        j += 1;
      }
      localArrayList.add(localResolution);
      i += 1;
    }
    return localArrayList;
  }

  public static ArrayList<Resolution> getResolutionList(InputStream paramInputStream)
    throws Exception
  {
    ArrayList localArrayList = new ArrayList();
    paramInputStream = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(paramInputStream).getElementsByTagName("Resolution");
    int i = 0;
    while (i < paramInputStream.getLength())
    {
      NodeList localNodeList = paramInputStream.item(i).getChildNodes();
      Resolution localResolution = new Resolution();
      int j = 0;
      while (j < localNodeList.getLength())
      {
        Node localNode = localNodeList.item(j);
        if ("resolutionXLow".equals(localNode.getNodeName()))
          localResolution.setResolutionXLow(Byte.valueOf(localNode.getTextContent()).byteValue());
        else if ("resolutionXHigh".equals(localNode.getNodeName()))
          localResolution.setResolutionXHigh(Byte.valueOf(localNode.getTextContent()).byteValue());
        else if ("resolutionYLow".equals(localNode.getNodeName()))
          localResolution.setResolutionYLow(Byte.valueOf(localNode.getTextContent()).byteValue());
        else if ("resolutionYHigh".equals(localNode.getNodeName()))
          localResolution.setResolutionYHigh(Byte.valueOf(localNode.getTextContent()).byteValue());
        j += 1;
      }
      localArrayList.add(localResolution);
      i += 1;
    }
    return localArrayList;
  }

  public static ArrayList<pFileInfo> getpFileInfoList(File paramFile)
    throws Exception
  {
    ArrayList localArrayList = new ArrayList();
    paramFile = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(paramFile).getElementsByTagName("FileInfo");
    int i = 0;
    while (i < paramFile.getLength())
    {
      NodeList localNodeList = paramFile.item(i).getChildNodes();
      pFileInfo localpFileInfo = new pFileInfo();
      int j = 0;
      while (j < localNodeList.getLength())
      {
        Node localNode = localNodeList.item(j);
        if ("type".equals(localNode.getNodeName()))
          localpFileInfo.setType(Byte.valueOf(localNode.getTextContent()).byteValue());
        else if ("versionCodeLow".equals(localNode.getNodeName()))
          localpFileInfo.setVersionCodeLow(Byte.valueOf(localNode.getTextContent()).byteValue());
        else if ("versionCodeHigh".equals(localNode.getNodeName()))
          localpFileInfo.setVersionCodeHigh(Byte.valueOf(localNode.getTextContent()).byteValue());
        j += 1;
      }
      localArrayList.add(localpFileInfo);
      i += 1;
    }
    return localArrayList;
  }

  public static ArrayList<pFileInfo> getpFileInfoList(InputStream paramInputStream)
    throws Exception
  {
    ArrayList localArrayList = new ArrayList();
    paramInputStream = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(paramInputStream).getElementsByTagName("FileInfo");
    int i = 0;
    while (i < paramInputStream.getLength())
    {
      NodeList localNodeList = paramInputStream.item(i).getChildNodes();
      pFileInfo localpFileInfo = new pFileInfo();
      int j = 0;
      while (j < localNodeList.getLength())
      {
        Node localNode = localNodeList.item(j);
        if ("type".equals(localNode.getNodeName()))
          localpFileInfo.setType(Byte.valueOf(localNode.getTextContent()).byteValue());
        else if ("versionCodeLow".equals(localNode.getNodeName()))
          localpFileInfo.setVersionCodeLow(Byte.valueOf(localNode.getTextContent()).byteValue());
        else if ("versionCodeHigh".equals(localNode.getNodeName()))
          localpFileInfo.setVersionCodeHigh(Byte.valueOf(localNode.getTextContent()).byteValue());
        j += 1;
      }
      localArrayList.add(localpFileInfo);
      i += 1;
    }
    return localArrayList;
  }

  public static boolean writeProfileXML(ArrayList<pFileInfo> paramArrayList, ArrayList<Resolution> paramArrayList1, ArrayList<DirKey> paramArrayList2, ArrayList<EyesView> paramArrayList3, ArrayList<NormalKey> paramArrayList4, File paramFile)
  {
    Object localObject1 = DocumentBuilderFactory.newInstance();
    while (true)
    {
      try
      {
        localObject1 = ((DocumentBuilderFactory)localObject1).newDocumentBuilder();
        try
        {
          localObject1 = ((DocumentBuilder)localObject1).newDocument();
          Element localElement1 = ((Document)localObject1).createElement("Keys");
          i = 0;
          if (i >= paramArrayList.size())
            break label1847;
          Object localObject2 = ((Document)localObject1).createElement("FileInfo");
          Object localObject3 = new StringBuilder();
          ((StringBuilder)localObject3).append(i);
          ((StringBuilder)localObject3).append("");
          ((Element)localObject2).setAttribute("id", ((StringBuilder)localObject3).toString());
          localObject3 = ((Document)localObject1).createElement("type");
          ((Element)localObject3).setTextContent(String.valueOf(((pFileInfo)paramArrayList.get(i)).getType()));
          Element localElement2 = ((Document)localObject1).createElement("versionCodeLow");
          localElement2.setTextContent(String.valueOf(((pFileInfo)paramArrayList.get(i)).getVersionCodeLow()));
          Element localElement3 = ((Document)localObject1).createElement("versionCodeHigh");
          localElement3.setTextContent(String.valueOf(((pFileInfo)paramArrayList.get(i)).getVersionCodeHigh()));
          ((Element)localObject2).appendChild((Node)localObject3);
          ((Element)localObject2).appendChild(localElement2);
          ((Element)localObject2).appendChild(localElement3);
          localElement1.appendChild((Node)localObject2);
          i += 1;
          continue;
          if (i >= paramArrayList1.size())
            break label1853;
          paramArrayList = ((Document)localObject1).createElement("Resolution");
          localObject2 = new StringBuilder();
          ((StringBuilder)localObject2).append(i);
          ((StringBuilder)localObject2).append("");
          paramArrayList.setAttribute("id", ((StringBuilder)localObject2).toString());
          localObject2 = ((Document)localObject1).createElement("resolutionXLow");
          ((Element)localObject2).setTextContent(String.valueOf(((Resolution)paramArrayList1.get(i)).getResolutionXLow()));
          localObject3 = ((Document)localObject1).createElement("resolutionXHigh");
          ((Element)localObject3).setTextContent(String.valueOf(((Resolution)paramArrayList1.get(i)).getResolutionXHigh()));
          localElement2 = ((Document)localObject1).createElement("resolutionYLow");
          localElement2.setTextContent(String.valueOf(((Resolution)paramArrayList1.get(i)).getResolutionYLow()));
          localElement3 = ((Document)localObject1).createElement("resolutionYHigh");
          localElement3.setTextContent(String.valueOf(((Resolution)paramArrayList1.get(i)).getResolutionYHigh()));
          paramArrayList.appendChild((Node)localObject2);
          paramArrayList.appendChild((Node)localObject3);
          paramArrayList.appendChild(localElement2);
          paramArrayList.appendChild(localElement3);
          localElement1.appendChild(paramArrayList);
          i += 1;
          continue;
          if (i >= paramArrayList3.size())
            break label1859;
          paramArrayList = ((Document)localObject1).createElement("EyesView");
          paramArrayList1 = new StringBuilder();
          paramArrayList1.append(i);
          paramArrayList1.append("");
          paramArrayList.setAttribute("id", paramArrayList1.toString());
          paramArrayList1 = ((Document)localObject1).createElement("eyesViewXLow");
          paramArrayList1.setTextContent(String.valueOf(((EyesView)paramArrayList3.get(i)).getEyesViewXLow()));
          localObject2 = ((Document)localObject1).createElement("eyesViewXHigh");
          ((Element)localObject2).setTextContent(String.valueOf(((EyesView)paramArrayList3.get(i)).getEyesViewXHigh()));
          localObject3 = ((Document)localObject1).createElement("eyesViewYLow");
          ((Element)localObject3).setTextContent(String.valueOf(((EyesView)paramArrayList3.get(i)).getEyesViewYLow()));
          localElement2 = ((Document)localObject1).createElement("eyesViewYHigh");
          localElement2.setTextContent(String.valueOf(((EyesView)paramArrayList3.get(i)).getEyesViewYHigh()));
          localElement3 = ((Document)localObject1).createElement("mouseSpeed");
          localElement3.setTextContent(String.valueOf(((EyesView)paramArrayList3.get(i)).getMouseSpeed()));
          paramArrayList.appendChild(paramArrayList1);
          paramArrayList.appendChild((Node)localObject2);
          paramArrayList.appendChild((Node)localObject3);
          paramArrayList.appendChild(localElement2);
          paramArrayList.appendChild(localElement3);
          localElement1.appendChild(paramArrayList);
          i += 1;
          continue;
          paramArrayList = paramArrayList2;
          if (i >= paramArrayList2.size())
            break label1865;
          paramArrayList1 = ((Document)localObject1).createElement("DirKey");
          paramArrayList3 = new StringBuilder();
          paramArrayList3.append(i);
          paramArrayList3.append("");
          paramArrayList1.setAttribute("id", paramArrayList3.toString());
          paramArrayList3 = ((Document)localObject1).createElement("originXLow");
          paramArrayList3.setTextContent(String.valueOf(((DirKey)paramArrayList.get(i)).getOriginXLow()));
          localObject2 = ((Document)localObject1).createElement("originXHigh");
          ((Element)localObject2).setTextContent(String.valueOf(((DirKey)paramArrayList.get(i)).getOriginXHigh()));
          localObject3 = ((Document)localObject1).createElement("originYLow");
          ((Element)localObject3).setTextContent(String.valueOf(((DirKey)paramArrayList.get(i)).getOriginYLow()));
          localElement2 = ((Document)localObject1).createElement("originYHigh");
          localElement2.setTextContent(String.valueOf(((DirKey)paramArrayList.get(i)).getOriginYHigh()));
          localElement3 = ((Document)localObject1).createElement("dirRadiusLow");
          localElement3.setTextContent(String.valueOf(((DirKey)paramArrayList.get(i)).getDirRadiusLow()));
          Element localElement4 = ((Document)localObject1).createElement("dirRadiusHigh");
          paramArrayList3.setTextContent(String.valueOf(((DirKey)paramArrayList.get(i)).getDirRadiusHigh()));
          Element localElement5 = ((Document)localObject1).createElement("up");
          ((Element)localObject2).setTextContent(String.valueOf(((DirKey)paramArrayList.get(i)).getUp()));
          Element localElement6 = ((Document)localObject1).createElement("down");
          ((Element)localObject3).setTextContent(String.valueOf(((DirKey)paramArrayList.get(i)).getDown()));
          Element localElement7 = ((Document)localObject1).createElement("left");
          localElement2.setTextContent(String.valueOf(((DirKey)paramArrayList.get(i)).getLeft()));
          Element localElement8 = ((Document)localObject1).createElement("right");
          localElement3.setTextContent(String.valueOf(((DirKey)paramArrayList.get(i)).getRight()));
          paramArrayList1.appendChild(paramArrayList3);
          paramArrayList1.appendChild((Node)localObject2);
          paramArrayList1.appendChild((Node)localObject3);
          paramArrayList1.appendChild(localElement2);
          paramArrayList1.appendChild(localElement3);
          paramArrayList1.appendChild(localElement4);
          paramArrayList1.appendChild(localElement5);
          paramArrayList1.appendChild(localElement6);
          paramArrayList1.appendChild(localElement7);
          paramArrayList1.appendChild(localElement8);
          localElement1.appendChild(paramArrayList1);
          i += 1;
          continue;
          if (i < paramArrayList4.size())
          {
            paramArrayList1 = ((Document)localObject1).createElement("Key");
            paramArrayList2 = new StringBuilder();
            paramArrayList2.append(i);
            paramArrayList2.append("");
            paramArrayList1.setAttribute("id", paramArrayList2.toString());
            paramArrayList2 = ((Document)localObject1).createElement("originXLow");
            paramArrayList2.setTextContent(String.valueOf(((NormalKey)paramArrayList.get(i)).getType()));
            paramArrayList3 = ((Document)localObject1).createElement("originXHigh");
            paramArrayList3.setTextContent(String.valueOf(((NormalKey)paramArrayList.get(i)).getCode()));
            localObject2 = ((Document)localObject1).createElement("originYLow");
            ((Element)localObject2).setTextContent(String.valueOf(((NormalKey)paramArrayList.get(i)).getP1xl()));
            localObject3 = ((Document)localObject1).createElement("originYHigh");
            ((Element)localObject3).setTextContent(String.valueOf(((NormalKey)paramArrayList.get(i)).getP1xh()));
            localElement2 = ((Document)localObject1).createElement("dirRadiusLow");
            localElement2.setTextContent(String.valueOf(((NormalKey)paramArrayList.get(i)).getP1yl()));
            localElement3 = ((Document)localObject1).createElement("dirRadiusHigh");
            paramArrayList2.setTextContent(String.valueOf(((NormalKey)paramArrayList.get(i)).getP1yh()));
            localElement4 = ((Document)localObject1).createElement("up");
            paramArrayList3.setTextContent(String.valueOf(((NormalKey)paramArrayList.get(i)).getP2xl()));
            localElement5 = ((Document)localObject1).createElement("down");
            ((Element)localObject2).setTextContent(String.valueOf(((NormalKey)paramArrayList.get(i)).getP2xh()));
            localElement6 = ((Document)localObject1).createElement("left");
            ((Element)localObject3).setTextContent(String.valueOf(((NormalKey)paramArrayList.get(i)).getP2yl()));
            localElement7 = ((Document)localObject1).createElement("right");
            localElement2.setTextContent(String.valueOf(((NormalKey)paramArrayList.get(i)).getP2yh()));
            paramArrayList1.appendChild(paramArrayList2);
            paramArrayList1.appendChild(paramArrayList3);
            paramArrayList1.appendChild((Node)localObject2);
            paramArrayList1.appendChild((Node)localObject3);
            paramArrayList1.appendChild(localElement2);
            paramArrayList1.appendChild(localElement3);
            paramArrayList1.appendChild(localElement4);
            paramArrayList1.appendChild(localElement5);
            paramArrayList1.appendChild(localElement6);
            paramArrayList1.appendChild(localElement7);
            localElement1.appendChild(paramArrayList1);
            i += 1;
            paramArrayList = paramArrayList4;
            continue;
          }
          ((Document)localObject1).appendChild(localElement1);
          paramArrayList = TransformerFactory.newInstance().newTransformer();
          paramArrayList.setOutputProperty("encoding", "UTF-8");
          paramArrayList1 = new FileOutputStream(paramFile);
          paramArrayList.transform(new DOMSource((Node)localObject1), new StreamResult(paramArrayList1));
          return true;
        }
        catch (FileNotFoundException paramArrayList)
        {
        }
        catch (TransformerConfigurationException paramArrayList)
        {
        }
        catch (ParserConfigurationException paramArrayList)
        {
        }
      }
      catch (TransformerException paramArrayList)
      {
        paramArrayList.printStackTrace();
        return false;
      }
      catch (FileNotFoundException paramArrayList)
      {
        paramArrayList.printStackTrace();
        return false;
      }
      catch (TransformerConfigurationException paramArrayList)
      {
        paramArrayList.printStackTrace();
        return false;
      }
      catch (ParserConfigurationException paramArrayList)
      {
      }
      paramArrayList.printStackTrace();
      return false;
      label1847: int i = 0;
      continue;
      label1853: i = 0;
      continue;
      label1859: i = 0;
      continue;
      label1865: paramArrayList = paramArrayList4;
      i = 0;
    }
  }
}
