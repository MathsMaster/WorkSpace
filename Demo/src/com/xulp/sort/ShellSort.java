package com.xulp.sort;

//ϣ�������ԭ��:��һ��������n�Σ�ÿ��m��Ԫ�أ�ȡÿһ�εĵ�һ��Ԫ�ؽ��в�����������ȡÿ�εĵڶ���Ԫ�ؽ������򡣡���������
//			����������������2n�Σ�ÿ��2m��Ԫ�أ�����ȡÿ�εĵ�һ��Ԫ�ؽ�����������ȡ�ڶ���Ԫ�ؽ������򡣡�����
//			......
//			����������飬ֱ��ÿ��ֻ��һ��Ԫ�أ����в�������
public class ShellSort {

	public static int [] shellSort(int [] array)
	{
		int n = 3;//��ʾ��Ϊ���ٶ�
		int m = array.length/n;//��ʾÿ��Ԫ�ص����������һ�ο���ֻ�в���m��Ԫ��
		while(m >= 1)
		{
			for(int j = 0 ; j < m ; j++)
			{
				for(int k = j; k < array.length;k += m)
				{
					for(int i = k ; i >= m && array[i] < array[i - m] ; i -= m)
					{
						//��ȡ����ֵ�����������λ��
						exchange(array, i, i - m);
					}
				}
				
			}
			n = 2*n;
			m = array.length/n;
		}
		return array;
	}
	
	static void exchange(int []array,int i,int j)
	{
		int temp = array[i];
		array[i] = array[j];
		array[j] = temp;
	}
}
