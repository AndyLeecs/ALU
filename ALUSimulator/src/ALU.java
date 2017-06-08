import java.text.DecimalFormat;
import java.util.Random;

/**
 * ģ��ALU���������͸���������������
 * 
 * @author [161250055_���]
 *
 */

public class ALU
{

	/**
	 * ����ʮ���������Ķ����Ʋ����ʾ��<br/>
	 * ����integerRepresentation("9", 8)
	 * 
	 * @param number
	 *            ʮ������������Ϊ���������һλΪ��-������Ϊ������ 0�����޷���λ
	 * @param length
	 *            �����Ʋ����ʾ�ĳ���
	 * @return number�Ķ����Ʋ����ʾ������Ϊlength
	 */
	public String integerRepresentation(String number, int length)
	{
		// TODO YOUR CODE HERE.
		boolean neg = false;// ������ʶ�Ƿ��Ǹ���
		if (number.contains("-"))
		{
			number = number.substring(1);
			neg = true;

		}
		int a = Integer.parseInt(number);// ������ת��Ϊ��Ӧ������
		String string = "";// �洢Ҫ���ص��ַ���
		int b = 0;// �洢����

		// ����߽�ֵ
		if (a == Math.pow(2, length - 1) && neg)// ���aΪ��С����
			while (string.length() < length)
				string = 1 + string;
		// ��������
		else if (!neg)
		{
			if (a > Math.pow(2, length - 1) - 1)
				a = (int) (a % (Math.pow(2, length - 1)));// �Գ�����Χ����ȡģ
			do
			{
				b = a % 2;
				a = a / 2;
				string = b + string;
			} while (a != 0);// ���һ�����ȵ��ַ���

			while (string.length() < length)
				string = 0 + string;// ����������ȵ��ַ���
		} else
		{
			string = integerRepresentation(number, length);// ȡ���෴���Ķ����Ʊ�ʾ
			string = negation(string);// ��λȡ��
			string = oneAdder(string).substring(1);// �ټ�һ
		}

		return string;
	}

