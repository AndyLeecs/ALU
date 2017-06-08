import java.text.DecimalFormat;
import java.util.Random;

/**
 * 模拟ALU进行整数和浮点数的四则运算
 * 
 * @author [161250055_李安迪]
 *
 */

public class ALU
{

	/**
	 * 生成十进制整数的二进制补码表示。<br/>
	 * 例：integerRepresentation("9", 8)
	 * 
	 * @param number
	 *            十进制整数。若为负数；则第一位为“-”；若为正数或 0，则无符号位
	 * @param length
	 *            二进制补码表示的长度
	 * @return number的二进制补码表示，长度为length
	 */
	public String integerRepresentation(String number, int length)
	{
		// TODO YOUR CODE HERE.
		boolean neg = false;// 用来标识是否是负数
		if (number.contains("-"))
		{
			number = number.substring(1);
			neg = true;

		}
		int a = Integer.parseInt(number);// 将数字转换为对应的整数
		String string = "";// 存储要返回的字符串
		int b = 0;// 存储余数

		// 处理边界值
		if (a == Math.pow(2, length - 1) && neg)// 如果a为最小整数
			while (string.length() < length)
				string = 1 + string;
		// 处理正数
		else if (!neg)
		{
			if (a > Math.pow(2, length - 1) - 1)
				a = (int) (a % (Math.pow(2, length - 1)));// 对超过范围的数取模
			do
			{
				b = a % 2;
				a = a / 2;
				string = b + string;
			} while (a != 0);// 获得一定长度的字符串

			while (string.length() < length)
				string = 0 + string;// 获得期望长度的字符串
		} else
		{
			string = integerRepresentation(number, length);// 取得相反数的二进制表示
			string = negation(string);// 按位取反
			string = oneAdder(string).substring(1);// 再加一
		}

		return string;
	}

	/**
	 * 生成十进制浮点数的二进制表示。 需要考虑 0、反规格化、正负无穷（“+Inf”和“-Inf”）、 NaN等因素，具体借鉴 IEEE 754。
	 * 舍入策略为向0舍入。<br/>
	 * 例：floatRepresentation("11.375", 8, 11)
	 * 
	 * @param number
	 *            十进制浮点数，包含小数点。若为负数；则第一位为“-”；若为正数或 0，则无符号位
	 * @param eLength
	 *            指数的长度，取值大于等于 4
	 * @param sLength
	 *            尾数的长度，取值大于等于 4
	 * @return number的二进制表示，长度为 1+eLength+sLength。从左向右，依次为符号、指数（移码表示）、尾数（首位隐藏）
	 */
	public String floatRepresentation(String number, int eLength, int sLength)
	{
		// TODO YOUR CODE HERE.
		String string = "";// 存储要返回的字符串
		String e = new String();// 存储指数位的字符串
		String s = new String();// 存储尾数位的字符串

		String s0 = new String();// 尾数位全为0的字符串
		while (s0.length() < sLength)
			s0 = s0 + 0;
		String s1 = new String();// 尾数位全为1的字符串
		while (s1.length() < sLength)
			s1 = s1 + 1;
		String e0 = new String();
		while (e0.length() < eLength)// 指数位全为0的字符串
			e0 = e0 + 0;
		String e1 = new String();// 指数位全为1的字符串
		while (e1.length() < eLength)
			e1 = e1 + 1;

		Random r = new Random();
if(number.equals("+Inf")){
	string = "0" + e1 + s0;
	return string;
}

else if(number.equals("-Inf")){
	string = "1" + e1 + s0;
	return string;
}
else if(number.equals("-0.0")){
	string = "1" + e0 + s0;
	return string;
}
else{
		double a = Double.parseDouble(number);// 将数字转换为对应的小数
		
		if(a == 0){
			string = 0 + e0 + s0;
			return string;}
		if (Double.isNaN(a))
		{
			// NaN
			string = String.valueOf(r.nextBoolean());// 为NaN取得一个随机的符号位

			string = string + e1 + getRandomS(sLength);

		}

		else
		{

			int sign = 0;
			if (a < 0)
			{
				sign = 1;// 处理符号位
				a = -a;// 将数值替换为对应的正数
				number = number.replace("-", "");// 字符串去除负号
			} else if (a == 0)// 0
				string = String.valueOf(r.nextBoolean()) + e0 + s0;

//			if (a > (Math.pow(2, Math.pow(2, eLength - 1) - 1)) * (2 - Math.pow(2, -sLength)))
				// inf
				

			// 规格化
			if ((a >= Math.pow(2, -Math.pow(2, eLength - 1) + 2))
					&& (a <= (Math.pow(2, Math.pow(2, eLength - 1) - 1)) * (2 - Math.pow(2, -sLength))))
			{

				int intPart = (int) a;// 整数部分
				int intRemainder = 0;
				String intString = "";
				double deciPart = a - intPart;// 小数部分
				int deciRemainder = 0;
				String deciString = "";

				do
				{
					intRemainder = intPart % 2;
					intPart = intPart / 2;
					intString = intRemainder + intString;
				} while (intPart != 0);// 获得整数部分的字符串

				s = intString + deciString;// 获得尾数

				do
				{
					deciPart = deciPart * 2;
					if (deciPart >= 1)
					{
						deciPart = deciPart - 1;
						deciRemainder = 1;
					} else
						deciRemainder = 0;
					deciString = deciString + deciRemainder;
					s = intString + deciString;

				} while (((deciPart != 0)) && (s.length() <= sLength));// 获得小数部分的字符串

				int move = 0;// 获得小数点应移动的位数
				if (intString.equals("0"))
				{
					intString = intString.replace("0", "");

					// 只有小数部分没有整数部分

					for (move = 0; true; move++)// 此时右移
					{

						if (deciString.charAt(0) != 0)
							break;// 如果开头字符不是零就跳出循环
						deciString = deciString.substring(1);// 去掉开头的零
					}
					move = -move;

				} else
				{// 有整数部分，有或没有小数部分
					move = intString.length();
					// 此时左移
				}
				e = integerRepresentation(Integer.toString((move - 2 + (int) (Math.pow(2, eLength - 1)))), eLength + 1);// 获得指数部分
				e = e.substring(1);// 去掉符号位

				s = intString + deciString;
				s = s.substring(1);// 去掉开头隐藏的一位1
				while (s.length() < sLength)
					s = s + 0;

				string = sign + e + s;// 获得一个规格化的字符串
			}

			// 非规格化
			else if (a >= Math.pow(2, -(Math.pow(2, eLength - 1) - 2 + sLength)))
			{
				double deciPart = a * Math.pow(2, Math.pow(2, eLength - 1) - 2);// 获得需要表示为尾数的数值
				int deciRemainder = 0;
				String deciString = "";

				do
				{
					deciPart = deciPart * 2;
					if (deciPart >= 1)
					{
						deciPart = deciPart - 1;
						deciRemainder = 1;
					} else
						deciRemainder = 0;
					deciString = deciString + deciRemainder;
					s = deciString;// 获得尾数

				} while ((deciPart != 1) && (deciPart != 0) && (s.length() < sLength));// 获得小数部分的字符串

				while (s.length() < sLength)
					s = s + 0;

				string = sign + e0 + s;
			}

		}
}
		return string;

	}

