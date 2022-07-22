/**
 * @author xiongliff
 * @version V1.0
 * @Package PACKAGE_NAME
 * @date 2021/11/19 0:14
 */

/*
    链表类实现，增删改查
 */

public class Node {
    public int value;
    public Node next;

    public Node(int value){
        this.value = value;
        this.next =null;
    }

    //通过值进行查询

    public static Node findNodeByValue(int value, Node source){
        //while循环比递归好用，判断条件即 节点值不等，节点还有next
        while(source.value != value && source.next != null){

            source = source.next;
        }
        //当结束循环时，有两种可能：1.已经找到值，那么返回source即可，2.节点已经遍历完成但是没有找到节点，那么返回null
        return source.value == value ? source:null;
    }

    //查找节点的上一个节点

    public static Node findPreNodeByValue(int value, Node source){
        //主要是要有一个temp保存一下上一个节点值
        Node temp = null;
        while(source.value != value && source.next !=null ){
            //相当于保存的上一个节点值
            temp = source;
            source = source.next;
        }
        //当结束循环时，有两种可能：1.已经找到值，那么返回temp即可，2.节点已经遍历完成但是没有找到节点，那么返回null
        return source.value == value ? temp :null;

    }
    //插入节点
    public static boolean insert(int value, Node source, Node newNode){
        Node currentNode = findNodeByValue(value, source);
        if(currentNode == null){
            System.out.println("insert fail!");
            return  false;
        }else{
            Node temp =currentNode.next ;
            currentNode.next = newNode;
            newNode.next = temp;
            return true;
        }
    }

    //删除节点
    public static  boolean delete(Node source, Node node){
        Node preNode = findPreNodeByValue(node.value, source);
        if(preNode == null){
            System.out.println("delete fail");
            return false;
        }else{
            preNode.next = null;
            return true;
        }

    }

    //打印节点的所有值
    public static void printNode(Node source){
        String temp ="";
        while(source.next != null){
            temp = temp + source.value;
            source = source.next;
        }
        //注意循环结束后，还需要加上最后一个节点的value
        temp = temp+ source.value;
        System.out.println(temp);
    }


  public static void main(String[] args) {
    //
      Node node1 = new Node(1);
      Node node2 = new Node(2);
      Node node3 = new Node(3);
      Node node4 = new Node(4);
      Node node5 = new Node(5);
      node1.next = node2;
      node2.next = node3;
      node3.next = node4;
      node4.next = node5;
    // Node res = Node.findPreNodeByValue(1,node1);
    // if(res != null){
    //    System.out.println( res.value);
    // }else{
    //      System.out.println("找不到");
    // }

    //      delete(node3, node5);
    //      printNode(node3);
    Node newNode = new Node(7);
    insert(1,node1, newNode);
    printNode(node1);
  }
}
