package Test;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import Test.Ping.IpInfo;


//ɨ����ip,   ���������� telnet��ɨ��˿�   ��¼   ->     ��ƴЭ��  
public class TestNetScan {
	public static void main(String[] args) {
		System.out.println("���������ߵĻ������£�");
		System.out.println("��ַ\t\t\tIP\t\t\t����ʱ��\t\t������\t\t����");

		NetScan ns1 = new NetScan("192.168.76", 0, 10, new NofityNetScan() {
			@Override
			public void notify(IpInfo ipInfo) {
				System.out.println(ipInfo.hostname + "\t\t" + ipInfo.ip
						+ "\t\t" + ipInfo.linkTime + "ms" + "\t\t"
						+ ipInfo.lost + "%" + "\t\t" + ipInfo.speed);				
				//TODO: ��¼�����ݿ�
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
		
		
		//TODO: ��telnet  ���Ӷ˿�.
		
	}
}

interface NofityNetScan {
	public void notify(IpInfo ipInfo);
}

class NetScan extends Thread {
	private String ipPrefix; // ip��ַǰ��λ
	private int start; // ip��ַ��һλ����ʼֵ
	private int end; // ip��ַ��һλ����ֵֹ
	private String hostname; // ��ַ
	private NofityNetScan nofityNetScan;

	// ���캯�� ���ڶ�IP����ɨ��
	public NetScan(String ipPrefix, int start, int end,
			NofityNetScan nofityNetScan) {
		this.ipPrefix = ipPrefix;
		this.start = start;
		this.end = end;
		this.nofityNetScan = nofityNetScan;
	}

	// ���ع��캯�� ����ַ����
	public NetScan(String hostname, NofityNetScan nofityNetScan) {
		this.hostname = hostname;
		this.nofityNetScan = nofityNetScan;
		this.start = -1;
	}

	public void run() {
		if (this.start != -1) {   //���� -1   �Ǹ���ip ����ping
			for (int i = start; i < end; i++) {
				String ip = ipPrefix + "." + i;
				Ping p = new Ping();
				try {
					String result = p.getPingResult(ip);   //  ping�����ַ������гɹ��ģ�Ҳ��ʧ�ܣ�����Ҫ�ַ�������
					IpInfo ipInfo = p.isOnLine(ip, result);   //�����Ƿ�����
					if (this.nofityNetScan != null && ipInfo.isOnLine) {
						this.nofityNetScan.notify(ipInfo);
					}
				} catch (IOException e) {
					System.out.println("��ַ�е�ַΪ" + ip + "�Ļ������������⣺");
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
				System.out.println("��ַ�е�ַΪ" + hostname + "�Ļ������������⣺");
				e.printStackTrace();
			}
		}
	}
}

class Ping {
	// ��ȡping���ص�����
	public String getPingResult(String ip) throws IOException {
		// ִ��dos����, ֻ���windows   
		Process p = Runtime.getRuntime().exec("ping  " + ip);
		// ���������
		InputStream iis = p.getInputStream();
		InputStreamReader ir = new InputStreamReader(iis,
				Charset.forName("GBK")); // ����������������
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
		
		if (result.indexOf("�޷�����Ŀ������") != -1 || result.indexOf("�����Ҳ�������") != -1
				|| result.indexOf("����ʱ") != -1) {
			ipinfo.ip = ip;
			ipinfo.isOnLine = false;
			ipinfo.linkTime = Integer.MAX_VALUE;
		} else {
			ipinfo.ip = ip;
			ipinfo.hostname = ip; // ����ip��������һ��
			ipinfo.isOnLine = true;
			// �������ַ
			if (result.contains("[")) {
				// ����ip��
				if (matchePattern("\\[(.*)\\]", result.toString()) != null) {   //   [192.168.1.1]
					ipinfo.ip = matchePattern("\\[(.*)\\]", result.toString());
				}
			}
			// ��������ƽ����ʱ
			int ave = 0;
			if (matchePattern("ƽ�� = (.*)ms", result.toString()) != null) {
				ave = Integer.parseInt(matchePattern("ƽ�� = (.*)ms",
						result.toString()));
			}
			ipinfo.linkTime = ave;
			// ƥ�䶪����
			int lost = 0;
			if (matchePattern("\\((.*)\\% ��ʧ", result.toString()) != null) {
				lost = Integer.parseInt(matchePattern("\\((.*)\\% ��ʧ",
						result.toString()));
			}
			ipinfo.lost = lost;
			// �����ٶ�
			Pattern pattern = Pattern.compile("ʱ��=(.*)m");
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

	// ��ȡƥ�����
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
		public String ip; // ip��ַ
		public boolean isOnLine; // �Ƿ�����
		public int linkTime; // ����ʱ��
		public String hostname; // ������
		public double lost; // �����ٷֱ�
		public double speed; // �����ٶ�
	}
}