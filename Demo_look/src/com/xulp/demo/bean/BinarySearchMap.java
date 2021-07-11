package com.xulp.demo.bean;

/**
 * 使用有序数组构建的符号表
 * 
 * @author xulp
 *
 */
public class BinarySearchMap extends BaseMap<String, Integer> {

	private Comparable<String>[] keys = new Comparable[20000];
	private int[] values = new int[20000];
	private int size = 0;

	@Override
	public void put(String key, int value) {
		if (contains(key)) {
			int index = rank(key, 0, size - 1);
			values[index] = value;
		} else {
			int index = rank(key, 0, size - 1);// 获取到元素应该出现的位置索引
			for (int i = size - 1; i > index; i--) {
				keys[i] = keys[i - 1];
				values[i] = values[i - 1];
			}
			keys[index] = key;
			values[index] = value;

			size++;
		}
	}

	@Override
	public int get(String key) {
		int index = rank(key, 0, size - 1);
		return index;
	}

	@Override
	public boolean contains(String key) {
		for (Comparable<String> s : keys) {
			if (key.equals(s))
				return true;
		}
		return false;
	}

	@Override
	public Object[] keys() {
		return keys;
	}

	/**
	 * 递归的目的有两个，一个是元素存在时，定位到元素所在的位置 而是，元素不存在时，应该定位到元素应该存在的位置
	 * 
	 * @param key
	 * @param low
	 * @param high
	 * @return
	 */
//	public int rank(String key, int low, int high) {
//
//		if (low >= high)
//			return low;
//
//		int mid = low + (high - low) / 2;
//		if(mid < 0)
//			return low;
//		int comp = (keys[mid]+"").compareTo(key);
//		if (comp > 0) {
//			return rank(key, low, mid - 1);
//		} else if (comp < 0) {
//			return rank(key, mid + 1, high);
//		} else {
//			return mid;
//		}
//
//	}

	/**
	 * 递归的目的有两个，一个是元素存在时，定位到元素所在的位置 而是，元素不存在时，应该定位到元素应该存在的位置
	 * 
	 * @param key
	 * @param low
	 * @param high
	 * @return
	 */
	public int rank(String key, int low, int high) {

		while (low <= high) {
			int mid = low + (high - low) / 2;
			if ((keys[mid] + "").compareTo(key) > 0) {
				high = mid - 1;
			} else if ((keys[mid] + "").compareTo(key) < 0) {
				low = mid + 1;
			} else {
				return mid;
			}
		}

		return low;

	}

}