	/**
	 * 生成一个随机非全零尾数位
	 * 
	 * @param sLength
	 *            尾数位的长度
	 * @return 二进制表示的非全零尾数位
	 */
	public String getRandomS(int sLength)
	{
		String s = new String();// 存储要返回的字符串
		Random r = new Random();

		while (s.length() < +sLength)
			s = s + String.valueOf(r.nextBoolean());// 获取规定长度的字符串

		String s0 = new String();// 尾数位全为0的字符串
		while (s0.length() < sLength)
			s0 = s0 + 0;

		if (s.equals(s0))
			s = getRandomS(sLength);// 确认字符串不是全零

		return s;
	}

	/**
	 * 生成十进制浮点数的IEEE 754表示，要求调用{@link #floatRepresentation(String, int, int)
	 * floatRepresentation}实现。<br/>
	 * 例：ieee754("11.375", 32)
	 * 
	 * @param number
	 *            十进制浮点数，包含小数点。若为负数；则第一位为“-”；若为正数或 0，则无符号位
	 * @param length
	 *            二进制表示的长度，为32或64
	 * @return number的IEEE 754表示，长度为length。从左向右，依次为符号、指数（移码表示）、尾数（首位隐藏）
	 */
	public String ieee754(String number, int length)
	{
		// TODO YOUR CODE HERE.
		String string = new String();
		if (length == 32)
			string = floatRepresentation(number, 8, 23);
		else
			string = floatRepresentation(number, 11, 52);
		return string;
	}

	/**
	 * 计算二进制补码表示的整数的真值。<br/>
	 * 例：integerTrueValue("00001001")
	 * 
	 * @param operand
	 *            二进制补码表示的操作数
	 * @return operand的真值。若为负数；则第一位为“-”；若为正数或 0，则无符号位
	 */
	public String integerTrueValue(String operand)
	{
		// TODO YOUR CODE HERE.

		String result = new String();
		result = "";

		// 检验是否为负数
		if (operand.charAt(0) == '1')
		{
			for(int i = 1;i<operand.length(); i++){
				if(operand.charAt(i) != '0')
					break;
				if(i == operand.length() - 1){
					result = "-" + (int)Math.pow(2, operand.length() - 1);
					return result;
				}//检验是否为首位为1后面都是0的形式
			}
			operand = negation(operand);// 按位取反
			operand = oneAdder(operand).substring(1);
			result = "-" + integerTrueValue(operand);
		}
		// 不是负数

		else
		{
			char[] list = operand.toCharArray();
			int length = list.length;
			length = length - 1;
			int resultInt = 0;
			for (int i = 0; i < list.length; i++)
			{
				int pow = (int) Math.pow(2, i);
				int that = Integer.parseInt(list[length] + "");
				int adder = that * pow;
				resultInt = adder + resultInt;
				length--;
			}

			result = Integer.toString(resultInt);
		}

		return result;

	}

	/**
	 * 计算二进制原码表示的浮点数的真值。<br/>
	 * 例：floatTrueValue("01000001001101100000", 8, 11)
	 * 
	 * @param operand
	 *            二进制表示的操作数
	 * @param eLength
	 *            指数的长度，取值大于等于 4
	 * @param sLength
	 *            尾数的长度，取值大于等于 4
	 * @return operand的真值。若为负数；则第一位为“-”；若为正数或 0，则无符号位。正负无穷分别表示为“+Inf”和“-Inf”，
	 *         NaN表示为“NaN”
	 */
	public String floatTrueValue(String operand, int eLength, int sLength)
	{
		// TODO YOUR CODE HERE.

		// 初始化
		String resultString = new String();
		double result;

		// 获取特殊的字符串
		String s0 = new String();// 尾数位全为0的字符串
		while (s0.length() < sLength)
			s0 = s0 + 0;
		String s1 = new String();// 尾数位全为1的字符串
		while (s1.length() < sLength)
			s1 = s1 + 1;
		String e0 = new String();
		while (e0.length() < eLength)// 指数位全为0的字符串
			e0 = e0 + 0;
		String e1 = new String();// 指数位全为1的字符串
		while (e1.length() < eLength)
			e1 = e1 + 1;

		// 获取指数部分
		String e = operand.substring(1, eLength + 1);
		// 获取尾数部分
		String s = operand.substring(eLength + 1);

		// 获取符号位
		char sign = operand.charAt(0);
		String signString = new String();
		signString = "";
		DecimalFormat format = new DecimalFormat("#0.###############");
		

		if (operand.equals("0" + e0 + s0))
		{
			resultString = "0.0";
			return resultString;
		}
		if (operand.equals("0" + e1 + s0))
		{
			resultString = "+Inf";
			return resultString;
		}
		if (operand.equals("1" + e1 + s0))
		{
			resultString = "-Inf";
			return resultString;
		}
		if (e.equals(e1))
		{
			resultString = "NaN";
			return resultString;
		}

		if (!(sign == '0'))
			signString = "-";

		char[] list = s.toCharArray();
		int len = list.length;

		// 非规格化数
		if (e.equals(e0))
		{
			double temp = 0;
			for (int i = 0; i < len; i++)
				temp = temp + Integer.parseInt(list[i] + "") * Math.pow(2, -(i + 1));
			result = temp * Math.pow(2, -(Math.pow(2, eLength - 1) - 2));
		}
		// 规格化数
		else
		{
			double temp = 1;
			for (int i = 0; i < len; i++)
				temp = temp + Integer.parseInt(list[i] + "") * Math.pow(2, -(i + 1));

			String eIntString = integerTrueValue("0" + e);
			int eInt = Integer.parseInt(eIntString);
			result = temp * Math.pow(2, eInt - Math.pow(2, eLength - 1) + 1);


		}
		// 检验是否为整数
		int resultInt = 0;
		if (result == (int) result)
		{
			resultInt = (int) result;
			resultString = signString + resultInt + ".0";
			// 检验是否为零
			if(result == 0)
				return 0+"";

			else{
			return resultString;
			
		}
		}

		
		resultString = signString + format.format(result) + "";
		return resultString;
	}

	/**
	 * 按位取反操作。<br/>
	 * 例：negation("00001001")
	 * 
	 * @param operand
	 *            二进制表示的操作数
	 * @return operand按位取反的结果
	 */
	public String negation(String operand)
	{
		// TODO YOUR CODE HERE.

		char[] list = operand.toCharArray();

		for (int i = 0; i < list.length; i++)
		{
			if (list[i] == '1')
				list[i] = '0';
			else
				list[i] = '1';
		}

		String result = new String(list);
		return result;
	}

