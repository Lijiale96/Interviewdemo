

//public class Solution {
//    public static int JumpFloorII(int target) {
//        int [] f = new int[target+2];
//        f[0]=1;f[1]=1;f[2]=2;
//        int temp=0;
//        if(target==1){return 1;}
//        if(target==2){return 2;}
//        if(target>=3){
//            for(int i=3;i<=target;i++){
//                for(int j=1;j<=i;j++){
//                    temp+=f[i-j];
//                }
//            }
//        }
//        return temp;
//    }


import java.util.List;

public class Solution {
//    public static int NumberOf1(int n) {
//        if (n == 0) {
//            return 0;
//        }
//        java.util.ArrayList<Integer> list = new java.util.ArrayList<>();
//        int len =0;
//        if (n > 0) {
//            int step = (n + 1) / 2;
//            for (int i = 0; i < step; i++) {
//                n /= 2;
//                list.add(i, n % 2);
//            }
//            len = list.size();
//        }
//        if (n < 0) {
//            int m = Math.abs(n);
//            int step = (m + 1) / 2;
//            for (int i = 0; i < step; i++) {
//                m /= 2;
//                list.add(i, m % 2);
//            }
//             len =2 + list.size();
//        }
//        return len;
//    }

    public static double Power(double base, int exponent) {
        double res=base;
        if(exponent>0){
            for(int i=1;i<exponent;i++){
                res*=base;
            }
        }
        if(exponent<0){
            for(int i=1;i<Math.abs(exponent);i++){
                res*=base;
                res=1/res;
            }
        }
        return res;
    }
public static int[] reOrderArray(int[] array) {
    int len =array.length;
    int left=0,right=len;
    int mid = (left+right)/2;

    int [] arr= new int[len+1];
    for(int i=0;i<len;i++){
        if(array[i]%2==1){
            arr[left]=array[i];
            left++;
        }else{
            arr[mid+1]=array[i];
            mid++;
        }
    }
    System.arraycopy(arr,0,array,0,len);
    return array;
}

    public static void main(String[] args) {
//

    int [] array ={1,2,3,4,5,6,7,8};
        System.out.println(reOrderArray(array));
//        System.out.println(Power(3, 3));
    }
}