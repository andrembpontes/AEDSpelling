package aed.dataStructures;

import java.lang.reflect.Array;
import java.util.Random;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * @author Andre Pontes (42845) <am.pontes@campus.fct.unl.pt>
 * @author Goncalo Marcelino (43178) <gb.marcelino@campus.fct.unl.pt>
 *
 */
public class TestLists {

	public static final int testPopulation = 10000; // Integer.MAX_VALUE;

	private List<String> arrayList, linkedList;

	@Test
	public void hardIntegrity() {
		java.util.List<String> javaList = new java.util.LinkedList<String>();

		@SuppressWarnings("unchecked")
		List<String>[] lists = (List<String>[]) Array
				.newInstance(List.class, 2);
		lists[0] = this.linkedList;
		lists[1] = this.arrayList;

		@SuppressWarnings("unchecked")
		java.util.List<String>[] javaLists = (java.util.List<String>[]) Array
				.newInstance(java.util.List.class, 1);
		javaLists[0] = javaList;

		this.randomPopulateLists(lists, javaLists, TestLists.testPopulation);
		this.testListElements(javaList, this.arrayList, this.linkedList);

		this.randomRemove(lists, javaLists, TestLists.testPopulation / 4);
		this.testListElements(javaList, this.arrayList, this.linkedList);

	}

	@Before
	public void initialize() {
		this.linkedList = new LinkedList<String>();
		this.arrayList = new ArrayList<String>();
	}

	@Test
	public void integrity() {
		int i = TestLists.testPopulation;
		java.util.List<String> javaList = new java.util.LinkedList<String>();

		@SuppressWarnings("unchecked")
		List<String>[] lists = (List<String>[]) Array
				.newInstance(List.class, 2);
		lists[0] = this.linkedList;
		lists[1] = this.arrayList;

		@SuppressWarnings("unchecked")
		java.util.List<String>[] javaLists = (java.util.List<String>[]) Array
				.newInstance(java.util.List.class, 1);
		javaLists[0] = javaList;

		this.populateLists(lists, javaLists, i);
		this.testListElements(javaList, this.arrayList, this.linkedList);
	}

	public void populateLists(List<String>[] lists,
			java.util.List<String>[] javaLists, int elementsNumber) {
		Random rand = new Random();
		int i;
		for (i = 0; i < elementsNumber - 1; i++) {
			String str = new String(Integer.toString(rand
					.nextInt(Integer.MAX_VALUE)));

			for (List<String> list : lists)
				list.add(str);

			for (java.util.List<String> javaList : javaLists)
				javaList.add(str);
		}
	}

	public void randomPopulateLists(List<String>[] lists,
			java.util.List<String>[] javaLists, int elementsNumber) {
		Random rand = new Random();
		int i;
		for (i = 0; i < elementsNumber - 1; i++) {
			String str = new String(Integer.toString(rand
					.nextInt(Integer.MAX_VALUE)));
			int insertAt;

			if (i < 2)
				insertAt = 0;
			else
				insertAt = rand.nextInt(i - 1);

			for (java.util.List<String> javaList : javaLists)
				javaList.add(insertAt, str);

			for (List<String> list : lists)
				list.insert(insertAt, str);

			for (List<String> list : lists)
				Assert.assertEquals(list.size(), javaLists[0].size());
		}
	}

	public void randomRemove(List<String>[] lists,
			java.util.List<String>[] javaLists, int elementsNumber) {
		Random rand = new Random();
		int i;
		for (i = 0; i < elementsNumber - 1; i++) {
			int removeAt = rand.nextInt(i + 1);

			for (List<String> list : lists)
				list.remove(removeAt);

			for (java.util.List<String> javaList : javaLists)
				javaList.remove(removeAt);
		}
	}

	@Test
	public void size() {
		int i;
		for (i = 0; i < TestLists.testPopulation; i++) {
			Assert.assertEquals(this.linkedList.size(), i);
			Assert.assertEquals(this.arrayList.size(), i);

			String str = new String(Integer.toString(i));

			this.linkedList.add(str);
			this.arrayList.add(str);
		}
		Assert.assertEquals(this.linkedList.size(), i);
		Assert.assertEquals(this.arrayList.size(), i);
	}

	public void testListElements(java.util.List<String> javaList,
			List<String> arrayList, List<String> linkedList) {
		// test size
		Assert.assertEquals("LinkedList size error", javaList.size(),
				this.linkedList.size());
		Assert.assertEquals("ArrayList size error", javaList.size(),
				this.arrayList.size());

		// test elements
		Iterator<String> linkedListIterator = this.linkedList.iterator();
		Iterator<String> arrayListIterator = this.arrayList.iterator();
		java.util.Iterator<String> javaIterator = javaList.iterator();

		while (linkedListIterator.hasNext() || arrayListIterator.hasNext()
				|| javaIterator.hasNext()) {
			Assert.assertEquals("LinkedListIterator hasNext error!",
					linkedListIterator.hasNext(), javaIterator.hasNext());
			Assert.assertEquals("ArrayListIterator hasNext error!",
					arrayListIterator.hasNext(), javaIterator.hasNext());

			String str = javaIterator.next();
			Assert.assertEquals("ArrayList integrity error!",
					arrayListIterator.next(), str);
			Assert.assertEquals("LinkedList itegrity error!",
					linkedListIterator.next(), str);
		}
	}
}