	/**
	 * 左移操作。<br/>
	 * 例：leftShift("00001001", 2)
	 * 
	 * @param operand
	 *            二进制表示的操作数
	 * @param n
	 *            左移的位数
	 * @return operand左移n位的结果
	 */
	public String leftShift(String operand, int n)
	{
		// TODO YOUR CODE HERE.
		char[] list = operand.toCharArray();
		for (int i = 0; i < n; i++)
		{
			for (int j = 1; j < list.length; j++)
				list[j - 1] = list[j];
			list[list.length - 1] = '0';
		}
		String result = new String(list);

		return result;
	}

	/**
	 * 逻辑右移操作。<br/>
	 * 例：logRightShift("11110110", 2)
	 * 
	 * @param operand
	 *            二进制表示的操作数
	 * @param n
	 *            右移的位数
	 * @return operand逻辑右移n位的结果
	 */
	public String logRightShift(String operand, int n)
	{
		// TODO YOUR CODE HERE.
		char[] list = operand.toCharArray();
		for (int i = 0; i < n; i++)
		{
			for (int j = list.length - 1; j > 0; j--)
				list[j] = list[j - 1];
			list[0] = '0';
		}
		String result = new String(list);

		return result;

	}

	/**
	 * 算术右移操作。<br/>
	 * 例：logRightShift("11110110", 2)
	 * 
	 * @param operand
	 *            二进制表示的操作数
	 * @param n
	 *            右移的位数
	 * @return operand算术右移n位的结果
	 */
	public String ariRightShift(String operand, int n)
	{
		// TODO YOUR CODE HERE.
		boolean negative = false;
		if (operand.charAt(0) == '1')
			negative = true;
		char[] list = operand.toCharArray();
		for (int i = 0; i < n; i++)
		{
			for (int j = list.length - 1; j > 0; j--)
				list[j] = list[j - 1];
			if (!negative)
				list[0] = '0';
			else
				list[0] = '1';
		}
		String result = new String(list);

		return result;

	}

	/**
	 * 半加器，对两位进行加法运算；
	 * 
	 * @param x
	 *            被加数的某一位，取0或1
	 * @param y
	 *            加数的某一位，取0或1
	 * @return 相加的结果，用长度为2的字符串表示，第1位表示进位，第2位表示和
	 */
	public String halfAdder(char x, char y)
	{

		int sum = x ^ y;
		String sumString = sum + "";
		int pro = Integer.parseInt(x + "") & Integer.parseInt(y + "");
		String proString = pro + "";

		return proString + sumString;

	}

	/**
	 * 全加器，对两位以及进位进行加法运算。<br/>
	 * 例：fullAdder('1', '1', '0')
	 * 
	 * @param x
	 *            被加数的某一位，取0或1
	 * @param y
	 *            加数的某一位，取0或1
	 * @param c
	 *            低位对当前位的进位，取0或1
	 * @return 相加的结果，用长度为2的字符串表示，第1位表示进位，第2位表示和
	 */
	public String fullAdder(char x, char y, char c)
	{

		// TODO YOUR CODE HERE.
		String temp = halfAdder(x, y);

		String result2 = halfAdder(temp.charAt(1), c);
		String result = "" + (Integer.parseInt(temp.charAt(0) + "") | Integer.parseInt(result2.charAt(0) + ""))
				+ result2.substring(1);

		return result;
	}

	/**
	 * 4位先行进位加法器。要求采用{@link #fullAdder(char, char, char) fullAdder}来实现<br/>
	 * 例：claAdder("1001", "0001", '1')
	 * 
	 * @param operand1
	 *            4位二进制表示的被加数
	 * @param operand2
	 *            4位二进制表示的加数
	 * @param c
	 *            低位对当前位的进位，取0或1
	 * @return 长度为5的字符串表示的计算结果，其中第1位是最高位进位，后4位是相加结果，其中进位不可以由循环获得
	 */
	public String claAdder(String operand1, String operand2, char c)
	{
		// TODO YOUR CODE HERE.
		// 获取两个字符串所表示的字符数组
		char[] list1 = operand1.toCharArray();
		char[] list2 = operand2.toCharArray();
		String result = new String();
		result = "";
		String temp = new String();

		temp = fullAdder(list1[3], list2[3], c);
		result = result + temp.charAt(1);
		for (int i = 2; i >= 0; i--)
		{

			temp = fullAdder(list1[i], list2[i], temp.charAt(0));
			result = temp.charAt(1) + result;
		}

		result = temp.charAt(0) + result;
		return result;
	}

	/**
	 * 伪取反操作，实现操作数为0时返回1，操作数为1时返回0
	 * 
	 * @param operand
	 *            操作数，为0或1
	 * @return operand取反后的结果
	 */
	public int Not(int operand)
	{
		if (operand == 1)
		{
			operand = 0;
		} else
			operand = 1;
		return operand;
	}

	/**
	 * 加一器，实现操作数加1的运算。 需要采用与门、或门、异或门等模拟，
	 * 不可以直接调用{@link #fullAdder(char, char, char) fullAdder}、
	 * {@link #claAdder(String, String, char) claAdder}、
	 * {@link #adder(String, String, char, int) adder}、
	 * {@link #integerAddition(String, String, int) integerAddition}方法。<br/>
	 * 例：oneAdder("00001001")
	 * 
	 * @param operand
	 *            二进制补码表示的操作数
	 * @return operand加1的结果，长度为operand的长度加1，其中第1位指示是否溢出（溢出为1，否则为0），其余位为相加结果
	 */
	public String oneAdder(String operand)
	{
		// TODO YOUR CODE HERE.
		char[] list = operand.toCharArray();
		int len = list.length;
		String result = new String();
		int pro = 0;// 进位
		for (int i = len - 1; i >= 0; i--)
		{

			int aint = Integer.parseInt(list[i] + "");// 当位
			if (i == (len - 1))// 和1做运算
				result = (aint + "") + (Not(aint) + "");
			else
			{
				String proString = (pro & aint) + "";
				String intString = (pro ^ aint) + "";
				result = proString + intString + result.substring(1);
			}

			pro = Integer.parseInt(result.charAt(0) + "");// 获得进位
		}
		// 去掉首位的进位
		result = result.substring(1);

		// 判断是否溢出
		if (!((result.charAt(0) == '1' )&& (list[0] == '0')))
			result = "0" + result;
		else
			result = "1" + result;

		return result;
	}

