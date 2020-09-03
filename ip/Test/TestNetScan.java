package Test;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import Test.Ping.IpInfo;


//扫描了ip,   可以下面用 telnet来扫描端口   记录   ->     再拼协议  
public class TestNetScan {
	public static void main(String[] args) {
		System.out.println("网络中上线的机器如下：");
		System.out.println("地址\t\t\tIP\t\t\t链接时间\t\t丢包率\t\t网速");

		NetScan ns1 = new NetScan("192.168.76", 0, 10, new NofityNetScan() {
			@Override
			public void notify(IpInfo ipInfo) {
				System.out.println(ipInfo.hostname + "\t\t" + ipInfo.ip
						+ "\t\t" + ipInfo.linkTime + "ms" + "\t\t"
						+ ipInfo.lost + "%" + "\t\t" + ipInfo.speed);				
				//TODO: 记录到数据库
			}
		});
		ns1.start();
		
		NetScan ns = new NetScan("www.baidu.com", new NofityNetScan() {
			@Override
			public void notify(IpInfo ipInfo) {
				System.out.println(ipInfo.hostname + "\t\t" + ipInfo.ip
						+ "\t\t" + ipInfo.linkTime + "ms" + "\t\t"
						+ ipInfo.lost + "%" + "\t\t" + ipInfo.speed);
			}
		});
		ns.start();
		
		
		//TODO: 用telnet  联接端口.
		
	}
}

interface NofityNetScan {
	public void notify(IpInfo ipInfo);
}

class NetScan extends Thread {
	private String ipPrefix; // ip地址前三位
	private int start; // ip地址后一位的起始值
	private int end; // ip地址后一位的终止值
	private String hostname; // 网址
	private NofityNetScan nofityNetScan;

	// 构造函数 用于对IP进行扫描
	public NetScan(String ipPrefix, int start, int end,
			NofityNetScan nofityNetScan) {
		this.ipPrefix = ipPrefix;
		this.start = start;
		this.end = end;
		this.nofityNetScan = nofityNetScan;
	}

	// 重载构造函数 对网址重载
	public NetScan(String hostname, NofityNetScan nofityNetScan) {
		this.hostname = hostname;
		this.nofityNetScan = nofityNetScan;
		this.start = -1;
	}

	public void run() {
		if (this.start != -1) {   //不是 -1   是根据ip 发起ping
			for (int i = start; i < end; i++) {
				String ip = ipPrefix + "." + i;
				Ping p = new Ping();
				try {
					String result = p.getPingResult(ip);   //  ping完后的字符串，有成功的，也有失败，所以要字符串解析
					IpInfo ipInfo = p.isOnLine(ip, result);   //解析是否在线
					if (this.nofityNetScan != null && ipInfo.isOnLine) {
						this.nofityNetScan.notify(ipInfo);
					}
				} catch (IOException e) {
					System.out.println("网址中地址为" + ip + "的机器链接有问题：");
					e.printStackTrace();
				}

			}
		} else {
			try {
				Ping p = new Ping();
				String result = p.getPingResult(hostname);
				IpInfo ipInfo = p.isOnLine(hostname, result);
				if (this.nofityNetScan != null && ipInfo.isOnLine) {
					this.nofityNetScan.notify(ipInfo);
				}
			} catch (IOException e) {
				System.out.println("网址中地址为" + hostname + "的机器链接有问题：");
				e.printStackTrace();
			}
		}
	}
}

class Ping {
	// 获取ping返回的数据
	public String getPingResult(String ip) throws IOException {
		// 执行dos命令, 只针对windows   
		Process p = Runtime.getRuntime().exec("ping  " + ip);
		// 获得输入流
		InputStream iis = p.getInputStream();
		InputStreamReader ir = new InputStreamReader(iis,
				Charset.forName("GBK")); // 解决中文乱码的问题
		char[] bs = new char[1024];
		int length = -1;
		StringBuffer sb = new StringBuffer();
		while ((length = ir.read(bs, 0, bs.length)) != -1) {
			sb.append(new String(bs, 0, length));
		}
		ir.close();
		iis.close();
		return sb.toString();
	}

	public IpInfo isOnLine(String ip, String result) {
		IpInfo ipinfo = new IpInfo();
		
		//TODO:   System.getProperty(""
		
		if (result.indexOf("无法访问目标主机") != -1 || result.indexOf("请求找不到主机") != -1
				|| result.indexOf("请求超时") != -1) {
			ipinfo.ip = ip;
			ipinfo.isOnLine = false;
			ipinfo.linkTime = Integer.MAX_VALUE;
		} else {
			ipinfo.ip = ip;
			ipinfo.hostname = ip; // 设置ip和主机名一致
			ipinfo.isOnLine = true;
			// 如果是网址
			if (result.contains("[")) {
				// 重置ip名
				if (matchePattern("\\[(.*)\\]", result.toString()) != null) {   //   [192.168.1.1]
					ipinfo.ip = matchePattern("\\[(.*)\\]", result.toString());
				}
			}
			// 计算连接平均耗时
			int ave = 0;
			if (matchePattern("平均 = (.*)ms", result.toString()) != null) {
				ave = Integer.parseInt(matchePattern("平均 = (.*)ms",
						result.toString()));
			}
			ipinfo.linkTime = ave;
			// 匹配丢包率
			int lost = 0;
			if (matchePattern("\\((.*)\\% 丢失", result.toString()) != null) {
				lost = Integer.parseInt(matchePattern("\\((.*)\\% 丢失",
						result.toString()));
			}
			ipinfo.lost = lost;
			// 链接速度
			Pattern pattern = Pattern.compile("时间=(.*)m");
			Matcher matcher = pattern.matcher(result.toString());
			int total = 0;
			while (matcher.find()) {
				total += Integer.parseInt(matcher.group(1));
			}
			//TODO:
			double speed = (32 * 8 * 1000) / (total * 1024.0);
			ipinfo.speed = speed;
		}
		return ipinfo;
	}

	// 抽取匹配规则
	private String matchePattern(String pattern, String matcher) {
		Pattern mPattern = Pattern.compile(pattern);
		Matcher mMatcher = mPattern.matcher(matcher);
		String result = null;
		while (mMatcher.find()) {
			result = mMatcher.group(1);
		}
		return result;
	}

	public class IpInfo {
		public String ip; // ip地址
		public boolean isOnLine; // 是否在线
		public int linkTime; // 连接时间
		public String hostname; // 主机名
		public double lost; // 丢包百分比
		public double speed; // 连接速度
	}
}