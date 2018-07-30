import java.io.File;
import java.util.ArrayList;

public class ProjectTree {
	
	public static void main(String[] args){
		File file = new File(new File("").getAbsolutePath() + "\\");
		new ProjectTree().printTree(0, file, file.listFiles().length, 0, "");
	}
	
	public void printTree(int index, File file, int childrenSize, int number, String pre){
		if(index != 0){
			System.out.print(pre);
			if(number == childrenSize - 1){
				System.out.print("©»©¥");
				pre += "¡¡¡¡";
			}else{
				System.out.print("©Ç©¥");
				pre += "©§¡¡";
			}
		}
		System.out.println(file.getName());
		if(file.isDirectory()){
			int i = 0;
			File[] files = file.listFiles();
			int file_length = files.length;
			
			for(File f : files)
				printTree(index + 1, f, file_length, i++, pre);
		}
	}
}