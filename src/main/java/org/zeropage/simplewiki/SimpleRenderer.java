package org.zeropage.simplewiki;

import static org.pegdown.FastEncoder.encode;

import org.parboiled.common.StringUtils;
import org.pegdown.LinkRenderer;
import org.pegdown.ast.ExpLinkNode;

public class SimpleRenderer extends LinkRenderer{
    public Rendering render(ExpLinkNode node, String text) {
        Rendering rendering = new Rendering("./wiki/" + node.url, text);
        return StringUtils.isEmpty(node.title) ? rendering : rendering.withAttribute("title", encode(node.title));
    }	
}