	/**
	 * 加法器，要求调用{@link #claAdder(String, String, char)}方法实现。<br/>
	 * 例：adder("0100", "0011", ‘0’, 8)
	 * 
	 * @param operand1
	 *            二进制补码表示的被加数
	 * @param operand2
	 *            二进制补码表示的加数
	 * @param c
	 *            最低位进位
	 * @param length
	 *            存放操作数的寄存器的长度，为4的倍数。length不小于操作数的长度，当某个操作数的长度小于length时，需要在高位补符号位
	 * @return 长度为length+1的字符串表示的计算结果，其中第1位指示是否溢出（溢出为1，否则为0），后length位是相加结果
	 */
	public String adder(String operand1, String operand2, char c, int length)
	{
		// TODO YOUR CODE HERE.
		// 两个操作数的位数是否相等，选择位数较多的记录位数，位数少的补齐
		int len1 = operand1.length();
		int len2 = operand2.length();

		int len = len2;

		if (len1 > len2)
		{

			len = len1;

		}

		String sign1 = operand1.charAt(0) + "";
		String sign2 = operand2.charAt(0) + "";// 获得符号位

		// 计算需要调用几次claAdder
		int time = len / 4;
		// int newlength = time*4;

		while (operand1.length() < len)
			operand1 = sign1 + operand1;
		while (operand2.length() < len)
			operand2 = sign2 + operand2;

		// 调用claAdder
		String temp = new String();
		temp = "";
		int now = 0;
		for (int i = 1; i <= time; i++)
		{
			now = time - i;
			String temp1 = new String();
			String temp2 = new String();
			if (i == 1)
			{
				temp1 = operand1.substring(4 * now);// 传入claAdder的参数
				temp2 = operand2.substring(4 * now);
			} else
			{
				temp1 = operand1.substring(4 * now, 4 * (now + 1));// 传入claAdder的参数
				temp2 = operand2.substring(4 * now, 4 * (now + 1));

			}
			temp = claAdder(temp1, temp2, c) + temp;
			c = temp.charAt(0);
			temp = temp.substring(1);

		}

		// 补齐位数
		char sign = temp.charAt(0);
		while (temp.length() < length)
			temp = sign + temp;
		// 加溢出标志
		String flowsign = "0";
		if ((operand1.charAt(0) == operand2.charAt(0)) && (operand1.charAt(0) != sign))
			flowsign = "1";

		// 返回结果
		temp = flowsign + temp;

		return temp;
	}

	/**
	 * 整数加法，要求调用{@link #adder(String, String, char, int) adder}方法实现。<br/>
	 * 例：integerAddition("0100", "0011", 8)
	 * 
	 * @param operand1
	 *            二进制补码表示的被加数
	 * @param operand2
	 *            二进制补码表示的加数
	 * @param length
	 *            存放操作数的寄存器的长度，为4的倍数。length不小于操作数的长度，当某个操作数的长度小于length时，需要在高位补符号位
	 * @return 长度为length+1的字符串表示的计算结果，其中第1位指示是否溢出（溢出为1，否则为0），后length位是相加结果
	 */
	public String integerAddition(String operand1, String operand2, int length)
	{
		// TODO YOUR CODE HERE.

		return adder(operand1, operand2, '0', length);
	}

	/**
	 * 整数减法，可调用{@link #adder(String, String, char, int) adder}方法实现。<br/>
	 * 例：integerSubtraction("0100", "0011", 8)
	 * 
	 * @param operand1
	 *            二进制补码表示的被减数
	 * @param operand2
	 *            二进制补码表示的减数
	 * @param length
	 *            存放操作数的寄存器的长度，为4的倍数。length不小于操作数的长度，当某个操作数的长度小于length时，需要在高位补符号位
	 * @return 长度为length+1的字符串表示的计算结果，其中第1位指示是否溢出（溢出为1，否则为0），后length位是相减结果
	 */
	public String integerSubtraction(String operand1, String operand2, int length)
	{
		// TODO YOUR CODE HERE.

		operand2 = negation(operand2);
	

		return adder(operand1, operand2, '1',length);
		
	}

	/**
	 * 整数乘法，使用Booth算法实现，可调用{@link #adder(String, String, char, int)
	 * adder}等方法。<br/>
	 * 例：integerMultiplication("0100", "0011", 8)
	 * 
	 * @param operand1
	 *            二进制补码表示的被乘数
	 * @param operand2
	 *            二进制补码表示的乘数
	 * @param length
	 *            存放操作数的寄存器的长度，为4的倍数。length不小于操作数的长度，当某个操作数的长度小于length时，需要在高位补符号位
	 * @return 长度为length+1的字符串表示的相乘结果，其中第1位指示是否溢出（溢出为1，否则为0），后length位是相乘结果
	 */
	public String integerMultiplication(String operand1, String operand2, int length)
	{
		// TODO YOUR CODE HERE.
		// 取长度，补齐
		


		String sign1 = operand1.charAt(0) + "";
		String sign2 = operand2.charAt(0) + "";// 获得符号位

		while (operand1.length() < length)
			operand1 = sign1 + operand1;
		while (operand2.length() < length)
			operand2 = sign2 + operand2;
		
		boolean trouble = false;
		if ((operand1.charAt(0) == '1') && (operand2.charAt(0) == '0'))
		{
			for(int i = 1;i<operand1.length(); i++){
				if(operand1.charAt(i) != '0')
					break;
				if(i == operand1.length() - 1){
					String temp = "";
					temp = operand1;
					operand1 = operand2;
					operand2 = temp;
					trouble = true;

				}//检验是否为首位为1后面都是0的形式
			}
		}
		else if(operand2.charAt(0) == '1')
			for(int i = 1;i<operand2.length(); i++){
				if(operand1.charAt(i) != '0')
					break;
				if(i == operand2.length()-1){
					trouble = true;
				}
			}

		// 求第一个操作数的补码
		String operand1Partner = oneAdder(negation(operand1)).substring(1);
		// 把第一个操作数和它的补码都左移
		while (operand1.length() < 2 * length + 1)
			operand1 = operand1 + 0;
		while (operand1Partner.length() < 2 * length + 1)
			operand1Partner = operand1Partner + 0;
		// 初始化第二个操作数
		operand2 = operand2 + 0;
		while (operand2.length() < (2 * length + 1))
			operand2 = "0" + operand2;

		// 进循环
		for (int i = 1; i <= length; i++)
		{
			// 获取后两位
			String examine = operand2.substring(2 * length - 1);
			String forAdder2 = operand2.substring(0, length);
			String forAdder1 = operand1.substring(0, length);
			String forAdder1Partner = operand1Partner.substring(0, length);
			String forAdder = new String();
			// 判断下一步操作
			if (examine.equals("01"))
			{

				forAdder = adder(forAdder2, forAdder1, '0', length);
				operand2 = forAdder.substring(1) + operand2.substring(length);
			}
			if (examine.equals("10"))
			{
				forAdder = adder(forAdder2, forAdder1Partner, '0', length);
				operand2 = forAdder.substring(1) + operand2.substring(length);
			}
			// 右移
			operand2 = ariRightShift(operand2, 1);

		}
		operand2 = operand2.substring(0, 2 * length);
		
		// 判断溢出
		String sign = "0";
		String thrown = operand2.substring(0, length);
		for (char c : thrown.toCharArray())
			if (c != operand2.charAt(length))
			{
				sign = "1";

				break;
			}
		if((sign1.equals(sign2))&&trouble)
			sign = "1";
		return sign + operand2.substring(length);
	}

