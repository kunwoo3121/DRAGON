# DRAGON

https://algospot.com/judge/problem/read/DRAGON

# 구현 방법
```
 i)   KLIS 문제와 비슷하게 P번째 문자부터 출력해야하므로 그 전까지의 문자는 무시하며 탐색의 수를 줄여나간다.
 
 ii)  P번째 문자부터 L개의 문자를 출력해야하므로 무시해야하는 문자의 수를 P-1 ~ P+L-2 까지 진행하며 문자를 한개씩 출력한다.
 
 iii) 무시해야하는 문자의 수를 skip이라고 두고 이 skip을 줄여나가는 방법은 아래와 같다.
      
      1) X나 Y가 아닐 경우 => 늘어날 수 없으므로 1 감소
      2) X나 Y일 경우
      
         X/Y의 경우 늘어나는 문자의 길이가 정해져 있다.
         
         X -> X+YF -> X+YF+FX-YF -> X+YF+FX-YF+FX-YF-FX-YF ......
         1     4         10               22               ......
         
         n세대에서의 길이는 n-1세대의 길이 * 2 + 2 의 관계를 가지고 있다는 것을 알 수 있고 이것은 Y일때도 동일하다. 
         이것을 통해 X나 Y가 n세대만큼 지났을 때의 길이를 미리 알 수 있다. 
         
         X나 Y를 만났을 경우 n세대에서의 길이와 skip의 값을 비교하여 skip의 값이 더 크거나 같은 경우 skip의 값을 감소시키면 된다.
         그렇지 않을 경우 무시하면 안되는 탐색이므로 X나 Y를 한 세대 진화시킨 후 탐색을 진행한다.
         
 iv)  탐색을 진행하다 입력된 세대만큼 진화가 다 된 경우에는 현재 문자열에서 남은 skip의 값만큼 문자를 넘기고 다음 문자를 출력하면 된다.
      진화가 다 되었다는 것은 무조건 현재 문자열 안에 원하는 문자가 있다는 의미이기 때문이다.
     
 v)   그 외 남은 skip값이 0일 경우 문자열의 현재 위치를 출력하면 된다.
 
 vi)  0세대일 때 문자열이 FX이므로 이 값을 초기 문자열로 주고 위의 과정을 L번 반복하게 되면 원하는 문자열이 출력되게 된다.
 ```
 
 # 구현 코드
 ```java
 import java.util.Scanner;

public class DRAGON {
	
	static int MAX = 1000000000 + 1;
	static int[] length = new int[51];
	static String Expand_X = "X+YF";
	static String Expand_Y = "FX-Y";
	
	public static void main(String args[])
	{
		Scanner sc = new Scanner(System.in);
		
		int c = sc.nextInt();
		int p;
		int l;
		int n;
		
		
		precalc();
		
		for(int i = 0; i < c; i++)
		{
			n = sc.nextInt();
			p = sc.nextInt();
			l = sc.nextInt();
			
			for(int j = 0; j < l; j++)
			{
				System.out.print(expand("FX", n, p + j - 1));
			}
			System.out.println();
		}
		
	}
	
	public static void precalc()
	{
		length[0] = 1;
		
		for(int i = 1; i < 51; i++)
		{
			length[i] = Math.min(MAX, length[i - 1] * 2 + 2);
		}
	}
	
	public static char expand(String dragonCurve, int gen, int skip)
	{
		if(gen == 0)
		{
			return dragonCurve.charAt(skip);
		}
		
		for(int i = 0; i < dragonCurve.length(); i++)
		{
			if(dragonCurve.charAt(i) == 'X' || dragonCurve.charAt(i) == 'Y')
			{
				if(skip >= length[gen])
					skip -= length[gen];
				else if(dragonCurve.charAt(i) == 'X')
					return expand(Expand_X, gen - 1, skip);
				else
					return expand(Expand_Y, gen - 1, skip);
			}
			
			else if( skip > 0 )
				skip--;
			
			else
				return dragonCurve.charAt(i);
		}
		
		return '@';
	}
}
```
