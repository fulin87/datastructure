package ren.colorful.training.sparsearray;

/**
 * 稀疏数组是一种时间换空间的思路，用于对原有数据进行压缩 1，对于一个大数组有很多值都是一样的，只有少部分数据是不同的，则可以考虑用稀疏数组来表示
 * <pre>
	原始的二维数组：
	0 0 0 0 0 0 0 0 0 0 0 
	0 0 1 0 0 0 0 0 0 0 0 
	0 0 0 2 0 0 0 0 0 0 0 
	0 0 0 0 0 0 0 0 0 0 0 
	0 0 2 0 0 0 0 0 0 0 0 
	0 0 0 0 0 0 0 0 0 0 0 
	0 0 0 0 0 0 0 0 0 0 0 
	0 0 0 0 0 0 0 0 0 0 0 
	0 0 0 0 0 0 0 0 0 0 0 
	0 0 0 0 0 0 0 0 0 0 0 
	0 0 0 0 0 0 0 0 0 0 0 
	
	转换后的稀疏数组：
	11	11	3	
	1	2	1	
	2	3	2	
	4	2	2	
	 
	还原之后的稀疏数组
	0 0 0 0 0 0 0 0 0 0 0 
	0 0 1 0 0 0 0 0 0 0 0 
	0 0 0 2 0 0 0 0 0 0 0 
	0 0 0 0 0 0 0 0 0 0 0 
	0 0 2 0 0 0 0 0 0 0 0 
	0 0 0 0 0 0 0 0 0 0 0 
	0 0 0 0 0 0 0 0 0 0 0 
	0 0 0 0 0 0 0 0 0 0 0 
	0 0 0 0 0 0 0 0 0 0 0 
	0 0 0 0 0 0 0 0 0 0 0 
	0 0 0 0 0 0 0 0 0 0 0 
 * </pre>
 * 
 * 
 * @author fulin
 *
 */
public class SparseArray {
	public static void main(String[] args) {
		//创建一个原始的二维数组 11 * 11
		//0 表示没有棋子，1表示黑子，2表示蓝子
		int chessArr1[][] = new int[11][11];
		
		chessArr1[1][2] = 1;
		chessArr1[2][3] = 2;
		chessArr1[4][2] = 2;
		//输出原始的二维数组
		System.out.println("原始的二维数组：");
		for(int[] row : chessArr1) {
			for(int col : row) {
				System.out.print(col + " ");
			}
			System.out.println();
		}
		
		//将二维数组转换为稀疏数组
		//遍历原始数组，得到非0数据的个数
		int num = 0;
		for(int i = 0; i< 11;i++) {
			for(int j = 0;j < 11;j++) {
				if(chessArr1[i][j] != 0) {
					num ++;
				}
			}
		}
		
		//创建对应的稀疏数组
		int sparseArr[][] = new int[num + 1][3];
		sparseArr[0][0] = 11;
		sparseArr[0][1] = 11;
		sparseArr[0][2] = num;
		
		//遍历原始数组,将非0的值存放在稀疏数组中
		int col = 1;
		for(int i = 0; i< 11;i++) {
			for(int j = 0;j < 11;j++) {
				if(chessArr1[i][j] != 0) {
					sparseArr[col][0] = i;
					sparseArr[col][1] = j;
					sparseArr[col][2] = chessArr1[i][j];
					col ++;
				}
			}
		}
		
		//输出稀疏数组
		System.out.println("转换后的稀疏数组：");
		for(int[] row : sparseArr) {
			for(int data : row) {
				System.out.print(data + "\t");
			}
			System.out.println();
		}
		
		//将稀疏数组恢复成原始数组
		int[][] arr= new int[sparseArr[0][0]][sparseArr[0][1]];
		int line = 0;
		for(int[] row : sparseArr) {
			if(line == 0) {
				line ++;
				continue;
			}
			arr[row[0]][row[1]] = row[2];
		}
		
		System.out.println("还原之后的稀疏数组");
		for(int[] row : arr) {
			for(int i : row) {
				System.out.print(i + " ");
			}
			System.out.println();
		}
	}
}
