public class sqrt {
    static double findsqrt(int val,int dp) {
         int half = val / 2;

         while(half * half > val){
            half = half / 2;
         }


         while((half + 1) * (half + 1) <= val){
            half += 1;
         }

         double sqrt = half;
         double d = 0.1;
         sqrt += d;
        
         while(dp > 0){
            while(sqrt * sqrt < (double)val){
                sqrt += d;
                
            }
            sqrt -= d;
            d = d / 10;
            dp -= 1;
            
         }
         return sqrt;

    }

    public static void main(String[] args) {
        int dec = 5;
        double ans = findsqrt(27, dec);
        String str = "%." + dec + "f";
        System.out.println(str);
        System.out.printf(str, ans);
    }
}