	/**
	 * ����ʮ���Ƹ������Ķ����Ʊ�ʾ�� ��Ҫ���� 0������񻯡����������+Inf���͡�-Inf������ NaN�����أ������� IEEE 754��
	 * �������Ϊ��0���롣<br/>
	 * ����floatRepresentation("11.375", 8, 11)
	 * 
	 * @param number
	 *            ʮ���Ƹ�����������С���㡣��Ϊ���������һλΪ��-������Ϊ������ 0�����޷���λ
	 * @param eLength
	 *            ָ���ĳ��ȣ�ȡֵ���ڵ��� 4
	 * @param sLength
	 *            β���ĳ��ȣ�ȡֵ���ڵ��� 4
	 * @return number�Ķ����Ʊ�ʾ������Ϊ 1+eLength+sLength���������ң�����Ϊ���š�ָ���������ʾ����β������λ���أ�
	 */
	public String floatRepresentation(String number, int eLength, int sLength)
	{
		// TODO YOUR CODE HERE.
		String string = "";// �洢Ҫ���ص��ַ���
		String e = new String();// �洢ָ��λ���ַ���
		String s = new String();// �洢β��λ���ַ���

		String s0 = new String();// β��λȫΪ0���ַ���
		while (s0.length() < sLength)
			s0 = s0 + 0;
		String s1 = new String();// β��λȫΪ1���ַ���
		while (s1.length() < sLength)
			s1 = s1 + 1;
		String e0 = new String();
		while (e0.length() < eLength)// ָ��λȫΪ0���ַ���
			e0 = e0 + 0;
		String e1 = new String();// ָ��λȫΪ1���ַ���
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
		double a = Double.parseDouble(number);// ������ת��Ϊ��Ӧ��С��
		
		if(a == 0){
			string = 0 + e0 + s0;
			return string;}
		if (Double.isNaN(a))
		{
			// NaN
			string = String.valueOf(r.nextBoolean());// ΪNaNȡ��һ������ķ���λ

			string = string + e1 + getRandomS(sLength);

		}

		else
		{

			int sign = 0;
			if (a < 0)
			{
				sign = 1;// �������λ
				a = -a;// ����ֵ�滻Ϊ��Ӧ������
				number = number.replace("-", "");// �ַ���ȥ������
			} else if (a == 0)// 0
				string = String.valueOf(r.nextBoolean()) + e0 + s0;

//			if (a > (Math.pow(2, Math.pow(2, eLength - 1) - 1)) * (2 - Math.pow(2, -sLength)))
				// inf
				

			// ���
			if ((a >= Math.pow(2, -Math.pow(2, eLength - 1) + 2))
					&& (a <= (Math.pow(2, Math.pow(2, eLength - 1) - 1)) * (2 - Math.pow(2, -sLength))))
			{

				int intPart = (int) a;// ��������
				int intRemainder = 0;
				String intString = "";
				double deciPart = a - intPart;// С������
				int deciRemainder = 0;
				String deciString = "";

				do
				{
					intRemainder = intPart % 2;
					intPart = intPart / 2;
					intString = intRemainder + intString;
				} while (intPart != 0);// ����������ֵ��ַ���

				s = intString + deciString;// ���β��

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

				} while (((deciPart != 0)) && (s.length() <= sLength));// ���С�����ֵ��ַ���

				int move = 0;// ���С����Ӧ�ƶ���λ��
				if (intString.equals("0"))
				{
					intString = intString.replace("0", "");

					// ֻ��С������û����������

					for (move = 0; true; move++)// ��ʱ����
					{

						if (deciString.charAt(0) != 0)
							break;// �����ͷ�ַ������������ѭ��
						deciString = deciString.substring(1);// ȥ����ͷ����
					}
					move = -move;

				} else
				{// ���������֣��л�û��С������
					move = intString.length();
					// ��ʱ����
				}
				e = integerRepresentation(Integer.toString((move - 2 + (int) (Math.pow(2, eLength - 1)))), eLength + 1);// ���ָ������
				e = e.substring(1);// ȥ������λ

				s = intString + deciString;
				s = s.substring(1);// ȥ����ͷ���ص�һλ1
				while (s.length() < sLength)
					s = s + 0;

				string = sign + e + s;// ���һ����񻯵��ַ���
			}

			// �ǹ��
			else if (a >= Math.pow(2, -(Math.pow(2, eLength - 1) - 2 + sLength)))
			{
				double deciPart = a * Math.pow(2, Math.pow(2, eLength - 1) - 2);// �����Ҫ��ʾΪβ������ֵ
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
					s = deciString;// ���β��

				} while ((deciPart != 1) && (deciPart != 0) && (s.length() < sLength));// ���С�����ֵ��ַ���

				while (s.length() < sLength)
					s = s + 0;

				string = sign + e0 + s;
			}

		}
}
		return string;

	}

	/**
	 * ����һ�������ȫ��β��λ
	 * 
	 * @param sLength
	 *            β��λ�ĳ���
	 * @return �����Ʊ�ʾ�ķ�ȫ��β��λ
	 */
	public String getRandomS(int sLength)
	{
		String s = new String();// �洢Ҫ���ص��ַ���
		Random r = new Random();

		while (s.length() < +sLength)
			s = s + String.valueOf(r.nextBoolean());// ��ȡ�涨���ȵ��ַ���

		String s0 = new String();// β��λȫΪ0���ַ���
		while (s0.length() < sLength)
			s0 = s0 + 0;

		if (s.equals(s0))
			s = getRandomS(sLength);// ȷ���ַ�������ȫ��

		return s;
	}

	/**
	 * ����ʮ���Ƹ�������IEEE 754��ʾ��Ҫ�����{@link #floatRepresentation(String, int, int)
	 * floatRepresentation}ʵ�֡�<br/>
	 * ����ieee754("11.375", 32)
	 * 
	 * @param number
	 *            ʮ���Ƹ�����������С���㡣��Ϊ���������һλΪ��-������Ϊ������ 0�����޷���λ
	 * @param length
	 *            �����Ʊ�ʾ�ĳ��ȣ�Ϊ32��64
	 * @return number��IEEE 754��ʾ������Ϊlength���������ң�����Ϊ���š�ָ���������ʾ����β������λ���أ�
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
	 * ��������Ʋ����ʾ����������ֵ��<br/>
	 * ����integerTrueValue("00001001")
	 * 
	 * @param operand
	 *            �����Ʋ����ʾ�Ĳ�����
	 * @return operand����ֵ����Ϊ���������һλΪ��-������Ϊ������ 0�����޷���λ
	 */
	public String integerTrueValue(String operand)
	{
		// TODO YOUR CODE HERE.

		String result = new String();
		result = "";

		// �����Ƿ�Ϊ����
		if (operand.charAt(0) == '1')
		{
			for(int i = 1;i<operand.length(); i++){
				if(operand.charAt(i) != '0')
					break;
				if(i == operand.length() - 1){
					result = "-" + (int)Math.pow(2, operand.length() - 1);
					return result;
				}//�����Ƿ�Ϊ��λΪ1���涼��0����ʽ
			}
			operand = negation(operand);// ��λȡ��
			operand = oneAdder(operand).substring(1);
			result = "-" + integerTrueValue(operand);
		}
		// ���Ǹ���

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
	 * ���������ԭ���ʾ�ĸ���������ֵ��<br/>
	 * ����floatTrueValue("01000001001101100000", 8, 11)
	 * 
	 * @param operand
	 *            �����Ʊ�ʾ�Ĳ�����
	 * @param eLength
	 *            ָ���ĳ��ȣ�ȡֵ���ڵ��� 4
	 * @param sLength
	 *            β���ĳ��ȣ�ȡֵ���ڵ��� 4
	 * @return operand����ֵ����Ϊ���������һλΪ��-������Ϊ������ 0�����޷���λ����������ֱ��ʾΪ��+Inf���͡�-Inf����
	 *         NaN��ʾΪ��NaN��
	 */
	public String floatTrueValue(String operand, int eLength, int sLength)
	{
		// TODO YOUR CODE HERE.

		// ��ʼ��
		String resultString = new String();
		double result;

		// ��ȡ������ַ���
		String s0 = new String();// β��λȫΪ0���ַ���
		while (s0.length() < sLength)
			s0 = s0 + 0;
		String s1 = new String();// β��λȫΪ1���ַ���
		while (s1.length() < sLength)
			s1 = s1 + 1;
		String e0 = new String();
		while (e0.length() < eLength)// ָ��λȫΪ0���ַ���
			e0 = e0 + 0;
		String e1 = new String();// ָ��λȫΪ1���ַ���
		while (e1.length() < eLength)
			e1 = e1 + 1;

		// ��ȡָ������
		String e = operand.substring(1, eLength + 1);
		// ��ȡβ������
		String s = operand.substring(eLength + 1);

		// ��ȡ����λ
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

		// �ǹ����
		if (e.equals(e0))
		{
			double temp = 0;
			for (int i = 0; i < len; i++)
				temp = temp + Integer.parseInt(list[i] + "") * Math.pow(2, -(i + 1));
			result = temp * Math.pow(2, -(Math.pow(2, eLength - 1) - 2));
		}
		// �����
		else
		{
			double temp = 1;
			for (int i = 0; i < len; i++)
				temp = temp + Integer.parseInt(list[i] + "") * Math.pow(2, -(i + 1));

			String eIntString = integerTrueValue("0" + e);
			int eInt = Integer.parseInt(eIntString);
			result = temp * Math.pow(2, eInt - Math.pow(2, eLength - 1) + 1);


		}
		// �����Ƿ�Ϊ����
		int resultInt = 0;
		if (result == (int) result)
		{
			resultInt = (int) result;
			resultString = signString + resultInt + ".0";
			// �����Ƿ�Ϊ��
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
	 * ��λȡ��������<br/>
	 * ����negation("00001001")
	 * 
	 * @param operand
	 *            �����Ʊ�ʾ�Ĳ�����
	 * @return operand��λȡ���Ľ��
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
	 * ���Ʋ�����<br/>
	 * ����leftShift("00001001", 2)
	 * 
	 * @param operand
	 *            �����Ʊ�ʾ�Ĳ�����
	 * @param n
	 *            ���Ƶ�λ��
	 * @return operand����nλ�Ľ��
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
	 * �߼����Ʋ�����<br/>
	 * ����logRightShift("11110110", 2)
	 * 
	 * @param operand
	 *            �����Ʊ�ʾ�Ĳ�����
	 * @param n
	 *            ���Ƶ�λ��
	 * @return operand�߼�����nλ�Ľ��
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
	 * �������Ʋ�����<br/>
	 * ����logRightShift("11110110", 2)
	 * 
	 * @param operand
	 *            �����Ʊ�ʾ�Ĳ�����
	 * @param n
	 *            ���Ƶ�λ��
	 * @return operand��������nλ�Ľ��
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
	 * �����������λ���мӷ����㣻
	 * 
	 * @param x
	 *            ��������ĳһλ��ȡ0��1
	 * @param y
	 *            ������ĳһλ��ȡ0��1
	 * @return ��ӵĽ�����ó���Ϊ2���ַ�����ʾ����1λ��ʾ��λ����2λ��ʾ��
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
	 * ȫ����������λ�Լ���λ���мӷ����㡣<br/>
	 * ����fullAdder('1', '1', '0')
	 * 
	 * @param x
	 *            ��������ĳһλ��ȡ0��1
	 * @param y
	 *            ������ĳһλ��ȡ0��1
	 * @param c
	 *            ��λ�Ե�ǰλ�Ľ�λ��ȡ0��1
	 * @return ��ӵĽ�����ó���Ϊ2���ַ�����ʾ����1λ��ʾ��λ����2λ��ʾ��
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
	 * 4λ���н�λ�ӷ�����Ҫ�����{@link #fullAdder(char, char, char) fullAdder}��ʵ��<br/>
	 * ����claAdder("1001", "0001", '1')
	 * 
	 * @param operand1
	 *            4λ�����Ʊ�ʾ�ı�����
	 * @param operand2
	 *            4λ�����Ʊ�ʾ�ļ���
	 * @param c
	 *            ��λ�Ե�ǰλ�Ľ�λ��ȡ0��1
	 * @return ����Ϊ5���ַ�����ʾ�ļ����������е�1λ�����λ��λ����4λ����ӽ�������н�λ��������ѭ�����
	 */
	public String claAdder(String operand1, String operand2, char c)
	{
		// TODO YOUR CODE HERE.
		// ��ȡ�����ַ�������ʾ���ַ�����
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
	 * αȡ��������ʵ�ֲ�����Ϊ0ʱ����1��������Ϊ1ʱ����0
	 * 
	 * @param operand
	 *            ��������Ϊ0��1
	 * @return operandȡ����Ľ��
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
	 * ��һ����ʵ�ֲ�������1�����㡣 ��Ҫ�������š����š�����ŵ�ģ�⣬
	 * ������ֱ�ӵ���{@link #fullAdder(char, char, char) fullAdder}��
	 * {@link #claAdder(String, String, char) claAdder}��
	 * {@link #adder(String, String, char, int) adder}��
	 * {@link #integerAddition(String, String, int) integerAddition}������<br/>
	 * ����oneAdder("00001001")
	 * 
	 * @param operand
	 *            �����Ʋ����ʾ�Ĳ�����
	 * @return operand��1�Ľ��������Ϊoperand�ĳ��ȼ�1�����е�1λָʾ�Ƿ���������Ϊ1������Ϊ0��������λΪ��ӽ��
	 */
	public String oneAdder(String operand)
	{
		// TODO YOUR CODE HERE.
		char[] list = operand.toCharArray();
		int len = list.length;
		String result = new String();
		int pro = 0;// ��λ
		for (int i = len - 1; i >= 0; i--)
		{

			int aint = Integer.parseInt(list[i] + "");// ��λ
			if (i == (len - 1))// ��1������
				result = (aint + "") + (Not(aint) + "");
			else
			{
				String proString = (pro & aint) + "";
				String intString = (pro ^ aint) + "";
				result = proString + intString + result.substring(1);
			}

			pro = Integer.parseInt(result.charAt(0) + "");// ��ý�λ
		}
		// ȥ����λ�Ľ�λ
		result = result.substring(1);

		// �ж��Ƿ����
		if (!((result.charAt(0) == '1' )&& (list[0] == '0')))
			result = "0" + result;
		else
			result = "1" + result;

		return result;
	}

	/**
	 * �ӷ�����Ҫ�����{@link #claAdder(String, String, char)}����ʵ�֡�<br/>
	 * ����adder("0100", "0011", ��0��, 8)
	 * 
	 * @param operand1
	 *            �����Ʋ����ʾ�ı�����
	 * @param operand2
	 *            �����Ʋ����ʾ�ļ���
	 * @param c
	 *            ���λ��λ
	 * @param length
	 *            ��Ų������ļĴ����ĳ��ȣ�Ϊ4�ı�����length��С�ڲ������ĳ��ȣ���ĳ���������ĳ���С��lengthʱ����Ҫ�ڸ�λ������λ
	 * @return ����Ϊlength+1���ַ�����ʾ�ļ����������е�1λָʾ�Ƿ���������Ϊ1������Ϊ0������lengthλ����ӽ��
	 */
	public String adder(String operand1, String operand2, char c, int length)
	{
		// TODO YOUR CODE HERE.
		// ������������λ���Ƿ���ȣ�ѡ��λ���϶�ļ�¼λ����λ���ٵĲ���
		int len1 = operand1.length();
		int len2 = operand2.length();

		int len = len2;

		if (len1 > len2)
		{

			len = len1;

		}

		String sign1 = operand1.charAt(0) + "";
		String sign2 = operand2.charAt(0) + "";// ��÷���λ

		// ������Ҫ���ü���claAdder
		int time = len / 4;
		// int newlength = time*4;

		while (operand1.length() < len)
			operand1 = sign1 + operand1;
		while (operand2.length() < len)
			operand2 = sign2 + operand2;

		// ����claAdder
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
				temp1 = operand1.substring(4 * now);// ����claAdder�Ĳ���
				temp2 = operand2.substring(4 * now);
			} else
			{
				temp1 = operand1.substring(4 * now, 4 * (now + 1));// ����claAdder�Ĳ���
				temp2 = operand2.substring(4 * now, 4 * (now + 1));

			}
			temp = claAdder(temp1, temp2, c) + temp;
			c = temp.charAt(0);
			temp = temp.substring(1);

		}

		// ����λ��
		char sign = temp.charAt(0);
		while (temp.length() < length)
			temp = sign + temp;
		// �������־
		String flowsign = "0";
		if ((operand1.charAt(0) == operand2.charAt(0)) && (operand1.charAt(0) != sign))
			flowsign = "1";

		// ���ؽ��
		temp = flowsign + temp;

		return temp;
	}

	/**
	 * �����ӷ���Ҫ�����{@link #adder(String, String, char, int) adder}����ʵ�֡�<br/>
	 * ����integerAddition("0100", "0011", 8)
	 * 
	 * @param operand1
	 *            �����Ʋ����ʾ�ı�����
	 * @param operand2
	 *            �����Ʋ����ʾ�ļ���
	 * @param length
	 *            ��Ų������ļĴ����ĳ��ȣ�Ϊ4�ı�����length��С�ڲ������ĳ��ȣ���ĳ���������ĳ���С��lengthʱ����Ҫ�ڸ�λ������λ
	 * @return ����Ϊlength+1���ַ�����ʾ�ļ����������е�1λָʾ�Ƿ���������Ϊ1������Ϊ0������lengthλ����ӽ��
	 */
	public String integerAddition(String operand1, String operand2, int length)
	{
		// TODO YOUR CODE HERE.

		return adder(operand1, operand2, '0', length);
	}

	/**
	 * �����������ɵ���{@link #adder(String, String, char, int) adder}����ʵ�֡�<br/>
	 * ����integerSubtraction("0100", "0011", 8)
	 * 
	 * @param operand1
	 *            �����Ʋ����ʾ�ı�����
	 * @param operand2
	 *            �����Ʋ����ʾ�ļ���
	 * @param length
	 *            ��Ų������ļĴ����ĳ��ȣ�Ϊ4�ı�����length��С�ڲ������ĳ��ȣ���ĳ���������ĳ���С��lengthʱ����Ҫ�ڸ�λ������λ
	 * @return ����Ϊlength+1���ַ�����ʾ�ļ����������е�1λָʾ�Ƿ���������Ϊ1������Ϊ0������lengthλ��������
	 */
	public String integerSubtraction(String operand1, String operand2, int length)
	{
		// TODO YOUR CODE HERE.

		operand2 = negation(operand2);
	

		return adder(operand1, operand2, '1',length);
		
	}

	/**
	 * �����˷���ʹ��Booth�㷨ʵ�֣��ɵ���{@link #adder(String, String, char, int)
	 * adder}�ȷ�����<br/>
	 * ����integerMultiplication("0100", "0011", 8)
	 * 
	 * @param operand1
	 *            �����Ʋ����ʾ�ı�����
	 * @param operand2
	 *            �����Ʋ����ʾ�ĳ���
	 * @param length
	 *            ��Ų������ļĴ����ĳ��ȣ�Ϊ4�ı�����length��С�ڲ������ĳ��ȣ���ĳ���������ĳ���С��lengthʱ����Ҫ�ڸ�λ������λ
	 * @return ����Ϊlength+1���ַ�����ʾ����˽�������е�1λָʾ�Ƿ���������Ϊ1������Ϊ0������lengthλ����˽��
	 */
	public String integerMultiplication(String operand1, String operand2, int length)
	{
		// TODO YOUR CODE HERE.
		// ȡ���ȣ�����
		


		String sign1 = operand1.charAt(0) + "";
		String sign2 = operand2.charAt(0) + "";// ��÷���λ

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

				}//�����Ƿ�Ϊ��λΪ1���涼��0����ʽ
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

		// ���һ���������Ĳ���
		String operand1Partner = oneAdder(negation(operand1)).substring(1);
		// �ѵ�һ�������������Ĳ��붼����
		while (operand1.length() < 2 * length + 1)
			operand1 = operand1 + 0;
		while (operand1Partner.length() < 2 * length + 1)
			operand1Partner = operand1Partner + 0;
		// ��ʼ���ڶ���������
		operand2 = operand2 + 0;
		while (operand2.length() < (2 * length + 1))
			operand2 = "0" + operand2;

		// ��ѭ��
		for (int i = 1; i <= length; i++)
		{
			// ��ȡ����λ
			String examine = operand2.substring(2 * length - 1);
			String forAdder2 = operand2.substring(0, length);
			String forAdder1 = operand1.substring(0, length);
			String forAdder1Partner = operand1Partner.substring(0, length);
			String forAdder = new String();
			// �ж���һ������
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
			// ����
			operand2 = ariRightShift(operand2, 1);

		}
		operand2 = operand2.substring(0, 2 * length);
		
		// �ж����
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
	 * �����Ĳ��ָ������������ɵ���{@link #adder(String, String, char, int) adder}�ȷ���ʵ�֡�<br/>
	 * ����integerDivision("0100", "0011", 8)
	 * 
	 * @param operand1
	 *            �����Ʋ����ʾ�ı�����
	 * @param operand2
	 *            �����Ʋ����ʾ�ĳ���
	 * @param length
	 *            ��Ų������ļĴ����ĳ��ȣ�Ϊ4�ı�����length��С�ڲ������ĳ��ȣ���ĳ���������ĳ���С��lengthʱ����Ҫ�ڸ�λ������λ
	 * @return ����Ϊ2*length+1���ַ�����ʾ�������������е�1λָʾ�Ƿ���������Ϊ1������Ϊ0�������lengthλΪ�̣����lengthλΪ����
	 */
	public String integerDivision(String operand1, String operand2, int length)
	{
		// TODO YOUR CODE HERE.
		if(Integer.parseInt(operand2) == 0)
			return "NaN";
		// �Ѳ�����Ԥ��
		String x = operand1;// ������������չ֮��Ľ��
		String y = operand2;// ����������չ֮��Ľ��

		String r = new String();// �����Ĵ���
		String q = "";// �������̼Ĵ���

		while (y.length() < length)
			y = operand2.charAt(0) + y;

		while (x.length() < 2 * length)
			x = operand1.charAt(0) + x;

		// ���һλ��qn
		// �жϱ�����������Ƿ�ͬ��,����м�����

		String former = new String();

		String ypartner = oneAdder(negation(y)).substring(1);
		String firstsign = "1";
		String secondsign = "0";

		int int1 = Integer.parseInt(operand1.charAt(0) + "");
		int int2 = Integer.parseInt(operand2.charAt(0) + "");
		System.out.println(int1+""+int2);
		if ((int1 ^ int2) == 1)
		{
			firstsign = "0";// �������
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

		// �ж����
		String flsign = "0";
		if (firstsign.equals(secondsign))
			flsign = "1";
		// ȷ����ֵ
		for (int i = 1; i <= length; i++)
		{
			// ����
			x = leftShift(x, 1);
			// ����
			x = x.substring(0, 2 * length - 1) + secondsign;
			// ����
			
			if (secondsign.equals("1"))
			{
				former = integerAddition(x.substring(0, length), ypartner, length);
				x = former.substring(1) + x.substring(length);

			} else
			{
				former = integerAddition(x.substring(0, length), y, length);
				x = former.substring(1) + x.substring(length);

			}
			
			// ����
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

		// �̵�����
		// ����
		r = x.substring(0, length);
		q = x.substring(length);
		q = leftShift(q, 1);
		// ����
		q = q.substring(0, length - 1) + secondsign;
		// �Ƿ���Ҫ����������Ҫ����������
		System.out.println(q);
		if (firstsign.equals("0"))
			q = oneAdder(q).substring(1);

		// ����������
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

		// �����̺�����λ��
		String result = q + r;
		// ���������־
		result = flsign + result;

		return result;
	}

	/**
	 * ����С���Ĳ��ָ������������ɵ���{@link #adder(String, String, char, int)
	 * adder}�ȷ���ʵ�֡�<br/>
	 * 
	 * 
	 * @param operand1
	 *            �����Ʋ����ʾ�ı�����
	 * @param operand2
	 *            �����Ʋ����ʾ�ĳ���
	 * @param length
	 *            ��Ų������ļĴ����ĳ��ȣ�Ϊ4�ı�����length��С�ڲ������ĳ��ȣ���ĳ���������ĳ���С��lengthʱ����Ҫ�ڸ�λ������λ
	 * @return ����Ϊ2*length+1���ַ�����ʾ�������������е�1λָʾ�Ƿ���������Ϊ1������Ϊ0�������lengthλΪ�̣����lengthλΪ����
	 */
	public String notIntegerDivision(String operand1, String operand2, int length)
	{
		// TODO YOUR CODE HERE.
		// �Ѳ�����Ԥ��
		String x = operand1;// ������������չ֮��Ľ��
		String y = operand2;// ����������չ֮��Ľ��

		String r = new String();// �����Ĵ���
		String q = "";// �������̼Ĵ���

		while (y.length() < length)
			y = operand2.charAt(0) + y;

		while (x.length() < length)
			x = operand1.charAt(0) + x;

		while (x.length() < 2 * length)
			x = x + operand1.charAt(0);

		// ���һλ��qn
		// �жϱ�����������Ƿ�ͬ��,����м�����

		String former = new String();

		String ypartner = oneAdder(negation(y)).substring(1);
		String firstsign = "1";
		String secondsign = "0";

		int int1 = Integer.parseInt(operand1.charAt(0) + "");
		int int2 = Integer.parseInt(operand2.charAt(0) + "");
		if ((int1 ^ int2) == 1)
		{
			firstsign = "0";// �������
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

		// �ж����
		String flsign = "0";
		if (firstsign.equals(secondsign))
			flsign = "1";
		// ȷ����ֵ
		for (int i = 1; i < length; i++)
		{
			// ����
			x = leftShift(x, 1);
			// ����
			x = x.substring(0, 2 * length - 1) + secondsign;

			// ����
			if (secondsign.equals("1"))
			{
				former = integerAddition(x.substring(0, length), ypartner, length);
				x = former.substring(1) + x.substring(length);

			} else
			{
				former = integerAddition(x.substring(0, length), y, length);
				x = former.substring(1) + x.substring(length);

			}
			// ����
			if (x.charAt(0) == y.charAt(0))
			{
				secondsign = "1";

			} else
			{
				secondsign = "0";
			}

		}

		// �̵�����

		r = x.substring(0, length);
		q = x.substring(length);

		// ����
		q = q.substring(0, length - 1) + secondsign;
		// �����̺�����λ��
		String result = q + r;
		// ���������־
		result = flsign + result;

		return result;
	}

	/**
	 * �����������ӷ������Ե���{@link #adder(String, String, char, int) adder}�ȷ�����
	 * ������ֱ�ӽ�������ת��Ϊ�����ʹ��{@link #integerAddition(String, String, int)
	 * integerAddition}�� {@link #integerSubtraction(String, String, int)
	 * integerSubtraction}��ʵ�֡�<br/>
	 * ����signedAddition("1100", "1011", 8)
	 * 
	 * @param operand1
	 *            ������ԭ���ʾ�ı����������е�1λΪ����λ
	 * @param operand2
	 *            ������ԭ���ʾ�ļ��������е�1λΪ����λ
	 * @param length
	 *            ��Ų������ļĴ����ĳ��ȣ�Ϊ4�ı�����length��С�ڲ������ĳ��ȣ����������ţ�����ĳ���������ĳ���С��lengthʱ����Ҫ���䳤����չ��length
	 * @return ����Ϊlength+2���ַ�����ʾ�ļ����������е�1λָʾ�Ƿ���������Ϊ1������Ϊ0������2λΪ����λ����lengthλ����ӽ��
	 */
	public String signedAddition(String operand1, String operand2, int length)
	{
		// TODO YOUR CODE HERE.
		String result = new String();
		// �Ƚ������������ķ��ţ���¼Ϊsame��same��ʾͬ��
		boolean same = false;
		if ((operand1.charAt(0) ^ operand2.charAt(0)) == 0)
			same = true;
		
		System.out.println(same+"");

		// ����λ�����õ���ֵλ�ͷ���λ
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
		

		String fsign = "0";// �����־
		String sign = "0";// ����λ

		// ��same
		// ��ֵλ���
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

			// �ж��������¼Ϊfsign�������λ������λfsignΪ1
			else
				fsign = result.charAt(0) + "";
			System.out.println(fsign + "");
			// �жϷ��ţ���¼Ϊsign���͵ķ���λȡ�������ķ���
			sign = sign1;
		} else
		{

			// ��sameΪ0
			// ��ֵλ���
			String ycomplement = oneAdder(negation(y)).substring(1); // y����ֵλ�Ĳ���
			System.out.println(ycomplement);
			result = adder(x, ycomplement, '0', length);
			System.out.println(result);
			// �ж���ֵλ�Ƿ���ȷ�������ֵλ������λ����������
			// ��ֵλ��ȷ������λȡ�������ķ���
			// ��ֵλ����ȷ������λȡ�����ķ���

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
	 * �������ӷ����ɵ���{@link #signedAddition(String, String, int)
	 * signedAddition}�ȷ���ʵ�֡�<br/>
	 * ����floatAddition("00111111010100000", "00111111001000000", 8, 8, 8)
	 * 
	 * @param operand1
	 *            �����Ʊ�ʾ�ı�����
	 * @param operand2
	 *            �����Ʊ�ʾ�ļ���
	 * @param eLength
	 *            ָ���ĳ��ȣ�ȡֵ���ڵ��� 4
	 * @param sLength
	 *            β���ĳ��ȣ�ȡֵ���ڵ��� 4
	 * @param gLength
	 *            ����λ�ĳ���
	 * @return ����Ϊ2+eLength+sLength���ַ�����ʾ����ӽ�������е�1λָʾ�Ƿ�ָ�����磨���Ϊ1������Ϊ0��������λ����������Ϊ���š�ָ���������ʾ����β������λ���أ����������Ϊ��0����
	 */
	public String floatAddition(String operand1, String operand2, int eLength, int sLength, int gLength)
	{
		// TODO YOUR CODE HERE.
		// 1 ��ʼ����
		// 1.1���Ϊ����λ�������β��
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

		// 1.2��ʼ������λ
		String g = new String();
		while (g.length() < gLength)
			g = g + "0";
		// 1.3�÷���λ������λ��β���ͱ���λ������β��m
		String m1 = sign1 + "1" + s1 + g;
		String m2 = sign2 + "1" + s2 + g;
		String m = new String();
		int m1length = m1.length();
		// 2�Խ�
		// 2.1���������
		String e2complement = oneAdder(negation(e2)).substring(1);// ��ñ������Ĳ���
		int elength = ((int) (e2complement.length() / 4) + 1) * 4;// ��ô�Ž���������ļĴ�������
		String gap = adder(e2complement, e1, '0', elength).substring(1);// ���㲹����ʽ�������ֵ
		int intgap = Integer.parseInt(integerTrueValue(gap));// ����ò�ֵ����ֵ
		// 2.2����β��
		if (intgap < 0)
		{
			e = e2;// ���������
			System.out.println(e);

			m1 = sign1 + logRightShift(m1.substring(1), -intgap);// �����β��

		} else
		{
			e = e1;
			m2 = sign2 + logRightShift(m2.substring(1), intgap);
		}
System.out.println(intgap+"");
System.out.println(m1);
System.out.println(m2);
		// 3β�����
		// 3.1��ô��β���������ļĴ�������
		int mlength = ((int) (m1length / 4) + 1) * 4;
		System.out.println("mlength:"+mlength);
		System.out.println("m1length:"+m1length);
		// 3.2���ӷ�
		m = signedAddition(m1, m2, mlength);
		
		System.out.println("adder:" +m);
		char mFlowsign = '0';
		m = m.substring(1);// �����λΪ����λ������Ϊmlength+1���ַ���
		System.out.println("substring"+m);
		if ((m.substring(1, mlength - m1length + 2)).contains("1"))
			mFlowsign = '1';
		else
			m = m.charAt(0) + m.substring(mlength - m1length + 2);// �Ѳ����0λȥ��
		System.out.println("treated:"+m);

		// 4������

		// 4.1 mΪ0���û�����
		if (integerTrueValue(m).equals("0"))
		{
			while (result.length() < 2 + eLength + sLength)
				result = result + "0";
			return result;
		}

		// 4.2 m��Ϊ0
		else
		{
			// ��
			// m���
			if (mFlowsign == '1')
			{
				System.out.println("flow:"+m);

				// �ҹ�
				// �Ľ���
				// �жϽ����Ƿ�Ϊȫ1
				if (allOne(e))
				{
					result = Result(e, m, "1", eLength, sLength);

					return result;
				}

				// ��Ϊȫ1�������ҹ棬
				// �������־������
				// ���򣬽����1��ִ����������
				else
				{
					e = oneAdder(e).substring(1);
					if (allOne(e))
					{
						result = Result(e, m, "1", eLength, sLength);
						return result;
					}
				}

				// ��β��
				// β������һλ
				m = m.charAt(0) + (ariRightShift(m.substring(1), 1)).substring(mlength - m1length + 1);
			}
			// ����
			// m�����
			else
			{
				System.out.println("leftzero:"+m);


				// ��
				if (m.charAt(1) != '1')
				{
					
					// m�����������kλ��0
					// ���
					// �Ľ���
					// ÿ�θ�ǰ�жϽ����Ƿ�Ϊȫ0
					String minusOne = new String();// ��λȫΪ1�������������ͬ���ַ���������������
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

		// ���ǣ��������磬�û����㣬����
		// ��β��

		// 5β������
		m = m.charAt(0) + m.substring(2, sLength + 2);

		// 6����������
		result = Result(e, m, "0", eLength, sLength);

		return result;
	}

	/**
	 * �жϽ����Ƿ�ȫΪ1
	 * 
	 * @param e
	 *            ��Ҫ�����Ľ���
	 * @return �жϽ����Ϊ����ֵ
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
	 * �жϽ����Ƿ�ȫΪ0
	 * 
	 * @param e
	 *            ��Ҫ�����Ľ���
	 * @return �жϽ����Ϊ����ֵ
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
	 * ��װ���
	 * 
	 * @param e
	 *            ��Ҫ�����Ľ���
	 * @param m
	 *            ��Ҫ������β��
	 * @param c
	 *            �Ƿ�����������Ϊ'1'
	 * @param eLength
	 *            ����ĳ���
	 * @param sLength
	 *            β���ĳ���
	 * @return ������
	 */
	public String Result(String e, String m, String c, int eLength, int sLength)
	{
		String result = c + m.charAt(0) + e + m.substring(1);
		return result;
	}

	/**
	 * �������������ɵ���{@link #floatAddition(String, String, int, int, int)
	 * floatAddition}����ʵ�֡�<br/>
	 * ����floatSubtraction("00111111010100000", "00111111001000000", 8, 8, 8)
	 * 
	 * @param operand1
	 *            �����Ʊ�ʾ�ı�����
	 * @param operand2
	 *            �����Ʊ�ʾ�ļ���
	 * @param eLength
	 *            ָ���ĳ��ȣ�ȡֵ���ڵ��� 4
	 * @param sLength
	 *            β���ĳ��ȣ�ȡֵ���ڵ��� 4
	 * @param gLength
	 *            ����λ�ĳ���
	 * @return ����Ϊ2+eLength+sLength���ַ�����ʾ�������������е�1λָʾ�Ƿ�ָ�����磨���Ϊ1������Ϊ0��������λ����������Ϊ���š�ָ���������ʾ����β������λ���أ����������Ϊ��0����
	 */
	public String floatSubtraction(String operand1, String operand2, int eLength, int sLength, int gLength)
	{
		// TODO YOUR CODE HERE.
		String operand2Complement = Not(Integer.parseInt(String.valueOf(operand2.charAt(0)))) + operand2.substring(1);
		String result = floatAddition(operand1, operand2Complement, eLength, sLength, gLength);
		return result;

	}

	/**
	 * �������˷����ɵ���{@link #integerMultiplication(String, String, int)
	 * integerMultiplication}�ȷ���ʵ�֡�<br/>
	 * ����floatMultiplication("00111110111000000", "00111111000000000", 8, 8)
	 * 
	 * @param operand1
	 *            �����Ʊ�ʾ�ı�����
	 * @param operand2
	 *            �����Ʊ�ʾ�ĳ���
	 * @param eLength
	 *            ָ���ĳ��ȣ�ȡֵ���ڵ��� 4
	 * @param sLength
	 *            β���ĳ��ȣ�ȡֵ���ڵ��� 4
	 * @return ����Ϊ2+eLength+sLength���ַ�����ʾ����˽��,���е�1λָʾ�Ƿ�ָ�����磨���Ϊ1������Ϊ0��������λ����������Ϊ���š�ָ���������ʾ����β������λ���أ����������Ϊ��0����
	 */
	public String floatMultiplication(String operand1, String operand2, int eLength, int sLength)
	{
		// TODO YOUR CODE HERE
		// 1 ��ʼ����
		// 1.1���Ϊ����λ�������β��
		String sign1 = operand1.charAt(0) + "";
		String sign2 = operand2.charAt(0) + "";
		String e1 = operand1.substring(1, eLength + 1);
		String e2 = operand2.substring(1, eLength + 1);
		String s1 = operand1.substring(eLength + 1);
		String s2 = operand2.substring(eLength + 1);
		String e = new String();

		String sign = new String();// ��Ϊ�������ѷ���λ��һ����������Ϊ����
		sign = "0";
		if (!sign1.equals(sign2))
		{
			sign = "1";
		}
		String result = new String();

		// 1.3�÷���λ������λ��β��������β��m
		String m1 = "0" + "1" + s1;
		String m2 = "0" + "1" + s2;
		String m = new String();
		int m1length = m1.length();
		// 1 β����ˣ��������
		// 1.1 β�����
		// ��ô��β���������ļĴ�������
		int mlength = ((int) (m1length / 4) + 1) * 4;
		while (m1.length() < mlength)
			m1 = "0" + m1;
		while (m2.length() < mlength)
			m2 = "0" + m2;
		// ���˷�
		m = integerMultiplication(m1, m2, mlength * 2);
		m = m.substring(1 + 2 * (mlength - m1length) + 1 + 1);
		System.out.println(m);
		System.out.println(integerTrueValue(m.substring(1)));
		System.out.println(integerTrueValue(m.substring(2)));
		// ȥ����λ�����λ,����Ϊ��ʹ������Ϊ4�ı��������ӵ�λ�������һ������λ
		// m = m.charAt(0) + m.substring(mlength*2 - m1length + 1);// �Ѳ����0λȥ��

		// 1.2 �������
		// 2.1������ӷ�

		int elength = (e2.length() / 4 + 1) * 4;// ��ô�Ž���������ļĴ�������
		String etemp = adder(e2, e1, '0', elength).substring(elength - e2.length() + 1);// ���㲹����ʽ�������ֵ
		int forAdderInt = (int) Math.pow(2, e2.length() - 1) + 1;
		String forAdder = integerRepresentation(forAdderInt + "", elength).substring(elength - e2.length());
		e = adder(etemp, forAdder, '0', elength).substring(elength - e2.length() + 1);
		System.out.println(e);
		// ����+129֮��������ֵ
		// 2 β�����
		if (integerTrueValue(operand1).equals("0")||integerTrueValue(operand2).equals("0"))
		{
			while (result.length() < 2 + eLength + sLength)
				result = result + "0";
			return result;
		}

		if (!(m.substring(0, 2).equals("01")))
			m = m.charAt(0) + (ariRightShift(m.substring(1), 1)).substring(mlength * 2 - m1length * 2);
		// 3 β�����봦��
		// β������
		m = sign + m.substring(2, 2 + sLength);
		// 4 ��������ж�
		String flsign = "0";
		if (allOne(e) || ((e1.charAt(0) == '1') && (e2.charAt(0) == '1') && (e.charAt(0) == '0')))
			flsign = "1";
		if (allZero(e) || (e1.charAt(1) == '0') && (e2.charAt(0) == '0') && (e.charAt(0) == '1'))

		{
			while (result.length() < 2 + eLength + sLength)
				result = result + "0";
			return result;
		}

		// ����������
		result = Result(e, m, flsign, eLength, sLength);

		return result;

	}

	/**
	 * �������������ɵ���{@link #integerDivision(String, String, int)
	 * integerDivision}�ȷ���ʵ�֡�<br/>
	 * ����floatDivision("00111110111000000", "00111111000000000", 8, 8)
	 * 
	 * @param operand1
	 *            �����Ʊ�ʾ�ı�����
	 * @param operand2
	 *            �����Ʊ�ʾ�ĳ���
	 * @param eLength
	 *            ָ���ĳ��ȣ�ȡֵ���ڵ��� 4
	 * @param sLength
	 *            β���ĳ��ȣ�ȡֵ���ڵ��� 4
	 * @return ����Ϊ2+eLength+sLength���ַ�����ʾ����˽��,���е�1λָʾ�Ƿ�ָ�����磨���Ϊ1������Ϊ0��������λ����������Ϊ���š�ָ���������ʾ����β������λ���أ����������Ϊ��0����
	 */
	public String floatDivision(String operand1, String operand2, int eLength, int sLength)
	{
		// TODO YOUR CODE HERE.
		// 1 ��ʼ����
		// 1.1���Ϊ����λ�������β��
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
			

		String sign = new String();// ��Ϊ�������ѷ���λ��һ����������Ϊ����
		sign = "0";
		if (!sign1.equals(sign2))
		{
			sign = "1";
		}
		String result = new String();

		// 1.3�÷���λ������λ��β��������β��m
		String m1 = "0" + "1" + s1;
		String m2 = "0" + "1" + s2;
		String m = new String();
		int m1length = m1.length();
		// 1 β����ˣ��������
		// 1.1 β�����
		// ��ô��β���������ļĴ�������
		int mlength = ((int) (m1length / 4) + 1) * 4;
		while (m1.length() < mlength)
			m1 = m1 + "0";
		while (m2.length() < mlength)
			m2 = m2 + "0";
		// ������
		m = notIntegerDivision(m1, m2, mlength);

		m = m.substring(1, mlength + 1);// ȥ����λ�����λ,����Ϊ��ʹ������Ϊ4�ı��������ӵ�λ
		// m = m.charAt(0) + m.substring(mlength*2 - m1length + 1);// �Ѳ����0λȥ��

		// 1.2 �������
		// 2.1���������

		int elength = (e2.length() / 4 + 1) * 4;// ��ô�Ž���������ļĴ�������
		String e2complement = oneAdder(negation(e2)).substring(1);// ��ñ������Ĳ�����ʽ
		String etemp = adder(e2complement, e1, '0', elength).substring(elength - e2.length() + 1);// ���㲹����ʽ�������ֵ
		int forAdderInt = (int) Math.pow(2, e2.length() - 1) - 1;
		String forAdder = integerRepresentation(forAdderInt + "", elength).substring(elength - e2.length());
		e = adder(etemp, forAdder, '0', elength).substring(elength - e2.length() + 1);// ����+129֮��������ֵ
		// 2 β�����
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

			String minusOne = new String();// ��λȫΪ1�������������ͬ���ַ���������������
			while (minusOne.length() < eLength)
				minusOne = minusOne + "1";
			e = adder(e, minusOne, '0', eLength).substring(1);
		}
		// 3 β�����봦��
		// β������
		m = sign + m.substring(2, sLength + 2);
		// 4 ��������ж�
		String flsign = "0";
		if (allOne(e) || ((e1.charAt(0) == '1') && (e2.charAt(0) == '0') && (e.charAt(0) == '0')))
			flsign = "1";
		if (allZero(e) || ((e1.charAt(0) == '0') && (e2.charAt(0) == '1') && (e.charAt(0) == '1')))

		{
			while (result.length() < 2 + eLength + sLength)
				result = result + "0";
			return result;
		}

		// ����������
		result = Result(e, m, flsign, eLength, sLength);

		return result;

	}
}
