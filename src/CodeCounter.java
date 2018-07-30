import java.io.BufferedReader;
import java.io.File;
import java.io.FileFilter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class CodeCounter {
	public static void main(String[] args) {
		File file = new File(new File("").getAbsolutePath() + "\\");
		new CodeCounter().DFSForFS(file, countResults_File, countResults_String);
		printCountRes(countResults_File);
		printCountRes(countResults_String);
	}
	
	//对文件系统进行递归
	public void DFSForFS(File parentFile, CountResult[] cRByFile, CountResult[] cRByString) {
		if(parentFile.isDirectory()) {
			File[] file = parentFile.listFiles(new CodeCounterFileFilter());
			for(File f : file) {
				DFSForFS(f, cRByFile, cRByString);
			}
		}else {
			Counter c  = new Counter(parentFile, cRByFile, cRByString);
		}
	}
	
	public static void printCountRes(CountResult [] countResults) {
		for(CountResult c : countResults) {
			System.out.println(c.toString());
		}
	}
	
	//通过文件计算结果的CountResult数组
	public static CountResult[] countResults_File = {
		new CountResult("文件数量") {
			@Override
			public void addCountRes(File file) {
				// TODO Auto-generated method stub
				addRes(1);
			}
	
			@Override
			public void addCountRes(String str) {
				// TODO Auto-generated method stub
				
			}
		}
	};
	
	//通过字符串计算结果的CountResult数组
	public static CountResult[] countResults_String = {
		new CountResult("代码行数量") {

			@Override
			public void addCountRes(File file) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void addCountRes(String str) {
				addRes(1);
				
			}
			
		},
		new CountResult("字符数量") {

			@Override
			public void addCountRes(File file) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void addCountRes(String str) {
				// TODO Auto-generated method stub
				addRes(str.length());
			}
			
		}
	};
}

//统计器 统计代码量
class Counter{
	
	public Counter(File file, CountResult[] cRByFile, CountResult[] cRByString) {
		if(file.isFile()) {
			for(CountResult c : cRByFile) {
				c.addCountRes(file);
			}
			try {
				BufferedReader reader = new BufferedReader(new FileReader(file));
				String str = null;
				while((str = reader.readLine()) != null) {
					for(CountResult c : cRByString) {
						c.addCountRes(str);
					}
				}
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}

//统计结果类
abstract class CountResult{
	// 统计结果
	
	private String ResName = "";
	private int Res;
	
	public CountResult(String ResName) {
		this.ResName = ResName;
		Res = 0;
	}
	
	public void addRes(int res) {
		Res += res;
	}
	
	public String toString() {
		return ResName + ":" + Res;
	}
	
	//传入为文件类型
	abstract public void addCountRes(File file);
	  
	//传入为字符串类型
	abstract public void addCountRes(String str);
	 
}

//过滤器，识别指定文件
class CodeCounterFileFilter implements FileFilter{
	
	private static String[] beAcceptByName_suf = {
			".java"
	};
	
	@Override
	public boolean accept(File pathname) {
		if(!pathname.isDirectory()) {
			String fileName = pathname.getName();
			for(String str : beAcceptByName_suf) {
				if(fileName.endsWith(str)) return true;
			}
			return false;
		}
		return true;
	}
}