	/**
	 * 整数的不恢复余数除法，可调用{@link #adder(String, String, char, int) adder}等方法实现。<br/>
	 * 例：integerDivision("0100", "0011", 8)
	 * 
	 * @param operand1
	 *            二进制补码表示的被除数
	 * @param operand2
	 *            二进制补码表示的除数
	 * @param length
	 *            存放操作数的寄存器的长度，为4的倍数。length不小于操作数的长度，当某个操作数的长度小于length时，需要在高位补符号位
	 * @return 长度为2*length+1的字符串表示的相除结果，其中第1位指示是否溢出（溢出为1，否则为0），其后length位为商，最后length位为余数
	 */
	public String integerDivision(String operand1, String operand2, int length)
	{
		// TODO YOUR CODE HERE.
		if(Integer.parseInt(operand2) == 0)
			return "NaN";
		// 把操作数预置
		String x = operand1;// 被除数符号扩展之后的结果
		String y = operand2;// 除数符号扩展之后的结果

		String r = new String();// 余数寄存器
		String q = "";// 余数或商寄存器

		while (y.length() < length)
			y = operand2.charAt(0) + y;

		while (x.length() < 2 * length)
			x = operand1.charAt(0) + x;

		// 求第一位商qn
		// 判断被除数与除数是否同号,获得中间余数

		String former = new String();

		String ypartner = oneAdder(negation(y)).substring(1);
		String firstsign = "1";
		String secondsign = "0";

		int int1 = Integer.parseInt(operand1.charAt(0) + "");
		int int2 = Integer.parseInt(operand2.charAt(0) + "");
		System.out.println(int1+""+int2);
		if ((int1 ^ int2) == 1)
		{
			firstsign = "0";// 代表异号
			former = integerAddition(x.substring(0, length), y, length);
			x = former.substring(1) + x.substring(length);

		} else
		{
			former = integerAddition(x.substring(0, length), ypartner, length);
			x = former.substring(1) + x.substring(length);

		}
		if (x.charAt(0) == y.charAt(0))
		{
			secondsign = "1";

		} else
		{
			secondsign = "0";
		}

		// 判断溢出
		String flsign = "0";
		if (firstsign.equals(secondsign))
			flsign = "1";
		// 确定商值
		for (int i = 1; i <= length; i++)
		{
			// 左移
			x = leftShift(x, 1);
			// 上商
			x = x.substring(0, 2 * length - 1) + secondsign;
			// 做和
			
			if (secondsign.equals("1"))
			{
				former = integerAddition(x.substring(0, length), ypartner, length);
				x = former.substring(1) + x.substring(length);

			} else
			{
				former = integerAddition(x.substring(0, length), y, length);
				x = former.substring(1) + x.substring(length);

			}
			
			// 定号
			if(operand1.charAt(0) == '1'){
			if (x.charAt(0) == y.charAt(0) && (!integerTrueValue(x.substring(0,length)).equals("0")))
			{
				secondsign = "1";

			}else
				secondsign = "0";
			}
			if(operand1.charAt(0) == '0'){
				if (x.charAt(0) == y.charAt(0))
				{
					secondsign = "1";

				}else
					secondsign = "0";
				}

				
			
		}

		// 商的修正
		// 左移
		r = x.substring(0, length);
		q = x.substring(length);
		q = leftShift(q, 1);
		// 上商
		q = q.substring(0, length - 1) + secondsign;
		// 是否需要修正，如需要，进行修正
		System.out.println(q);
		if (firstsign.equals("0"))
			q = oneAdder(q).substring(1);

		// 余数的修正
		if (r.charAt(0) != operand1.charAt(0))
			if (firstsign.equals("0"))
			{
				former = integerAddition(r, ypartner, length);
				r = former.substring(1);

			} else
			{
				former = integerAddition(r, y, length);
				r = former.substring(1);

			}

		// 调换商和余数位置
		String result = q + r;
		// 加上溢出标志
		result = flsign + result;

		return result;
	}

	/**
	 * 定点小数的不恢复余数除法，可调用{@link #adder(String, String, char, int)
	 * adder}等方法实现。<br/>
	 * 
	 * 
	 * @param operand1
	 *            二进制补码表示的被除数
	 * @param operand2
	 *            二进制补码表示的除数
	 * @param length
	 *            存放操作数的寄存器的长度，为4的倍数。length不小于操作数的长度，当某个操作数的长度小于length时，需要在高位补符号位
	 * @return 长度为2*length+1的字符串表示的相除结果，其中第1位指示是否溢出（溢出为1，否则为0），其后length位为商，最后length位为余数
	 */
	public String notIntegerDivision(String operand1, String operand2, int length)
	{
		// TODO YOUR CODE HERE.
		// 把操作数预置
		String x = operand1;// 被除数符号扩展之后的结果
		String y = operand2;// 除数符号扩展之后的结果

		String r = new String();// 余数寄存器
		String q = "";// 余数或商寄存器

		while (y.length() < length)
			y = operand2.charAt(0) + y;

		while (x.length() < length)
			x = operand1.charAt(0) + x;

		while (x.length() < 2 * length)
			x = x + operand1.charAt(0);

		// 求第一位商qn
		// 判断被除数与除数是否同号,获得中间余数

		String former = new String();

		String ypartner = oneAdder(negation(y)).substring(1);
		String firstsign = "1";
		String secondsign = "0";

		int int1 = Integer.parseInt(operand1.charAt(0) + "");
		int int2 = Integer.parseInt(operand2.charAt(0) + "");
		if ((int1 ^ int2) == 1)
		{
			firstsign = "0";// 代表异号
			former = integerAddition(x.substring(0, length), y, length);
			x = former.substring(1) + x.substring(length);

		} else
		{
			former = integerAddition(x.substring(0, length), ypartner, length);
			x = former.substring(1) + x.substring(length);

		}
		if (x.charAt(0) == y.charAt(0))
		{
			secondsign = "1";

		}

		// 判断溢出
		String flsign = "0";
		if (firstsign.equals(secondsign))
			flsign = "1";
		// 确定商值
		for (int i = 1; i < length; i++)
		{
			// 左移
			x = leftShift(x, 1);
			// 上商
			x = x.substring(0, 2 * length - 1) + secondsign;

			// 做和
			if (secondsign.equals("1"))
			{
				former = integerAddition(x.substring(0, length), ypartner, length);
				x = former.substring(1) + x.substring(length);

			} else
			{
				former = integerAddition(x.substring(0, length), y, length);
				x = former.substring(1) + x.substring(length);

			}
			// 定号
			if (x.charAt(0) == y.charAt(0))
			{
				secondsign = "1";

			} else
			{
				secondsign = "0";
			}

		}

		// 商的修正

		r = x.substring(0, length);
		q = x.substring(length);

		// 上商
		q = q.substring(0, length - 1) + secondsign;
		// 调换商和余数位置
		String result = q + r;
		// 加上溢出标志
		result = flsign + result;

		return result;
	}

