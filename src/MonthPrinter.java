import java.util.Scanner;

public class MonthPrinter {

	public static void main(String [] args) {
		int year, month;
		
		Scanner scanner = new Scanner(System.in);
		System.out.println("Please input year:");
		year = scanner.nextInt();
		System.out.println("Please input Month:");
		month = scanner.nextInt();
		
		printTable(beforDay(year, month), getMonthDay(month, isDoubleYear(year)));
	}
	
//	获取在日期之前的天数
	private static int beforDay(int year, int month) {
		int i, resDay;
		boolean doubleYearFlag = isDoubleYear(year);
		resDay = 0;
		for(i = 1900; i < year; i++) {
			resDay += 365;
			if(isDoubleYear(i))	resDay++;
		}
		for(i = 1; i < month; i++) resDay += getMonthDay(i, doubleYearFlag);
		return resDay;
	}
	
	//判断是否为闰年
	private static boolean isDoubleYear(int year) {
		return year % 4 == 0 && year % 100 != 0 || year % 400 == 0;
	}
	
	//获取某一月份的天数
	private static int getMonthDay(int month, boolean isDoubleYear) {
		switch(month) {
			case 1:case 3:case 5:case 7:case 8:case 10:case 12: return 31;
			case 4:case 6:case 9:case 11: return 30;
			default:{return isDoubleYear?29:28;}
		}
	}
	
	//打印	┃ ┏ ┓ ┗ ┛ ┳ ┣ ┫ ╋ ┳ ┻ ━
	private static void monthPrinter(int beforeDays, int bePrintedDay) {
		System.out.println("日\t一\t二\t三\t四\t五\t六");
		int week = beforeDays % 7;
		week++;
		for(int i = 0; i < week; i++) {
			System.out.print("\t");
		}
		
		for(int i = 1; i <= bePrintedDay; i++) {
			System.out.print(i + "\t");
			if((week + i) % 7 == 0) System.out.println();
		}
	}
	
	private static void printTable(int beforeDays, int bePrintedDay) {
		String head = "┏━━┳━━┳━━┳━━┳━━┳━━┳━━┓\r\n" + 
				"┃　一┃　二┃　三┃　四┃　五┃　六┃　七┃";
		String end = "\n┗━━┻━━┻━━┻━━┻━━┻━━┻━━┛";
		String center = "┣━━╋━━╋━━╋━━╋━━╋━━╋━━┫";
		System.out.println(head + center + "\n┃");
		int week = beforeDays % 7;
		int count = 0;
		for(int i = 0; i < week; i++) {
			System.out.print("　　┃");
		}
		for(int i = 1; i <= bePrintedDay; i++) {
			if((week + i) % 7 == 1) {
				System.out.print("\n" + center + "\n┃");
				count = 0;
			}
			System.out.print(getDay(i) + "┃");
			count++;
		}
		
		for(int i = 0; i < 7 - count; i++) {
			System.out.print("　　┃");
		}
		System.out.println(end);
	}
	
	//获得数字字符串
	private static String getDay(int dayNumber) {
		String Res = "";
		Res += dayNumber / 10 == 0?"　":getFormatNumber(dayNumber / 10);
		Res += getFormatNumber(dayNumber % 10);
		return Res;
	}
	
	private static String getFormatNumber(int n) {
		switch(n) {
		case 0:return "０";
		case 1:return "１";
		case 2:return "２";
		case 3:return "３";
		case 4:return "４";
		case 5:return "５";
		case 6:return "６";
		case 7:return "７";
		case 8:return "８";
		case 9:return "９";
		default :{
			return "";
		}
		}
	}
}