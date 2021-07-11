package com.xulp.demo;

public class Test1 {

	public static void main(String[] args) {
		// System.out.println("Hello World! VSC");
		// int num = (int) (Math.random() * 100);
		// System.out.println("这是随机数 : " + num);

//		String ss = "adAfBgChABCABCDbvd";
//		String tt = "ABCABCD";
//		int[] next = new int[tt.length()];
//		getNext(next, tt);
//		next = getNextArray(tt.toCharArray());
//		int index = kmpMatch(ss, tt);
//		System.out.println("匹配上的索引 index : " + index);
		
		int [] next = getSuffixValue2("aaaaaaaaaaaaaaaaaaaaaaaaa");
		for (int i = 0; i < next.length; i++) {
			System.out.println("next[" + i + "] : " + next[i]);
		}
	}

	/*
	 * 获取字符串中的每个子段的前后缀重复数的最大长度, 
	 * 如 ABCABCD的后缀值的个数分别是 -1，0，0，1，2，3，0
	 * A		前缀 无		后缀 无 为 -1
	 * AB		前缀 ：A		后缀：B 前后缀重复数 0 
	 * ABC		前缀 ：A，AB 后缀：C，BC 前后缀重复数 0 
	 * ABCA	前缀 ：A ,AB,ABC		后缀：A,CA,BCA 前后缀最大重复数 1  A
	 * ABCAB	前缀 ：A,AB,ABC,ABCA		后缀：B,AB,CAB,BCAB 前后缀最大重复数 2  AB
	 * ABCABC 	前缀 ：A,AB,ABC,ABCA,ABCAB		后缀：C,BC,ABC,CABC,BCABC 前后缀最大重复数 3 ABC
	 * ABCABCD	前缀 ：A,AB,ABC,ABCA,ABCAB,ABCABC		后缀：D,CD,BCD,ABCD,CABCD,BCABCD 前后缀重复数 0
	 * 
	 * 思路:
	 * 某个字符串存在一种子段:
	 * abcdabcdabc
	 * 子段的前后缀重复部分abc长度为3，两个重复部分的相距间隔K == 8
	 * abc
	 *         abc
	 * abcdabc
	 * 子段的前后缀重复部分abcdabc长度为7，两个重复部分的相距间隔K == 4
	 *     abcdabc
	 * 	第一版思路:(先拆成一个个的子段后，再分别计算每个字段里面的前后缀重复数的最大长度吧)
	 * 		选择从后往前拆成各个子段,每个子段的长度由大到小，依次递减少
	 * 		对于长度为N，如 abcdabcdabc的字符串，需要遍历11次，可以分成11个子段，
	 * 		在遍历该子段时，如 abcdabcdabc时，
	 * 		1>想寻找该子段的重复部分，如果存在重复部分，那么子段的前缀每一个和后缀的每一个都是匹配的 如 前缀abc和后缀abc的每一个都匹配
	 * 		2>而且该子段中匹配的两部分，在子段abcdabcdabc中的位置 分别是 0，和 8，两部分相距间隔K==8
	 *		3>可以假设该子段中存在两个匹配的部分，并且该匹配的部分很长，因为匹配的部分越长，则相距间隔K就越小
	 *		4>假设匹配部分的最大长度为len - 1 , 则相距间隔K = 1，(如子段 AAAAAA,匹配部分最大长度为5(前面5个A和后面5个A)，(两个部分的首字母的索引)相距间隔K==1） 
	 *		5>如果匹配的话，该匹配部分的每个字符都相同，并且可以由K值来进行比较，ss[i] == ss[i+k] 来进行比较，
	 *			1》如果每个字符都相等的话，当前子段的最大重复匹配部分长度就是len - 1
	 *			2》如果有一个字符不想等，将K++;再比较每个字符ss[i] == ss[i+2]，如果每个字符都相等的话，当前字段的最大重复匹配部分长度就是len - 2，即len - k 
	 *			3>如果还不相等，就继续K++，直到K> len -1 ,此时表示子段的第一个单独字符a和最后一个单独字符c也没匹配上,就确定了该子段的最大重复部分的长度为
	 *				len - K == 0(没有重复的前后缀)
	 *		6>跳出循环，继续判断下个子段去
	 *		最坏情况下，总的循环次数为 1+2+3+4+.....+n = n(n+1)/2, 该思路的时间复杂度为 O(N^2)
	 *
	 * 第2版思路:abcdabcdabc
	 * 		第一版思路存在的问题，就是为N的字符串，外层循环为N，内层循环为 K-i，即总的循环次数 1+2+3+4+.....+n = n(n+1)/2
	 * 		优化的方式，从头往后循环，子段的长度不断递增，这样子段中两个重复部分的间距K的最大值也是在不断的增大(这里依旧选择每个子段的K值从1往大递增)
	 * 		从头开始循环,子段长度为len,k = 1,重复匹配部分的长度为len - k, 
	 * 		1>第一个子段只有首字符'a',该子段的len == 1, K ==1 , 重复匹配的最大部分为0,前后缀在这直接设为0
	 * 		2>取第2个子段为"ab"，该子段的len == 2, K ==1，假设重复匹配的最大部分为len - k == 1
	 * 			1>>通过ss[i] == ss[i+k]来判断该子段中的前后缀是否匹配
	 * 				1>匹配的话，就表示该子段的最大前后缀重复长度len - k == 1
	 * 				2>不匹配的话，将K++，假设重复匹配的最大部分为len - k == 0
	 * 		3>取第3个子段为"abc"，该子段的len == 3, K ==1，假设重复匹配的最大部分为len - k == 2
	 * 			1>>通过ss[i] == ss[i+k]来判断该子段中的前后缀是否匹配
	 * 				1>匹配的话，就表示该子段的最大前后缀重复长度len - k == 2
	 * 				2>不匹配的话，将K++，假设重复匹配的最大部分为len - k == 1,继续进行匹配,直到len - k == 0,然后就开始校验下个子段了
	 * 		.....
	 * 		11>取第11个子段为"abcdabcdabc"，该子段的len == 11, K ==1，假设重复匹配的最大部分为len - k ==10
	 *		最坏情况下，总的循环次数为 1+2+3+4+.....+n = n(n+1)/2, 该思路的时间复杂度为 O(N^2)
	 */
	public static int[] getSuffixValue2(String str) {
		
		if(str == null)
			return null;
		char [] ss = str.toCharArray();
		System.out.println("字符长度 : "+ss.length);
		int [] next = new int[ss.length];
		int num = 0;
		//循环取每个子段
		for(int i = 0 ; i < ss.length;i++)
		{
			int len = i+1;//表示当前子段的长度
			//循环取每个K值
			for(int k = 1; k < len ;k++)//每个子段中的前后缀间隔，一开始都设为从1开始
			{
				int len_match = len - k;//在当前K值下，可能存在的最长前后缀匹配长度
				boolean isAllMatch = true;
				//在当前的K值的基础上，判断重复部分是否每个值都能匹配上
				for(int j = 0 ; j < len_match ; j++)
				{
					num++;
					if(ss[j] != ss[j + k])//有一个字符不等于，就直接跳出去，
					{
						isAllMatch = false;
						break;
					}
				}
				if(isAllMatch)//如果能匹配上,当前子段的最大重复前后缀的相距间隔就是K,而该子段匹配的前后缀长度就是len_match
				{
					next[i] = len_match;
					break;//直接跳出去取下一个子段
				}//如果有一个字符没匹配上，就表示要增大K值,增大K值的操作，在循环里完成 
			}
		}
		System.out.println("num : "+num);
		return next;
	}
	
