package list.linkedlist.implementation;
 
public class LinkedList {
    // 첫번째 노드를 가리키는 필드
    private Node head;
    private Node tail;
    private int size = 0;
    
    private class Node {
        // 데이터가 저장될 필드
        private Object data;
        // 다음 노드를 가리키는 필드
        private Node next;
        public Node(Object input) {
            this.data = input;
            this.next = null;
        }
        // 노드의 내용을 쉽게 출력해서 확인해볼 수 있는 기능
        public String toString() {
            return String.valueOf(this.data);
        }
    }
    
    public void addFirst(Object input) {
        // 노드를 생성한다.
        Node newNode = new Node(input);
        // 새로운 노드의 다음 노드로 해드를 지정한다.
        newNode.next = head;
        // 헤드로 새로운 노드를 지정한다.
        head = newNode;
        size++;
        if(head.next == null){
            tail = head;
        }
    }
    
    public void addLast(Object input) {
        // 노드를 생성한다.
        Node newNode = new Node(input);
        // 리스트의 노드가 없다면 첫번째 노드를 추가하는 메소드를 사용한다.
        if(size == 0) {
            addFirst(input);
        } else {
            // 마지막 노드의 다음 노드로 생성한 노드를 지정한다.
            tail.next = newNode;
            // 마지막 노드를 갱신한다.
            tail = newNode;
            // 엘리먼트의 개수를 1 증가 시킨다.
            size++;
        }
    }
    
    Node node(int index) {
        Node x = head;
        for (int i = 0; i < index; i++)
            x = x.next;
        return x;
    }
    
    public void add(int k, Object input) {
        // 만약 k가 0이라면 첫번째 노드에 추가하는 것이기 때문에 addFirst를 사용한다.
        if(k == 0) {
            addFirst(input);
        } else {
            Node temp1 = node(k - 1);
            // k 번째 노드를 temp2로 지정한다.
            Node temp2 = temp1.next;
            // 새로운 노드를 생성한다.
            Node newNode = new Node(input);
            // temp1의 다음 노드로 새로운 노드를 지정한다.
            temp1.next = newNode;
            // 새로운 노드의 다음 노드로 temp2를 지정한다.
            newNode.next = temp2;
            size++;
            // 새로운 노드의 다음 노드가 없다면 새로운 노드가 마지막 노드이기 때문에 tail로 지정한다.
            if(newNode.next == null) {
                tail = newNode;
            }
        }
    }
    
    public String toString() {
        // 노드가 없다면 []를 리턴한다.
        if(head == null) {
            return "[]";
        }       
        // 탐색을 시작한다.
        Node temp = head;
        String str = "[";
        // 다음 노드가 없을 때까지 반복문을 실행한다.
        // 마지막 노드는 다음 노드가 없기 때문에 아래의 구문이 마지막 노드는 제외된다.
        while(temp.next != null) {
            str += temp.data + ", ";
            temp = temp.next;
        }
        // 마지막 노드를 출력결과에 포함시킨다.
        str += temp.data;
        return str + "]";
    }
    
    public Object removeFirst() {
        // 첫번째 노드를 temp로 지정하고 head의 값을 두번째 노드로 변경한다.
        Node temp = head;
        head = temp.next;
        // 데이터를 삭제하기 전에 리턴할 값을 임시 변수에 담는다. 
        Object returnData = temp.data;
        temp = null;
        size--;
        return returnData;
    }
    
    public Object remove(int k) {
        if(k == 0)
            return removeFirst();
        // k-1번째 노드를 temp의 값으로 지정한다.
        Node temp = node(k - 1);
        // 삭제 노드를 todoDeleted에 기록해 둔다. 
        // 삭제 노드를 지금 제거하면 삭제 앞 노드와 삭제 뒤 노드를 연결할 수 없다.  
        Node todoDeleted = temp.next;
        // 삭제 앞 노드의 다음 노드로 삭제 뒤 노드를 지정한다.
        temp.next = temp.next.next;
        // 삭제된 데이터를 리턴하기 위해서 returnData에 데이터를 저장한다.
        Object returnData = todoDeleted.data; 
        if(todoDeleted == tail) {
            tail = temp;
        }
        // cur.next를 삭제한다.
        todoDeleted = null; 
        size--;
        return returnData;
    }
    
    public Object removeLast() {
    	return remove(size - 1);
    }
    
    public int size() {
        return size;
    }
    
    public Object get(int k) {
        Node temp = node(k);
        return temp.data;
    }
    
    public int indexOf(Object data) {
        // 탐색 대상이 되는 노드를 temp로 지정한다.
        Node temp = head;
        // 탐색 대상이 몇번째 엘리먼트에 있는지를 의미하는 변수로 `index`를 사용한다.
        int index = 0;
        // 탐색 값과 탐색 대상의 값을 비교한다. 
        while(temp.data != data) {
            temp = temp.next;
            index++;
            // temp의 값이 null이라는 것은 더 이상 탐색 대상이 없다는 것을 의미한다.이 때 -1을 리턴한다.
            if(temp == null)
                return -1;
        }
        // 탐색 대상을 찾았다면 대상의 인덱스 값을 리턴한다.
        return index;
    }
    
    // 반복자를 생성해서 리턴해준다.
    public ListIterator listIterator() {
        return new ListIterator();
    }
    
    public class ListIterator {
        private Node lastReturned;
        private Node next;
        private int nextIndex;
         
        ListIterator() {
            next = head;
            nextIndex = 0;
        }
        
        public Object next() {
            lastReturned = next;
            next = next.next;
            nextIndex++;
            return lastReturned.data;
        }
        
        public boolean hasNext() {
            return nextIndex < size();
        }
        
        public void add(Object input) {
            Node newNode = new Node(input);
            if(lastReturned == null) {
                head= newNode;
                newNode.next = next;
            } else {
                lastReturned.next = newNode;
                newNode.next = next;
            }
            lastReturned = newNode;
            nextIndex++;
            size++;
        }
        
        public void remove() {
            if(nextIndex == 0) {
                throw new IllegalStateException();
            }
            LinkedList.this.remove(nextIndex - 1);
            nextIndex--;
        }
    }
}