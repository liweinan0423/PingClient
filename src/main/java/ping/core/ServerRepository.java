package ping.core;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * data format:
 * <servers>
 * <server id="uuid" name="server1" address="192.168.0.1"  />
 * </servers>
 */
public class ServerRepository {

    private static final Logger logger = Logger.getLogger("ServerRepository");

    private static final String USER_HOME = System.getProperty("user.home");
    private static final String DATA_FOLDER = ".pingclient";
    private static final String DATA_FILE = "servers.xml";
    public static final String DATA_FILE_FULL_PATH = USER_HOME + "/" + DATA_FOLDER + "/" + DATA_FILE;

    private String dataFile;


    public ServerRepository(String dataFile) {
        if (dataFile == null || "".equals(dataFile)) {
            this.dataFile = DATA_FILE_FULL_PATH;
        } else {
            this.dataFile = dataFile;
        }

        ensureDataFile();
    }

    private void ensureDataFile() {
        File file = new File(dataFile);
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

                writeDocument(document);
            } catch (Exception e) {
                logger.log(Level.WARNING, e.getMessage(), e);
            }
        }
    }

    public Server findByName(String name) {

        Server server = null;
        try {
            Document document = readDocument();

            NodeList serverList = document.getElementsByTagName("server");
            for (int i = 0; i < serverList.getLength(); i++) {
                Element element = (Element) serverList.item(i);
                if (name.equals(element.getAttribute("name"))) {
                    server = deserialize(element);
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage(), e);
        }

        return server;
    }

    private Server deserialize(Element element) {
        Server server = new Server();
        server.setId(element.getAttribute("id"));
        server.setName(element.getAttribute("name"));
        server.setAddress(element.getAttribute("address"));

        String latency = element.getAttribute("latency");
        server.setLatency((latency == null || "".equals(latency)) ? -1 : Integer.valueOf(latency));

        String status = element.getAttribute("status");
        server.setStatus(Integer.valueOf((status == null || "".equals(status)) ? Server.UNKNOWN : Integer.valueOf(status)));

        return server;
    }

    public void saveServer(Server server) throws DuplicateServerDefinitionException {

        if (findByName(server.getName()) != null) {
            throw new DuplicateServerDefinitionException("服务器[" + server.getName() + "]已经存在");
        }


        try {

            server.setId(UUID.randomUUID().toString());

            Document document = readDocument();

            Element root = document.getDocumentElement();
            Element element = document.createElement("server");
            element.setAttribute("id", server.getId());
            element.setAttribute("name", server.getName());
            element.setAttribute("address", server.getAddress());
            element.setAttribute("latency", String.valueOf(server.getLatency()));
            element.setAttribute("status", String.valueOf(server.getStatus()));
            root.appendChild(element);

            writeDocument(document);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    public List<Server> findAllServers() {
        List<Server> serverList = new ArrayList<Server>();
        try {
            Document document = readDocument();

            NodeList nodeList = document.getElementsByTagName("server");
            for (int i = 0; i < nodeList.getLength(); i++) {
                Element element = (Element) nodeList.item(i);
                Server server = deserialize(element);
                serverList.add(server);

            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return serverList;
    }

    public void removeServer(Server server) {
        try {
            Document document = readDocument();

            NodeList nodeList = document.getElementsByTagName("server");
            for (int i = 0; i < nodeList.getLength(); i++) {
                Element element = (Element) nodeList.item(i);
                if (server.getName().equals(element.getAttribute("name"))) {
                    document.getDocumentElement().removeChild(element);
                }

            }

            writeDocument(document);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void removeServerByName(String name) {
        removeServer(findByName(name));
    }


    private void writeDocument(Document document) throws TransformerException, FileNotFoundException {
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        Source source = new DOMSource(document);
        Result result = new StreamResult(new FileOutputStream(dataFile));
        transformer.transform(source, result);
    }

    private Document readDocument() throws ParserConfigurationException, IOException, SAXException {
        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = documentBuilderFactory.newDocumentBuilder();
        Document document = builder.parse(new File(dataFile));
        return document;
    }

    public void updateServer(Server server) {
        try {
            Document document = readDocument();
            NodeList nodeList = document.getElementsByTagName("server");

            for (int i = 0; i < nodeList.getLength(); i++) {
                Element element = (Element) nodeList.item(i);
                if (element.getAttribute("id").equals(server.getId())) {
                    element.setAttribute("name", server.getName());
                    element.setAttribute("address", server.getAddress());
                    element.setAttribute("latency", String.valueOf(server.getLatency()));
                    element.setAttribute("status", String.valueOf(server.getStatus()));
                }
            }

            writeDocument(document);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    public Server findById(String id) {
        Server server = null;
        try {
            Document document = readDocument();
            NodeList nodeList = document.getElementsByTagName("server");

            for (int i = 0; i < nodeList.getLength(); i++) {
                Element element = (Element) nodeList.item(i);
                if (element.getAttribute("id").equals(id)) {
                    server = deserialize(element);
                }
            }

            return server;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}