	/**
	 * 带符号整数加法，可以调用{@link #adder(String, String, char, int) adder}等方法，
	 * 但不能直接将操作数转换为补码后使用{@link #integerAddition(String, String, int)
	 * integerAddition}、 {@link #integerSubtraction(String, String, int)
	 * integerSubtraction}来实现。<br/>
	 * 例：signedAddition("1100", "1011", 8)
	 * 
	 * @param operand1
	 *            二进制原码表示的被加数，其中第1位为符号位
	 * @param operand2
	 *            二进制原码表示的加数，其中第1位为符号位
	 * @param length
	 *            存放操作数的寄存器的长度，为4的倍数。length不小于操作数的长度（不包含符号），当某个操作数的长度小于length时，需要将其长度扩展到length
	 * @return 长度为length+2的字符串表示的计算结果，其中第1位指示是否溢出（溢出为1，否则为0），第2位为符号位，后length位是相加结果
	 */
	public String signedAddition(String operand1, String operand2, int length)
	{
		// TODO YOUR CODE HERE.
		String result = new String();
		// 比较两个操作数的符号，记录为same，same表示同号
		boolean same = false;
		if ((operand1.charAt(0) ^ operand2.charAt(0)) == 0)
			same = true;
		
		System.out.println(same+"");

		// 补齐位数，得到数值位和符号位
		String sign1 = operand1.charAt(0) + "";
		String sign2 = operand2.charAt(0) + "";

		String x = operand1.substring(1);
		String y = operand2.substring(1);

		while (x.length() < length)
			x = 0 + x;
		while (y.length() < length)
			y = 0 + y;
		
		System.out.println("longer:"+x);
		System.out.println("longer:"+y);
		

		String fsign = "0";// 溢出标志
		String sign = "0";// 符号位

		// 若same
		// 数值位相加
		if (same)
		{
			System.out.println(operand1);
System.out.println(x);
System.out.println(operand2);
System.out.println(y);
			result = adder(x, y, '0', length);
			System.out.println(result);
			if((Integer.parseInt(result.substring(1),2)|0) == 0)
				fsign = "1";

			// 判断溢出，记录为fsign，若最高位产生进位fsign为1
			else
				fsign = result.charAt(0) + "";
			System.out.println(fsign + "");
			// 判断符号，记录为sign，和的符号位取被加数的符号
			sign = sign1;
		} else
		{

			// 若same为0
			// 数值位相减
			String ycomplement = oneAdder(negation(y)).substring(1); // y的数值位的补码
			System.out.println(ycomplement);
			result = adder(x, ycomplement, '0', length);
			System.out.println(result);
			// 判断数值位是否正确，最高数值位产生进位，否则求补码
			// 数值位正确，符号位取被加数的符号
			// 数值位不正确，符号位取加数的符号

			if (result.charAt(1) == '0')
			{
				sign = sign1;

			} else
			{
				result = oneAdder(negation(result)).substring(1);
				sign = sign2;
			}
		}

		
		result = fsign + sign + "0" + result.substring(2);
		System.out.println();
		return result;
	}

	/**
	 * 浮点数加法，可调用{@link #signedAddition(String, String, int)
	 * signedAddition}等方法实现。<br/>
	 * 例：floatAddition("00111111010100000", "00111111001000000", 8, 8, 8)
	 * 
	 * @param operand1
	 *            二进制表示的被加数
	 * @param operand2
	 *            二进制表示的加数
	 * @param eLength
	 *            指数的长度，取值大于等于 4
	 * @param sLength
	 *            尾数的长度，取值大于等于 4
	 * @param gLength
	 *            保护位的长度
	 * @return 长度为2+eLength+sLength的字符串表示的相加结果，其中第1位指示是否指数上溢（溢出为1，否则为0），其余位从左到右依次为符号、指数（移码表示）、尾数（首位隐藏）。舍入策略为向0舍入
	 */
	public String floatAddition(String operand1, String operand2, int eLength, int sLength, int gLength)
	{
		// TODO YOUR CODE HERE.
		// 1 初始化，
		// 1.1拆分为符号位，阶码和尾数
		String sign1 = operand1.charAt(0) + "";
		String sign2 = operand2.charAt(0) + "";
		String e1 = operand1.substring(1, eLength + 1);
		String e2 = operand2.substring(1, eLength + 1);
		String s1 = operand1.substring(eLength + 1);
		String s2 = operand2.substring(eLength + 1);
		if(integerTrueValue(e1).equals("0"))
			return "0"+operand2;
		if(integerTrueValue(e2).equals("0"))
			return "0"+operand1;
		String e = new String();

		String result = new String();

		// 1.2初始化保护位
		String g = new String();
		while (g.length() < gLength)
			g = g + "0";
		// 1.3用符号位，隐藏位，尾数和保护位构成新尾数m
		String m1 = sign1 + "1" + s1 + g;
		String m2 = sign2 + "1" + s2 + g;
		String m = new String();
		int m1length = m1.length();
		// 2对阶
		// 2.1做移码减法
		String e2complement = oneAdder(negation(e2)).substring(1);// 获得被减数的补码
		int elength = ((int) (e2complement.length() / 4) + 1) * 4;// 获得存放阶码操作数的寄存器长度
		String gap = adder(e2complement, e1, '0', elength).substring(1);// 计算补码形式的移码差值
		int intgap = Integer.parseInt(integerTrueValue(gap));// 计算该差值的真值
		// 2.2调整尾数
		if (intgap < 0)
		{
			e = e2;// 获得新移码
			System.out.println(e);

			m1 = sign1 + logRightShift(m1.substring(1), -intgap);// 获得新尾数

		} else
		{
			e = e1;
			m2 = sign2 + logRightShift(m2.substring(1), intgap);
		}
System.out.println(intgap+"");
System.out.println(m1);
System.out.println(m2);
		// 3尾数相加
		// 3.1获得存放尾数操作数的寄存器长度
		int mlength = ((int) (m1length / 4) + 1) * 4;
		System.out.println("mlength:"+mlength);
		System.out.println("m1length:"+m1length);
		// 3.2做加法
		m = signedAddition(m1, m2, mlength);
		
		System.out.println("adder:" +m);
		char mFlowsign = '0';
		m = m.substring(1);// 获得首位为符号位，长度为mlength+1的字符串
		System.out.println("substring"+m);
		if ((m.substring(1, mlength - m1length + 2)).contains("1"))
			mFlowsign = '1';
		else
			m = m.charAt(0) + m.substring(mlength - m1length + 2);// 把补充的0位去掉
		System.out.println("treated:"+m);

		// 4结果规格化

		// 4.1 m为0，置机器零
		if (integerTrueValue(m).equals("0"))
		{
			while (result.length() < 2 + eLength + sLength)
				result = result + "0";
			return result;
		}

		// 4.2 m不为0
		else
		{
			// 若
			// m溢出
			if (mFlowsign == '1')
			{
				System.out.println("flow:"+m);

				// 右规
				// 改阶码
				// 判断阶码是否为全1
				if (allOne(e))
				{
					result = Result(e, m, "1", eLength, sLength);

					return result;
				}

				// 若为全1，不需右规，
				// 置溢出标志，结束
				// 否则，阶码加1，执行上述操作
				else
				{
					e = oneAdder(e).substring(1);
					if (allOne(e))
					{
						result = Result(e, m, "1", eLength, sLength);
						return result;
					}
				}

				// 改尾数
				// 尾数右移一位
				m = m.charAt(0) + (ariRightShift(m.substring(1), 1)).substring(mlength - m1length + 1);
			}
			// 否则
			// m不溢出
			else
			{
				System.out.println("leftzero:"+m);


				// 若
				if (m.charAt(1) != '1')
				{
					
					// m最左边有连续k位个0
					// 左规
					// 改阶码
					// 每次改前判断阶码是否为全0
					String minusOne = new String();// 各位全为1，长度与阶码相同的字符串，用于做减法
					while (minusOne.length() < eLength)
						minusOne = minusOne + "1";

					while (m.charAt(1) != '1')
					{
						if (allZero(e))
						{
							while (result.length() < 2 + eLength + sLength)
								result = result + "0";
							return result;

						} else
						{
							e = adder(e, minusOne, '0', eLength).substring(1);
							m = m.charAt(0) + leftShift(m.substring(1), 1);
						}

					}
				}
			}
		}

		// 若是，阶码下溢，置机器零，结束
		// 改尾数

		// 5尾数舍入
		m = m.charAt(0) + m.substring(2, sLength + 2);

		// 6正常，结束
		result = Result(e, m, "0", eLength, sLength);

		return result;
	}

