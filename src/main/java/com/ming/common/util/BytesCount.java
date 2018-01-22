package com.ming.common.util;

/**
 * 类名：BytesCount<br>
 * 功能：byte[]与int、shot、long、float之间的转换<br>
 * 版本：1.0<br>
 * 日期：2015-08-24<br>
 * 说明：暂无<br>
 * 
 * @author Ming
 *
 */
public class BytesCount {

	/**
	 * 将32位的int值放到4字节的byte[]里
	 * 
	 * @param num
	 *            要转换的值
	 * @return byte[] 转换后的数组
	 */
	public static byte[] intToBytes(int num) {
		byte[] bytes = new byte[4];
		// 取最高8位放到0下标
		bytes[0] = (byte) (num >>> 24);
		// 取次高8位放到1下标
		bytes[1] = (byte) (num >>> 16);
		// 取次底8位放到2下标
		bytes[2] = (byte) (num >>> 8);
		// 取最低8位放到3下标
		bytes[3] = (byte) (num);
		return bytes;
	}

	/**
	 * 将4字节的byte[]转换为int
	 * 
	 * @param bytes
	 *            要转换的数组
	 * @return int 已转换的值
	 */
	public static int bytesToInt(byte[] bytes) {
		byte[] a = new byte[4];
		int i = a.length - 1, j = bytes.length - 1;
		for (; i >= 0; i--, j--) {// 从b的尾部(即int值的低位)开始copy数据
			if (j >= 0)
				a[i] = bytes[j];
			else
				a[i] = 0;// 如果b.length不足4,则将高位补0
		}
		// &0xff将byte值无差异转成int,避免Java自动类型提升后,会保留高位的符号位
		int v0 = (a[0] & 0xff) << 24;
		int v1 = (a[1] & 0xff) << 16;
		int v2 = (a[2] & 0xff) << 8;
		int v3 = (a[3] & 0xff);
		return v0 + v1 + v2 + v3;
	}

	/**
	 * 转换short为byte
	 * 
	 * @param bytes
	 *            想获得结果的byte[]
	 * @param s
	 *            需要转换的short
	 * @param index
	 *            第几位开始存放
	 */
	public static void shortTobytes(byte bytes[], short s, int index) {
		bytes[index + 1] = (byte) (s >> 8);
		bytes[index + 0] = (byte) (s >> 0);
	}

	/**
	 * 通过byte数组取到short
	 * 
	 * @param bytes
	 *            要转换的byte[]
	 * @param index
	 *            第几位开始取
	 * @return 转换后的short
	 */
	public static short bytesToShort(byte[] bytes, int index) {
		return (short) (((bytes[index + 1] << 8) | bytes[index + 0] & 0xff));
	}

	/**
	 * char转换到byte[]
	 * 
	 * @param bytes
	 *            要获得的数组
	 * @param ch
	 *            要转换的char
	 * @param index
	 *            要放入的位置
	 */
	public static void charToBytes(byte[] bytes, char ch, int index) {
		int temp = (int) ch;
		// byte[] b = new byte[2];
		for (int i = 0; i < 2; i++) {
			// 将最高位保存在最低位
			bytes[index + i] = new Integer(temp & 0xff).byteValue();
			temp = temp >> 8; // 向右移8位
		}
	}

	/**
	 * byte[]转换到char
	 * 
	 * @param bytes
	 *            要转换的数组
	 * @param index
	 *            从哪里开始转换
	 * @return char 转换后的字符
	 */
	public static char bytesToChar(byte[] bytes, int index) {
		int s = 0;
		if (bytes[index + 1] > 0)
			s += bytes[index + 1];
		else
			s += 256 + bytes[index + 0];
		s *= 256;
		if (bytes[index + 0] > 0)
			s += bytes[index + 1];
		else
			s += 256 + bytes[index + 0];
		char ch = (char) s;
		return ch;
	}

	/**
	 * float转换byte
	 * 
	 * @param bytes
	 * @param f
	 * @param index
	 */
	public static void floatToByte(byte[] bytes, float f, int index) {
		// byte[] b = new byte[4];
		int l = Float.floatToIntBits(f);
		for (int i = 0; i < 4; i++) {
			bytes[index + i] = new Integer(l).byteValue();
			l = l >> 8;
		}
	}

