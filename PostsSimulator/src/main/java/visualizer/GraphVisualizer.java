package visualizer;

import guru.nidi.graphviz.engine.Format;
import guru.nidi.graphviz.engine.Graphviz;
import guru.nidi.graphviz.model.MutableGraph;
import guru.nidi.graphviz.model.MutableNode;
import syntax.ExpressionParser;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static guru.nidi.graphviz.model.Factory.mutGraph;
import static guru.nidi.graphviz.model.Factory.mutNode;

public class GraphVisualizer {
    private final ExpressionParser.Node node;
    private int currentNodeIndex = 0;

    public GraphVisualizer(ExpressionParser.Node node) {
        this.node = node;
    }

    public void visualize() {
        MutableGraph mutableGraph = mutGraph("parsedTreeGraph").setDirected(true);

        for (MutableNode mutableNode : getMutableNodeList(node)) {
            mutableGraph.add(mutableNode);
        }

        try {
            Graphviz.fromGraph(mutableGraph).render(Format.PNG).toFile(new File("graph/parsedTreeGraph.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private List<MutableNode> getMutableNodeList(ExpressionParser.Node node) {
        List<MutableNode> mutableNodeList = new ArrayList<>();

        String currentNodeName = node.getGrammarObject() + " : " + currentNodeIndex;

        currentNodeIndex++;

        mutableNodeList.add(mutNode(currentNodeName));

        for (int i = node.getChildren().size() - 1; i >= 0; i--) {
            ExpressionParser.Node child = node.getChildren().get(i);

            List<MutableNode> childMutableNodeList = getMutableNodeList(child);

            mutableNodeList.addAll(childMutableNodeList);

            mutableNodeList.add(mutNode(currentNodeName).addLink(childMutableNodeList.get(0)));
        }

        return mutableNodeList;
    }
}
