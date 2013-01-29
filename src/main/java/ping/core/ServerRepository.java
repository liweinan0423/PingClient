package ping.core;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * data format:
 * <servers>
 * <server name="server1" address="192.168.0.1"  />
 * </servers>
 */
public class ServerRepository {

    private static final Logger logger = Logger.getLogger("ServerRepository");

    private static final String USER_HOME = System.getProperty("user.home");
    private static final String DATA_FOLDER = ".pingclient";
    private static final String DATA_FILE = "servers.xml";
    private static final String DATA_FILE_FULL_PATH = USER_HOME + "/" + DATA_FOLDER + "/" + DATA_FILE;

    static {
        ensureDataFile();
    }

    private static void ensureDataFile() {
        File file = new File(DATA_FILE_FULL_PATH);
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                logger.log(Level.WARNING, "Cannot create data file", e);
                throw new RuntimeException(e);
            }


            try {
                DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
                DocumentBuilder builder = factory.newDocumentBuilder();
                Document document = builder.newDocument();
                Element element = document.createElement("servers");
                document.appendChild(element);

                TransformerFactory transformerFactory = TransformerFactory.newInstance();
                Transformer transformer = transformerFactory.newTransformer();
                Source source = new DOMSource(document);
                Result result = new StreamResult(new FileOutputStream(DATA_FILE_FULL_PATH));
                transformer.transform(source, result);
            } catch (Exception e) {
                logger.log(Level.WARNING, e.getMessage(), e);
            }
        }
    }

    public Server findByName(String name) {

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        Server server = null;
        try {
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse(new File(DATA_FILE_FULL_PATH));
            NodeList serverList = document.getElementsByTagName("server");
            for (int i = 0; i < serverList.getLength(); i++) {
                Element element = (Element) serverList.item(i);
                if (name.equals(element.getAttribute("name"))) {
                    server = new Server();
                    server.setName(element.getAttribute("name"));
                    server.setAddress(element.getAttribute("address"));
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage(), e);
        }

        return server;
    }

    public void saveServer(Server server) throws DuplicateServerDefinitionException {

        if (findByName(server.getName()) != null) {
            throw new DuplicateServerDefinitionException("服务器[" + server.getName() + "]已经存在");
        }


        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse(new File(DATA_FILE_FULL_PATH));
            Element root = document.getDocumentElement();
            Element element = document.createElement("server");
            element.setAttribute("name", server.getName());
            element.setAttribute("address", server.getAddress());
            root.appendChild(element);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    public List<Server> findAllServers() {
        List<Server> serverList = new ArrayList<Server>();
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse(new File(DATA_FILE_FULL_PATH));
            NodeList nodeList = document.getElementsByTagName("server");
            for (int i = 0; i < nodeList.getLength(); i++) {
                Element element = (Element) nodeList.item(i);
                Server server = new Server();
                server.setName(element.getAttribute("name"));
                server.setAddress(element.getAttribute("address"));
                serverList.add(server);

            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return serverList;
    }

    public void removeServer(Server server) {
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse(new File(DATA_FILE_FULL_PATH));
            NodeList nodeList = document.getElementsByTagName("server");
            for (int i = 0; i < nodeList.getLength(); i++) {
                Element element = (Element) nodeList.item(i);
                if (server.getName().equals(element.getAttribute("name"))) {
                    document.getDocumentElement().removeChild(element);
                }

            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void removeServerByName(String name) {
        removeServer(findByName(name));
    }
}