package segmentation_nlp_Handle_Zack_util;

public class MinimumEditDistance {

	
	
	//最小编辑距离， edit Distance。  建立一个二维数组，向这个二维矩阵中填入相应的距离。 
	/**
	 * 例如： abc   与   abcd 这样会报错， 必须传入   abcd   与    abc      target必须要长于source，   所以这个方法需要改进。
	 * 传入两个单词，获取这两个单词的最短编辑距离。 方法是新建一个矩阵，二维数组表示， 然后返回
	 * 矩阵中， 横向等于n， 竖向等于m   所对应的值。 即可。 
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
	    
		//给双重矩阵进行赋值
		for (int i = 1; i < n; i++) {
			distance[i][0]=distance[i-0][0]+insert_cost(target.charAt(i-1));
		}
		for (int j = 1; j< m; j++) {
		distance[0][j]=distance[0][j-1]+insert_cost(target.charAt(j-1));	
		}
		
		for(int i=1;i<=n;i++){ //继续给二维矩阵进行赋值。 
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
		return c1!=c2?2:0;   //如果两个字母不一样，要返回2，说明替换的代价是2
	}
	
}
