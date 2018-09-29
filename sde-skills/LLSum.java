import java.util.concurrent.ThreadLocalRandom;
import java.util.Random;
class LLSum{
	static LL sum(LL x, LL y){
		LL z = new LL(0), zPre = z, zCur = z;
		int xLen = len(x), yLen = len(y);
		if (xLen < yLen){
			{LL tmp = x; x = y; y = tmp;}
			{int tmp = xLen; xLen = yLen; yLen = tmp;}
		}
		LL xCur = x, yCur = y;
		while(xCur != null){
			int sum = xCur.val + (xLen > yLen ? 0 : yCur.val);
			zCur = zCur.next = new LL(sum%10);
			if (zCur.val != 9){
				zPre.val += sum/10;
				if (sum > 9){ while((zPre = zPre.next).val == 9){
					zPre.val = 0;
				}}
				zPre = zCur;
			}
			if (xLen == yLen){
				yCur = yCur.next;
				--yLen;
			}
			xCur = xCur.next;
			--xLen;
		}
		if (z.val == 0 && z.next != null){
			return z.next;
		}
		return z;
	}
	static int len(LL x){
		int len = 0;
		while(x != null){
			x = x.next;
			++len;
		}
		return len;
	}
	static class LL{
		int val;
		LL next;
		LL(int val){
			this.val = val;
		}
	}

	//TEST
	public static void main(String[]args){
		Random r = ThreadLocalRandom.current();
		while(true){
			int x, y;
			int z = sum(x = r.nextInt(100001), y = r.nextInt(100001));
			if (z != x+y){
				System.out.println(String.format("%d+%d!=%d", x,y,z));
				throw new RuntimeException("bad sum");
			}
		}
	}
	static int sum(int x, int y){
		return toInt(sum(toLL(x), toLL(y)));
	}
	static LL toLL(int x){
		LL xx = new LL(0);
		while(x != 0){
			xx.val = x%10;
			x/=10;
			LL tmp = new LL(0); tmp.next = xx; xx = tmp;
		}
		return xx.next != null ? xx.next : xx;
	}
	static int toInt(LL x){
		int xx = 0;
		while(x != null){
			xx += x.val;
			x = x.next;
			if (x != null){
				xx *= 10;
			}
		}
		return xx;
	}
}