	/*
	 * 第三版思路:abcdabcdabc
	 * 	和第2版的思路相比较而言，优化的地方在于K值在每个子段里面都是从1，往字段的长度递增。
	 * 	在第一个子段'a'时，前后缀不存在，可匹配的前后缀长度肯定为0
	 * 	在第二个子段'ab'时，前后缀存在，可匹配的前后缀长度可能为1
	 * 	在第三个子段'abc'时，前后缀存在，可匹配的前后缀长度可能为2
	 * 	
	 * 	如果出现了这样的一种情况:在字符串aaaaaaaaaaaaaaaaaaabaaaaaaaaaaaaaaaa中
	 * 	第1个子段为'a'时,可匹配的前后缀长度肯定为0
	 * 	第2个子段为'aa'时,可匹配的前后缀长度为1
	 * 	第3个子段为'aaa'时,可匹配的前后缀长度2
	 * 	第4个子段为'aaaa'时,可匹配的前后缀长度3
	 * 	第5个子段为'aaaaa'时,可匹配的前后缀长度4
	 * 	
	 */
	public static int[] getSuffixValue3(String s) {
		
		return null;
	}

	/**
	 * 求出一个字符数组的next数组
	 * 
	 * @param t 字符数组
	 * @return next数组
	 */
	public static int[] getNextArray(char[] t) {
		int[] next = new int[t.length];
		next[0] = -1;
		next[1] = 0;
		int k;
		for (int j = 2; j < t.length; j++) {
			k = next[j - 1];
			while (k != -1) {
				if (t[j - 1] == t[k]) {
					next[j] = k + 1;
					break;
				} else {
					k = next[k];
				}
				next[j] = 0; // 当k==-1而跳出循环时，next[j] = 0，否则next[j]会在break之前被赋值
			}
		}
		return next;
	}

	/**
	 * 对主串s和模式串t进行KMP模式匹配
	 * 
	 * @param s 主串
	 * @param t 模式串
	 * @return 若匹配成功，返回t在s中的位置（第一个相同字符对应的位置），若匹配失败，返回-1
	 */
	public static int kmpMatch(String s, String t) {
		char[] s_arr = s.toCharArray();
		char[] t_arr = t.toCharArray();
		int[] next = getNextArray(t_arr);
		int i = 0, j = 0;
		while (i < s_arr.length && j < t_arr.length) {
			if (j == -1 || s_arr[i] == t_arr[j]) {
				i++;
				j++;
			} else
				j = next[j];
		}
		if (j == t_arr.length)
			return i - j;
		else
			return -1;
	}

	//aaaaaaaaaaaaaaaaaaabaaaaaaaaaaaaaaaa
	// aaaaaaaaaaaaaaaaaabaaaaaaaaaaaaaaaa
	static void getNext(int next[], String ss) {
		char[] t = ss.toCharArray();
		int j = 0, k = -1;
		next[0] = -1;
		while (j < t.length - 1) {
			if (k == -1 || t[j] == t[k]) {
				j++;
				k++;
				next[j] = k;
			} else
				k = next[k];// 此语句是这段代码最反人类的地方，如果你一下子就能看懂，那么请允许我称呼你一声大神！
		}
	}

}
