import java.io.File;
import java.io.FileFilter;
import java.util.ArrayList;

public class ProjectTree {
	
	public static void main(String[] args){
		File file = new File(new File("").getAbsolutePath() + "\\");
		new ProjectTree().printTree(0, file, file.listFiles(new MyFileFilter()).length, 0, "");
	}
	
	public void printTree(int index, File file, int childrenSize, int number, String pre){
		if(index != 0){
			System.out.print(pre);
			if(number == childrenSize - 1){
				System.out.print("┗━");
				pre += "　　";
			}else{
				System.out.print("┣━");
				pre += "┃　";
			}
		}
		System.out.println(file.getName());
		if(file.isDirectory()){
			int i = 0;
			File[] files = file.listFiles(new MyFileFilter());
			int file_length = files.length;
			
			for(File f : files)
				printTree(index + 1, f, file_length, i++, pre);
		}
	}
}
class MyFileFilter implements FileFilter{

	//By File Name In prefix
	private static String [] beAcceptFileName_pre = {

	};
	
	//By File Name In suffix
	private static String [] beAcceptFileName_suf = {
			
	};
	
	//By Path Name
	private static String [] beAcceptPathName = {
		"git"
	};
	
	@Override
	public boolean accept(File pathname) {
		// TODO Auto-generated method stub
		if(pathname.isDirectory()) {
			String fileName = pathname.getName();
			String pathName = pathname.getAbsolutePath();
			
			for(String str : beAcceptFileName_pre) {
				if(fileName.startsWith(str)) {
					return false;
				}
			}
			
			for(String str : beAcceptFileName_suf) {
				if(fileName.endsWith(str)) {
					return false;
				}
			}
			
			for(String str : beAcceptPathName) {
				if(pathName.contains(str)) {
					return false;
				}
			}
		}
		return true;
	}
}