	/**
	 * 判断阶码是否全为1
	 * 
	 * @param e
	 *            需要操作的阶码
	 * @return 判断结果，为布尔值
	 */
	public boolean allOne(String e)
	{
		boolean allOne = true;
		char[] list = e.toCharArray();
		for (char c : list)
		{
			if (c != '1')
			{
				allOne = false;
				break;
			}
		}
		return allOne;
	}

	/**
	 * 判断阶码是否全为0
	 * 
	 * @param e
	 *            需要操作的阶码
	 * @return 判断结果，为布尔值
	 */
	public boolean allZero(String e)
	{
		boolean allZero = true;
		char[] list = e.toCharArray();
		for (char c : list)
		{
			if (c != '0')
			{
				allZero = false;
				break;
			}
		}
		return allZero;
	}

	/**
	 * 组装结果
	 * 
	 * @param e
	 *            需要操作的阶码
	 * @param m
	 *            需要操作的尾数
	 * @param c
	 *            是否溢出，若溢出为'1'
	 * @param eLength
	 *            阶码的长度
	 * @param sLength
	 *            尾数的长度
	 * @return 计算结果
	 */
	public String Result(String e, String m, String c, int eLength, int sLength)
	{
		String result = c + m.charAt(0) + e + m.substring(1);
		return result;
	}

	/**
	 * 浮点数减法，可调用{@link #floatAddition(String, String, int, int, int)
	 * floatAddition}方法实现。<br/>
	 * 例：floatSubtraction("00111111010100000", "00111111001000000", 8, 8, 8)
	 * 
	 * @param operand1
	 *            二进制表示的被减数
	 * @param operand2
	 *            二进制表示的减数
	 * @param eLength
	 *            指数的长度，取值大于等于 4
	 * @param sLength
	 *            尾数的长度，取值大于等于 4
	 * @param gLength
	 *            保护位的长度
	 * @return 长度为2+eLength+sLength的字符串表示的相减结果，其中第1位指示是否指数上溢（溢出为1，否则为0），其余位从左到右依次为符号、指数（移码表示）、尾数（首位隐藏）。舍入策略为向0舍入
	 */
	public String floatSubtraction(String operand1, String operand2, int eLength, int sLength, int gLength)
	{
		// TODO YOUR CODE HERE.
		String operand2Complement = Not(Integer.parseInt(String.valueOf(operand2.charAt(0)))) + operand2.substring(1);
		String result = floatAddition(operand1, operand2Complement, eLength, sLength, gLength);
		return result;

	}

	/**
	 * 浮点数乘法，可调用{@link #integerMultiplication(String, String, int)
	 * integerMultiplication}等方法实现。<br/>
	 * 例：floatMultiplication("00111110111000000", "00111111000000000", 8, 8)
	 * 
	 * @param operand1
	 *            二进制表示的被乘数
	 * @param operand2
	 *            二进制表示的乘数
	 * @param eLength
	 *            指数的长度，取值大于等于 4
	 * @param sLength
	 *            尾数的长度，取值大于等于 4
	 * @return 长度为2+eLength+sLength的字符串表示的相乘结果,其中第1位指示是否指数上溢（溢出为1，否则为0），其余位从左到右依次为符号、指数（移码表示）、尾数（首位隐藏）。舍入策略为向0舍入
	 */
	public String floatMultiplication(String operand1, String operand2, int eLength, int sLength)
	{
		// TODO YOUR CODE HERE
		// 1 初始化，
		// 1.1拆分为符号位，阶码和尾数
		String sign1 = operand1.charAt(0) + "";
		String sign2 = operand2.charAt(0) + "";
		String e1 = operand1.substring(1, eLength + 1);
		String e2 = operand2.substring(1, eLength + 1);
		String s1 = operand1.substring(eLength + 1);
		String s2 = operand2.substring(eLength + 1);
		String e = new String();

		String sign = new String();// 若为负数，把符号位置一，操作数改为正数
		sign = "0";
		if (!sign1.equals(sign2))
		{
			sign = "1";
		}
		String result = new String();

		// 1.3用符号位，隐藏位，尾数构成新尾数m
		String m1 = "0" + "1" + s1;
		String m2 = "0" + "1" + s2;
		String m = new String();
		int m1length = m1.length();
		// 1 尾数相乘，阶码相加
		// 1.1 尾数相乘
		// 获得存放尾数操作数的寄存器长度
		int mlength = ((int) (m1length / 4) + 1) * 4;
		while (m1.length() < mlength)
			m1 = "0" + m1;
		while (m2.length() < mlength)
			m2 = "0" + m2;
		// 做乘法
		m = integerMultiplication(m1, m2, mlength * 2);
		m = m.substring(1 + 2 * (mlength - m1length) + 1 + 1);
		System.out.println(m);
		System.out.println(integerTrueValue(m.substring(1)));
		System.out.println(integerTrueValue(m.substring(2)));
		// 去除首位的溢出位,后面为了使操作数为4的倍数所增加的位，多余的一个符号位
		// m = m.charAt(0) + m.substring(mlength*2 - m1length + 1);// 把补充的0位去掉

		// 1.2 阶码相加
		// 2.1做移码加法

		int elength = (e2.length() / 4 + 1) * 4;// 获得存放阶码操作数的寄存器长度
		String etemp = adder(e2, e1, '0', elength).substring(elength - e2.length() + 1);// 计算补码形式的移码和值
		int forAdderInt = (int) Math.pow(2, e2.length() - 1) + 1;
		String forAdder = integerRepresentation(forAdderInt + "", elength).substring(elength - e2.length());
		e = adder(etemp, forAdder, '0', elength).substring(elength - e2.length() + 1);
		System.out.println(e);
		// 计算+129之后的移码和值
		// 2 尾数规格化
		if (integerTrueValue(operand1).equals("0")||integerTrueValue(operand2).equals("0"))
		{
			while (result.length() < 2 + eLength + sLength)
				result = result + "0";
			return result;
		}

		if (!(m.substring(0, 2).equals("01")))
			m = m.charAt(0) + (ariRightShift(m.substring(1), 1)).substring(mlength * 2 - m1length * 2);
		// 3 尾数舍入处理
		// 尾数舍入
		m = sign + m.substring(2, 2 + sLength);
		// 4 阶码溢出判断
		String flsign = "0";
		if (allOne(e) || ((e1.charAt(0) == '1') && (e2.charAt(0) == '1') && (e.charAt(0) == '0')))
			flsign = "1";
		if (allZero(e) || (e1.charAt(1) == '0') && (e2.charAt(0) == '0') && (e.charAt(0) == '1'))

		{
			while (result.length() < 2 + eLength + sLength)
				result = result + "0";
			return result;
		}

		// 正常，结束
		result = Result(e, m, flsign, eLength, sLength);

		return result;

	}

