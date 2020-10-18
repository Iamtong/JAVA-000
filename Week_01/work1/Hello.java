/**
 * week01 1
 */
public class Hello{
    public static void main(String[] args){
        int total1 = 0;
        int total2 = 1;
        for (int i=1;i<10;i++){
            if (i%2==0){
                total1 += i;
            }else {
                total1 -= i;
            }
        }
        System.out.println("total="+total1);
        for (int i=1;i<10;i++){
            if (i%3==0){
                total2 /= 2;
            }else{
                total2 *= 2;
            }
        }
        System.out.println("total="+total2);
    }
}