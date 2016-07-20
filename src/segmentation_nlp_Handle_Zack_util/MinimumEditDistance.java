package segmentation_nlp_Handle_Zack_util;

public class MinimumEditDistance {

	
	
	//��С�༭���룬 edit Distance��  ����һ����ά���飬�������ά������������Ӧ�ľ��롣 
	/**
	 * ���磺 abc   ��   abcd �����ᱨ�� ���봫��   abcd   ��    abc      target����Ҫ����source��   �������������Ҫ�Ľ���
	 * �����������ʣ���ȡ���������ʵ���̱༭���롣 �������½�һ�����󣬶�ά�����ʾ�� Ȼ�󷵻�
	 * �����У� �������n�� �������m   ����Ӧ��ֵ�� ���ɡ� 
	 * @param target
	 * @param source
	 * @return
	 */
	public static int getMinimumEditDistance(String target, String source){
		int result=0;
		int n=target.length();
		int m=source.length();
		int [][]distance=new int[n+1][m+1];
		distance[0][0]=0;
	    
		//��˫�ؾ�����и�ֵ
		for (int i = 1; i < n; i++) {
			distance[i][0]=distance[i-0][0]+insert_cost(target.charAt(i-1));
		}
		for (int j = 1; j< m; j++) {
		distance[0][j]=distance[0][j-1]+insert_cost(target.charAt(j-1));	
		}
		
		for(int i=1;i<=n;i++){ //��������ά������и�ֵ�� 
			for(int j=1;j<=m;j++){
				int ins = distance[i-1][j]+insert_cost(target.charAt(i-1));
				int sub = distance[i-1][j-1] + subs_cost(target.charAt(i-1), source.charAt(j-1));
				int del = distance[i][j-1] + delete_cost(source.charAt(j-1));
				distance[i][j] = min(ins,min(sub,del));
			}
		}
		result = distance[n][m];
		
		return result;
	}
	
	private static int min(int d1,int d2){
		return d1<d2?d1:d2;
	}
	
	private static int insert_cost(char c){
		return 1;
	}
	
	private static int delete_cost(char c){
		return 1;
	}
	
	private static int subs_cost(char c1,char c2){
		return c1!=c2?2:0;   //���������ĸ��һ����Ҫ����2��˵���滻�Ĵ�����2
	}
	
}
