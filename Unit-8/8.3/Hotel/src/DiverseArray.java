
public class DiverseArray {

	
	public static int arraySum(int[] arr){
	       int sum=0;
	       for (int elem : arr){
	         sum += elem;
	       }  
	       return sum; 
	}
	
	public static int[] rowSums(int[][] arr2D){
        int [] sums=new int[arr2D.length];
        int rowNum=0;
        for(int[] row : arr2D){
            sums[rowNum]=arraySum(row);
            rowNum++;
        }
        return sums;
    }
	
	public static boolean isDiverse(int[][] arr2D){
        int [] sums=rowSums(arr2D);
        for (int i=0; i < sums.length; i++){
            for (int j=i+1; j < sums.length; j++){
                if (sums[i]==sums[j]){
                		return false;
                }
            }
        }  
        return true;
    }
}
