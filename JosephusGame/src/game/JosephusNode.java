package game;
/*
	Name: Kim Vallido
	Date: 2/29/2020
	File name: JosephusNode.java
 */

/**
 * A doubly-linked list node used in JosephusCircle.
 *
 * @author Kim Vallido
 * @version 1.0
 */
public class JosephusNode
{
    private Object data;
    private JosephusNode next;
    private JosephusNode previous;

    /**
     * Creates a new JosephusNode, to be part of a JosephusCircle.
     *
     * @param data Data of your choosing to be held in
     *             the node.
     */
    public JosephusNode(Object data)
    {
        this.data = data;
    }

    /**
     * Creates a new JosephusNode with data of your choosing
     * and allows you to set a next and previous node.
     *
     * @param data Data of your choosing to be held in
     *             the node.
     * @param next References the next JosephusNode in the JosephusCircle
     * @param previous References the previous JosephusNode in the JosephusCircle
     */
    public JosephusNode(Object data, JosephusNode next, JosephusNode previous)
    {
        this.data = data;
        this.next = next;
        this.previous = previous;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public JosephusNode getNext() {
        return next;
    }

    public void setNext(JosephusNode next) {
        this.next = next;
    }

    public JosephusNode getPrevious() {
        return previous;
    }

    public void setPrevious(JosephusNode previous) {
        this.previous = previous;
    }

    /**
     * String representation of the data held in the
     * JosephusNode.
     * @return data held in the JosephusNode
     */
    public String toString()
    {
        return data.toString();
    }
}
