package edu.du.werthan;
import edu.du.dudraw.DUDraw;
public class Driver {
	public static void main(String[] args) {
//		if(DLLTest()) {
//			System.out.println("test passed");
//		}else {
//			System.out.println("test failed");
//		}
		Snake s = new Snake();
		while(true) {
			s.update();
			DUDraw.show();
		}
	}

	public static boolean DLLTest() {
		/* DoublyLinkedList verify
	     * Returns false if any errors in the DoublyLinkedList are detected.
	     * Also outputs error message to console.
	     */
		DLL dll = new DLL();
        if(dll.getHead() == null || dll.getTail() == null) {
            System.err.println("Header or trailer node is null");
            return false;
        }
        if (dll.size() == 0) {
            if(dll.getHead().getNext() != dll.getTail() || dll.getTail().getPrev() != dll.getHead()) {
                System.err.println("DLL verify: list size is 0 but header and trailer references are incorrect.");
                return false;
            }
        }
        if(dll.getHead().getNext() == dll.getTail()) {
            if(dll.size() != 0) {
                System.err.println("DLL verify: list is empty but size is not 0.");
                return false;
            }
        }
        else {
            // Traverse the list. Make sure none of the values are null and check that the references are correct.
            // Also count the nodes and make sure the count matches the size
            Node temp = dll.getHead().getNext();
            int count = 0;
            while (temp != dll.getTail()) {
                if(temp.getPrev() == null || temp.getNext() == null) {
                    System.err.println("DLL verify: node at index: " + count + " has a pointer to null.");
                    return false;
                }

                if(temp.getNext().getPrev() != temp || temp.getPrev().getNext() != temp) {
                    System.err.println("DLL verify: neighbor of node at index " + count + " has incorrect pointers");
                    return false;
                }

                if (temp.getVal() == null) {
                    System.err.println("DLL verify: node index " + count + " has null pointer for value");
                    return false;
                }
                temp = temp.getNext();
                count++;
            }
            if (count != dll.size()) {
                System.err.println("DLL verify: size (" + dll.size() + ") does not match the number of nodes (" + count + ").");
                return false;
            }
        }
        return true;
	}
}

