package forest;

import java.util.Comparator;

/**
 * NodeComparatorクラス
 * Nodeを辞書順にソートするためのクラス
 */
public class NodeComparator implements Comparator<Node> {

  @Override
  public int compare(Node node1, Node node2) {
    return node1.getName().compareTo(node2.getName());
  }
}
