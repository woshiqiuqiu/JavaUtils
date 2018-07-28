import java.io.File;
import java.util.ArrayList;

public class ProjectTree {
	
	public static void main(String[] args){
		File file = new File(new File("").getAbsolutePath() + "\\");
		TreeNode allTree = new ProjectTree().DFSForFileSystem(file);
		new ProjectTree().printTree(0, allTree, allTree.getChildNodesSize(), 0, "");
	}
	
	public void printTree(int index, TreeNode res, int childrenSize, int number, String pre){
		if(index == 0){
			System.out.println(res.getNodeName());
		}else{
			System.out.print(pre);
			if(number == childrenSize - 1){
				System.out.println("©»©¥" + res.getNodeName());
				pre += "¡¡¡¡";
			}else{
				System.out.println("©Ç©¥" + res.getNodeName());
				pre += "©§¡¡";
			}
			
		}
		ArrayList<TreeNode> childNodes = res.getArrayList();
		if(childNodes != null){
			int i = 0;
			for(TreeNode tn : childNodes){
				printTree(index + 1, tn, res.getChildNodesSize(), i, pre);
				i++;
			}
		}
	}

	
	public TreeNode DFSForFileSystem(File file){
		TreeNode res = new TreeNode(file);
		
		if(file.isDirectory()){
			File[] children = file.listFiles();
			for(File f : children){
				res.addChildNode(DFSForFileSystem(f));
			}
		}
		
		return res;
	}
}
class TreeNode{
	private File file;
	private String nodeName;
	private ArrayList<TreeNode> childNodes;
	
	public TreeNode(File root){
		file = root;
		if(file.isDirectory()){
			childNodes = new ArrayList<TreeNode>();
		}else{
			childNodes = null;
		}
		nodeName = file.getName();
	}
	
	public boolean childNodesIsNull(){
		return childNodes == null;
	}
	
	public void addChildNode(TreeNode childNode){
		childNodes.add(childNode);
	}
	
	public String getNodeName(){
		return nodeName;
	}
	
	public ArrayList<TreeNode> getArrayList(){
		return childNodes;
	}
	
	public int getChildNodesSize(){
		return childNodes.size();
	}
}