	/**
	 * 通过byte数组取得float
	 * 
	 * @param bytes
	 * @param index
	 * @return float 转换后的float
	 */
	public static float byteToFloat(byte[] bytes, int index) {
		int l;
		l = bytes[index + 0];
		l &= 0xff;
		l |= ((long) bytes[index + 1] << 8);
		l &= 0xffff;
		l |= ((long) bytes[index + 2] << 16);
		l &= 0xffffff;
		l |= ((long) bytes[index + 3] << 24);
		return Float.intBitsToFloat(l);
	}

	/**
	 * double转换byte[]
	 * 
	 * @param bytes
	 *            转换后的数组
	 * @param d
	 *            转换的double
	 * @param index
	 *            插入数组的位置
	 */
	public static void doubleToBytes(byte[] bytes, double d, int index) {
		// byte[] b = new byte[8];
		long l = Double.doubleToLongBits(d);
		for (int i = 0; i < 4; i++) {
			bytes[index + i] = new Long(l).byteValue();
			l = l >> 8;
		}
	}

	/**
	 * 通过byte数组取得double
	 * 
	 * @param bytes
	 *            待转换数组
	 * @param index
	 *            待转换的数组开始位置
	 * @return double 转换后的double
	 */
	public static double bytesToDouble(byte[] bytes, int index) {
		long l;
		l = bytes[0];
		l &= 0xff;
		l |= ((long) bytes[1] << 8);
		l &= 0xffff;
		l |= ((long) bytes[2] << 16);
		l &= 0xffffff;
		l |= ((long) bytes[3] << 24);
		l &= 0xffffffffl;
		l |= ((long) bytes[4] << 32);
		l &= 0xffffffffffl;
		l |= ((long) bytes[5] << 40);
		l &= 0xffffffffffffl;
		l |= ((long) bytes[6] << 48);
		l &= 0xffffffffffffffl;
		l |= ((long) bytes[7] << 56);
		return Double.longBitsToDouble(l);
	}

	/**
	 * 将64位的long值放到8字节的byte数组
	 * 
	 * @param num
	 *            待转换的long
	 * @return 返回转换后的byte数组
	 */
	public static byte[] longToBytes(long num) {
		byte[] result = new byte[8];
		result[0] = (byte) (num >>> 56);// 取最高8位放到0下标
		result[1] = (byte) (num >>> 48);// 取最高8位放到0下标
		result[2] = (byte) (num >>> 40);// 取最高8位放到0下标
		result[3] = (byte) (num >>> 32);// 取最高8位放到0下标
		result[4] = (byte) (num >>> 24);// 取最高8位放到0下标
		result[5] = (byte) (num >>> 16);// 取次高8为放到1下标
		result[6] = (byte) (num >>> 8); // 取次低8位放到2下标
		result[7] = (byte) (num); // 取最低8位放到3下标
		return result;
	}

	/**
	 * 将8字节的byte数组转成一个long值
	 * 
	 * @param bytes
	 *            待转换的byte[]
	 * @return 转换后的long型数值
	 */
	public static long bytesTolong(byte[] bytes) {
		byte[] a = new byte[8];
		int i = a.length - 1, j = bytes.length - 1;
		for (; i >= 0; i--, j--) {// 从b的尾部(即int值的低位)开始copy数据
			if (j >= 0)
				a[i] = bytes[j];
			else
				a[i] = 0;// 如果b.length不足8,则将高位补0
		}
		// 注意此处和byte数组转换成int的区别在于，下面的转换中要将先将数组中的元素转换成long型再做移位操作，
		// 若直接做位移操作将得不到正确结果，因为Java默认操作数字时，若不加声明会将数字作为int型来对待，此处必须注意。
		// &0xff将byte值无差异转成int,避免Java自动类型提升后,会保留高位的符号位
		long v0 = (long) (a[0] & 0xff) << 56;
		long v1 = (long) (a[1] & 0xff) << 48;
		long v2 = (long) (a[2] & 0xff) << 40;
		long v3 = (long) (a[3] & 0xff) << 32;
		long v4 = (long) (a[4] & 0xff) << 24;
		long v5 = (long) (a[5] & 0xff) << 16;
		long v6 = (long) (a[6] & 0xff) << 8;
		long v7 = (long) (a[7] & 0xff);
		return v0 + v1 + v2 + v3 + v4 + v5 + v6 + v7;
	}

}
