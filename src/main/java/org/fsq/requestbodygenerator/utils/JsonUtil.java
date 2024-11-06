package org.fsq.requestbodygenerator.utils;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

public class JsonUtil {
    /**
     * Extract Pojo from Jackson Pojo Node
     * @param root Parent node
     * @param nodeKey Key of the node to be extracted
     * @param c Type of node to be extracted
     * @return The object inside the jackson PojoNode
     * @throws IOException if ObjectMapper#treeTovalue fails
     */
    public static Object getPojo(JsonNode root, String nodeKey, Class<?> c) throws IOException {
        ObjectMapper om = new ObjectMapper();
        JsonNode node = root.findValue(nodeKey);
        Object res = om.treeToValue(node, c);
        return res;
    }
}
