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