	/**
	 * 浮点数除法，可调用{@link #integerDivision(String, String, int)
	 * integerDivision}等方法实现。<br/>
	 * 例：floatDivision("00111110111000000", "00111111000000000", 8, 8)
	 * 
	 * @param operand1
	 *            二进制表示的被除数
	 * @param operand2
	 *            二进制表示的除数
	 * @param eLength
	 *            指数的长度，取值大于等于 4
	 * @param sLength
	 *            尾数的长度，取值大于等于 4
	 * @return 长度为2+eLength+sLength的字符串表示的相乘结果,其中第1位指示是否指数上溢（溢出为1，否则为0），其余位从左到右依次为符号、指数（移码表示）、尾数（首位隐藏）。舍入策略为向0舍入
	 */
	public String floatDivision(String operand1, String operand2, int eLength, int sLength)
	{
		// TODO YOUR CODE HERE.
		// 1 初始化，
		// 1.1拆分为符号位，阶码和尾数
		String sign1 = operand1.charAt(0) + "";
		String sign2 = operand2.charAt(0) + "";
		String e1 = operand1.substring(1, eLength + 1);
		String e2 = operand2.substring(1, eLength + 1);
		String s1 = operand1.substring(eLength + 1);
		String s2 = operand2.substring(eLength + 1);
		String e = new String();
		
		if(Integer.parseInt(operand1.substring(1),2)==0){
			String temp = "";
			for(int i= 0 ; i < 2 + eLength + sLength ; i++)
				temp = temp + "0";
			return temp;
		}
		else if(Integer.parseInt(operand2.substring(1),2) == 0){
			String temp = "00";
			for(int i = 0 ; i < eLength ; i++)
				temp = temp + "1";
			for(int i = 0 ; i < sLength ; i++)
				temp = temp + "0";
			
			return temp;
		}
			

		String sign = new String();// 若为负数，把符号位置一，操作数改为正数
		sign = "0";
		if (!sign1.equals(sign2))
		{
			sign = "1";
		}
		String result = new String();

		// 1.3用符号位，隐藏位，尾数构成新尾数m
		String m1 = "0" + "1" + s1;
		String m2 = "0" + "1" + s2;
		String m = new String();
		int m1length = m1.length();
		// 1 尾数相乘，阶码相加
		// 1.1 尾数相除
		// 获得存放尾数操作数的寄存器长度
		int mlength = ((int) (m1length / 4) + 1) * 4;
		while (m1.length() < mlength)
			m1 = m1 + "0";
		while (m2.length() < mlength)
			m2 = m2 + "0";
		// 做除法
		m = notIntegerDivision(m1, m2, mlength);

		m = m.substring(1, mlength + 1);// 去除首位的溢出位,后面为了使操作数为4的倍数所增加的位
		// m = m.charAt(0) + m.substring(mlength*2 - m1length + 1);// 把补充的0位去掉

		// 1.2 阶码相减
		// 2.1做移码减法

		int elength = (e2.length() / 4 + 1) * 4;// 获得存放阶码操作数的寄存器长度
		String e2complement = oneAdder(negation(e2)).substring(1);// 获得被减数的补码形式
		String etemp = adder(e2complement, e1, '0', elength).substring(elength - e2.length() + 1);// 计算补码形式的移码和值
		int forAdderInt = (int) Math.pow(2, e2.length() - 1) - 1;
		String forAdder = integerRepresentation(forAdderInt + "", elength).substring(elength - e2.length());
		e = adder(etemp, forAdder, '0', elength).substring(elength - e2.length() + 1);// 计算+129之后的移码和值
		// 2 尾数规格化
		if (integerTrueValue(m) == "0")
		{
			while (result.length() < 2 + eLength + sLength)
				result = result + "0";
			return result;
		}

		if (!(m.charAt(1) == '1'))
		{
			m = leftShift(m, 1);
			if (integerTrueValue(m) == "0")
			{
				while (result.length() < 2 + eLength + sLength)
					result = result + "0";
				return result;
			}

			String minusOne = new String();// 各位全为1，长度与阶码相同的字符串，用于做减法
			while (minusOne.length() < eLength)
				minusOne = minusOne + "1";
			e = adder(e, minusOne, '0', eLength).substring(1);
		}
		// 3 尾数舍入处理
		// 尾数舍入
		m = sign + m.substring(2, sLength + 2);
		// 4 阶码溢出判断
		String flsign = "0";
		if (allOne(e) || ((e1.charAt(0) == '1') && (e2.charAt(0) == '0') && (e.charAt(0) == '0')))
			flsign = "1";
		if (allZero(e) || ((e1.charAt(0) == '0') && (e2.charAt(0) == '1') && (e.charAt(0) == '1')))

		{
			while (result.length() < 2 + eLength + sLength)
				result = result + "0";
			return result;
		}

		// 正常，结束
		result = Result(e, m, flsign, eLength, sLength);

		return result;

	}
}
