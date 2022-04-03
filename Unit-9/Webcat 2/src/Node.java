
public class Node {
		public Person item;
		public Node left;
		public Node right;

		public Node(Person person) {
			this.item = person;
			this.left = null;
			this.right = null;
		}
	}