package bean3;

import java.util.Random;

/**
 * 需求: 模拟kugoo的工作原理  主线程在播放音乐库灵里面的个 新线程在下载音乐 当音乐下载后  要打断原有的播放  优先播放新下载的音乐
 * @author fangxiang
 *
 */
public class Kugou {

	public static void main(String[] args) throws InterruptedException {
		Random r=new Random();
		
		DownLoadFinish dlf=new DownLoadFinish();
		
		DownLoad dl=new DownLoad("喜洋洋美羊羊",300,dlf);
		dl.start();
		
		for(int i=0;i<100;i++) {
			//有没有在同步下载,如果有  看是否下完了
			if(dlf!=null&&dlf.getFinish()) {
				System.out.println("播放新下的音乐:"+dlf.getMusic());;
				dlf=null;//下载成功 设置通知为null
				i--;
			}else {
				System.out.println("音乐播放"+i+".......");
			}
			Thread.sleep(r.nextInt(20));
		}
	}
}


//通知接口
interface NotifyFinsh{//通知接口
	//回调
	public void isFinish(boolean finish,String music,int length);
}



class DownLoadFinish implements NotifyFinsh{
	private boolean finish;//false
	private String music;//null
	private int length;//null
	
	//通知
	public void isFinish(boolean finish, String music, int length) {
		this.finish = finish;
		this.music = music;
		this.length = length;
	}

	public boolean getFinish() {
		return finish;
	}

	public void setFinish(boolean finish) {
		this.finish = finish;
	}

	public String getMusic() {
		return music;
	}

	public void setMusic(String music) {
		this.music = music;
	}

	public int getLength() {
		return length;
	}

	public void setLength(int length) {
		this.length = length;
	}

}

class DownLoad extends Thread{
	private String music;
	private int length;
	private boolean flag=true;
	private int total;
	private NotifyFinsh nptifyFinsh;
	
	
	public DownLoad(String music, int length, NotifyFinsh nptifyFinsh) {
		super();
		this.music = music;
		this.length = length;
		this.nptifyFinsh = nptifyFinsh;
	}

	public void run() {
		//完成下载
		while(flag) {
			total+=10;
			try {
				Thread.sleep(10);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			if(total>=length) {//判断是否下载完成
				System.out.println("下载"+this.music+"完成");
				flag=false;
				
				//通知
				this.nptifyFinsh.isFinish(true, this.music, length);
			}
		}
		
	}
}
